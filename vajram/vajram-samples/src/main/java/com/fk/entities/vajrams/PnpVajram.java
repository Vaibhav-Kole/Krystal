package com.fk.entities.vajrams;

import com.flipkart.krystal.datatypes.StringType;
import com.flipkart.krystal.vajram.DefaultModulatedBlockingVajram;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.inputs.Input;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import java.util.List;

@VajramDef("PnPVJ")
public class PnpVajram extends DefaultModulatedBlockingVajram {
    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(Input.builder().name("pid").type(StringType.string()).isMandatory().build(),
                Input.builder().name("lid").type(StringType.string()).isMandatory().build());
    }

}
