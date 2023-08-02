package com.flipkart.krystal.krystex.kryon;

import static com.flipkart.krystal.krystex.kryon.KryonUtils.createResolverDefinitionsByInputs;
import static com.google.common.collect.ImmutableSet.toImmutableSet;
import static java.util.stream.Collectors.groupingBy;

import com.flipkart.krystal.krystex.MainLogicDefinition;
import com.flipkart.krystal.krystex.commands.KryonCommand;
import com.flipkart.krystal.krystex.decoration.LogicDecorationOrdering;
import com.flipkart.krystal.krystex.decoration.LogicExecutionContext;
import com.flipkart.krystal.krystex.decoration.MainLogicDecorator;
import com.flipkart.krystal.krystex.request.RequestIdGenerator;
import com.flipkart.krystal.krystex.resolution.ResolverDefinition;
import com.flipkart.krystal.utils.ImmutableMapView;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;

abstract sealed class AbstractKryon<C extends KryonCommand, R extends KryonResponse>
    implements Kryon<C, R> permits BatchKryon, GranularKryon {

  protected final KryonDefinition kryonDefinition;
  protected final KryonId kryonId;
  protected final KryonExecutor kryonExecutor;
  /** decoratorType -> Decorator */
  protected final Function<LogicExecutionContext, ImmutableMap<String, MainLogicDecorator>>
      requestScopedDecoratorsSupplier;

  protected final LogicDecorationOrdering logicDecorationOrdering;

  protected final ImmutableMapView<Optional<String>, List<ResolverDefinition>>
      resolverDefinitionsByInput;
  protected final ImmutableMapView<String, ImmutableSet<ResolverDefinition>>
      resolverDefinitionsByDependencies;
  protected final ImmutableSet<String> dependenciesWithNoResolvers;
  protected final RequestIdGenerator requestIdGenerator;

  AbstractKryon(
      KryonDefinition kryonDefinition,
      KryonExecutor kryonExecutor,
      Function<LogicExecutionContext, ImmutableMap<String, MainLogicDecorator>>
          requestScopedDecoratorsSupplier,
      LogicDecorationOrdering logicDecorationOrdering,
      RequestIdGenerator requestIdGenerator) {
    this.kryonDefinition = kryonDefinition;
    this.kryonId = kryonDefinition.kryonId();
    this.kryonExecutor = kryonExecutor;
    this.requestScopedDecoratorsSupplier = requestScopedDecoratorsSupplier;
    this.logicDecorationOrdering = logicDecorationOrdering;
    this.resolverDefinitionsByInput =
        createResolverDefinitionsByInputs(kryonDefinition.resolverDefinitions());
    this.resolverDefinitionsByDependencies =
        ImmutableMapView.viewOf(
            kryonDefinition.resolverDefinitions().stream()
                .collect(groupingBy(ResolverDefinition::dependencyName, toImmutableSet())));
    this.dependenciesWithNoResolvers =
        kryonDefinition.dependencyKryons().keySet().stream()
            .filter(
                depName ->
                    resolverDefinitionsByDependencies
                        .getOrDefault(depName, ImmutableSet.of())
                        .isEmpty())
            .collect(toImmutableSet());
    this.requestIdGenerator = requestIdGenerator;
  }

  protected NavigableSet<MainLogicDecorator> getSortedDecorators(DependantChain dependantChain) {
    MainLogicDefinition<Object> mainLogicDefinition = kryonDefinition.getMainLogicDefinition();
    Map<String, MainLogicDecorator> decorators =
        new LinkedHashMap<>(
            mainLogicDefinition.getSessionScopedLogicDecorators(kryonDefinition, dependantChain));
    // If the same decoratorType is configured for session and request scope, request scope
    // overrides session scope.
    decorators.putAll(
        requestScopedDecoratorsSupplier.apply(
            new LogicExecutionContext(
                kryonId,
                mainLogicDefinition.logicTags(),
                dependantChain,
                kryonDefinition.kryonDefinitionRegistry())));
    TreeSet<MainLogicDecorator> sortedDecorators =
        new TreeSet<>(logicDecorationOrdering.decorationOrder());
    sortedDecorators.addAll(decorators.values());
    return sortedDecorators;
  }
}
