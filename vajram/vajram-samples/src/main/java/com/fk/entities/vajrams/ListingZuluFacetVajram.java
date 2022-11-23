package com.fk.entities.vajrams;

import com.fk.entities.models.Listing;
import com.flipkart.krystal.vajram.ExecutionContext;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.RequestBuilder;
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
                Dependency.builder().name("zulu_listing").dataAccessSpec(VajramID.vajramID("ZuluVJ")).isMandatory().build());
    }

    @Override
    public com.google.common.collect.ImmutableList<RequestBuilder<?>> resolveInputOfDependency(String dependency,
            com.google.common.collect.ImmutableSet resolvableInputs, ExecutionContext executionContext) {
        return null;
    }

    @VajramLogic
    public Listing getListing(String pid, String lid,  Map<String, Object>zuluResponse) {
        // Entity holder can be used to fetch already initialized entity
        Listing listing = new Listing(pid, lid);
        listing.deserializeZuluResponse(zuluResponse);

        return listing;
    }

    @Override
    public com.google.common.collect.ImmutableList executeNonBlocking(ExecutionContext executionContext) {
        return null;
    }
}
