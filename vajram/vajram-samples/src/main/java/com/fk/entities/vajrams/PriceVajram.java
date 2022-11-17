package com.fk.entities.vajrams;

import com.fk.entities.models.Price;
import com.flipkart.krystal.datatypes.StringType;
import com.flipkart.krystal.vajram.DefaultModulatedBlockingVajram;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.VajramID;
import com.flipkart.krystal.vajram.VajramLogic;
import com.flipkart.krystal.vajram.inputs.Dependency;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import java.util.List;
import java.util.Map;

@VajramDef("PriceVJ")
public class PriceVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(Dependency.builder().name("Pnp_price").dependencySpec(VajramID.vajramID("PnPVJ")).isMandatory().build());
    }

    @VajramLogic
    public Price getPrice(Map<String, Object> pnpResponse) {
        Price price = new Price();
        price.deserialize(pnpResponse);

        return price;
    }
}
