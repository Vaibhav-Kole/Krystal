package com.fk.entities.vajrams;

import com.fk.entities.models.Listing;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.VajramID;
import com.flipkart.krystal.vajram.VajramLogic;
import com.flipkart.krystal.vajram.inputs.Dependency;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import java.util.List;
import java.util.Map;

@VajramDef("ListingZuluVJ")
public class ListingZuluFacetVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(
                Dependency.builder().name("zulu_listing").dependencySpec(VajramID.vajramID("ZuluVJ")).isMandatory().build());
    }

    @VajramLogic
    public Listing getListing(String pid, String lid,  Map<String, Object>zuluResponse) {
        // Entity holder can be used to fetch already initialized entity
        Listing listing = new Listing(pid, lid);
        listing.deserializeZuluResponse(zuluResponse);

        return listing;
    }
}
