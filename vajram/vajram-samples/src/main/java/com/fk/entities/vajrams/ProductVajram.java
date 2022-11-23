package com.fk.entities.vajrams;

import com.fk.entities.models.Product;
import com.flipkart.krystal.vajram.ExecutionContext;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.RequestBuilder;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.VajramLogic;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;

@VajramDef("ProductVJ")
public class ProductVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return null;
    }

    @Override
    public ImmutableList<RequestBuilder<?>> resolveInputOfDependency(String dependency, ImmutableSet resolvableInputs,
            ExecutionContext executionContext) {
        return null;
    }

    @VajramLogic
    public Product getProduct(String pid, Map<String, Object>zuluResponse) {
        Product product = new Product(pid);
        product.deserialize(zuluResponse);

        return product;
    }

    @Override
    public ImmutableList executeNonBlocking(ExecutionContext executionContext) {
        return null;
    }
}
