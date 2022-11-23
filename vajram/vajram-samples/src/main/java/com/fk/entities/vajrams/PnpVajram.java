package com.fk.entities.vajrams;

import com.flipkart.krystal.datatypes.StringType;
import com.flipkart.krystal.vajram.DefaultModulatedBlockingVajram;
import com.flipkart.krystal.vajram.ExecutionContext;
import com.flipkart.krystal.vajram.RequestBuilder;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.inputs.Input;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@VajramDef("PnPVJ")
public class PnpVajram extends DefaultModulatedBlockingVajram {
    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(Input.builder().name("pid").type(StringType.string()).mandatory().build(),
                Input.builder().name("lid").type(StringType.string()).mandatory().build());
    }

    @Override
    public CompletableFuture<ImmutableList> execute(ExecutionContext executionContext) {
        return null;
    }

    @Override
    public ImmutableList<RequestBuilder<?>> resolveInputOfDependency(String dependency, ImmutableSet resolvableInputs,
            ExecutionContext executionContext) {
        return null;
    }

}
