package com.flipkart.krystal.krystex;

import com.flipkart.krystal.data.Inputs;
import com.flipkart.krystal.krystex.decoration.LogicExecutionContext;
import com.flipkart.krystal.krystex.decoration.MainLogicDecorator;
import com.flipkart.krystal.krystex.decoration.MainLogicDecoratorConfig;
import com.flipkart.krystal.krystex.decoration.MainLogicDecoratorConfig.DecoratorContext;
import com.flipkart.krystal.krystex.node.DependantChain;
import com.flipkart.krystal.krystex.node.NodeDefinition;
import com.flipkart.krystal.krystex.node.NodeLogicId;
import com.flipkart.krystal.logic.LogicTag;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract sealed class MainLogicDefinition<T> extends LogicDefinition<MainLogic<T>>
    permits IOLogicDefinition, ComputeLogicDefinition {

  protected MainLogicDefinition(
      NodeLogicId nodeLogicId,
      Set<String> inputs,
      ImmutableMap<String, LogicTag> logicTags,
      MainLogic<T> mainLogic) {
    super(nodeLogicId, inputs, logicTags, mainLogic);
  }

  public final ImmutableMap<Inputs, CompletableFuture<T>> execute(ImmutableList<Inputs> inputs) {
    return logic().execute(inputs);
  }

  private ImmutableMap<String, MainLogicDecoratorConfig> requestScopedLogicDecoratorConfigs =
      ImmutableMap.of();

  /** LogicDecorator Id -> LogicDecorator */
  private final Map<String, MainLogicDecoratorConfig> sessionScopedLogicDecoratorConfigs =
      new HashMap<>();

  private final Map<String, Map<String, MainLogicDecorator>> sessionScopedDecorators =
      new LinkedHashMap<>();

  public ImmutableMap<String, MainLogicDecoratorConfig> getRequestScopedLogicDecoratorConfigs() {
    return requestScopedLogicDecoratorConfigs;
  }

  public ImmutableMap<String, MainLogicDecorator> getSessionScopedLogicDecorators(
      NodeDefinition nodeDefinition, @Nullable DependantChain dependants) {
    Map<String, MainLogicDecorator> decorators = new LinkedHashMap<>();
    sessionScopedLogicDecoratorConfigs.forEach(
        (s, decoratorConfig) -> {
          LogicExecutionContext logicExecutionContext =
              new LogicExecutionContext(
                  nodeDefinition.nodeId(),
                  logicTags(),
                  dependants,
                  nodeDefinition.nodeDefinitionRegistry());
          if (decoratorConfig.shouldDecorate().test(logicExecutionContext)) {
            String instanceId = decoratorConfig.instanceIdGenerator().apply(logicExecutionContext);
            decorators.put(
                s,
                sessionScopedDecorators
                    .computeIfAbsent(s, k -> new LinkedHashMap<>())
                    .computeIfAbsent(
                        instanceId,
                        k ->
                            decoratorConfig
                                .factory()
                                .apply(new DecoratorContext(instanceId, logicExecutionContext))));
          }
        });
    return ImmutableMap.copyOf(decorators);
  }

  public void registerRequestScopedDecorator(
      Collection<MainLogicDecoratorConfig> decoratorConfigs) {
    //noinspection UnstableApiUsage
    requestScopedLogicDecoratorConfigs =
        ImmutableMap.<String, MainLogicDecoratorConfig>builderWithExpectedSize(
                requestScopedLogicDecoratorConfigs.size() + decoratorConfigs.size())
            .putAll(sessionScopedLogicDecoratorConfigs)
            .putAll(
                decoratorConfigs.stream()
                    .collect(
                        Collectors.toMap(
                            MainLogicDecoratorConfig::decoratorType, Function.identity())))
            .build();
  }

  public void registerSessionScopedLogicDecorator(MainLogicDecoratorConfig decoratorConfig) {
    sessionScopedLogicDecoratorConfigs.put(decoratorConfig.decoratorType(), decoratorConfig);
  }
}
