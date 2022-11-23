package com.fk.entities.vajrams;

import com.flipkart.krystal.vajram.DefaultModulatedBlockingVajram;
import com.flipkart.krystal.vajram.ExecutionContext;
import com.flipkart.krystal.vajram.RequestBuilder;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@VajramDef("ZuluVJ")
public class ZuluVajram extends DefaultModulatedBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return null;
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
