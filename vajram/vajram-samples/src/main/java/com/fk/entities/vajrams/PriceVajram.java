package com.fk.entities.vajrams;

import com.fk.entities.models.Price;
import com.flipkart.krystal.datatypes.StringType;
import com.flipkart.krystal.vajram.DefaultModulatedBlockingVajram;
import com.flipkart.krystal.vajram.ExecutionContext;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.RequestBuilder;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.VajramID;
import com.flipkart.krystal.vajram.VajramLogic;
import com.flipkart.krystal.vajram.inputs.Dependency;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;

@VajramDef("PriceVJ")
public class PriceVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(Dependency.builder().name("Pnp_price").dataAccessSpec(VajramID.vajramID("PnPVJ")).isMandatory().build());
    }

    @Override
    public ImmutableList<RequestBuilder<?>> resolveInputOfDependency(String dependency, ImmutableSet resolvableInputs,
            ExecutionContext executionContext) {
        return null;
    }

    @VajramLogic
    public Price getPrice(Map<String, Object> pnpResponse) {
        Price price = new Price();
        price.deserialize(pnpResponse);

        return price;
    }

    @Override
    public ImmutableList executeNonBlocking(ExecutionContext executionContext) {
        return null;
    }
}
