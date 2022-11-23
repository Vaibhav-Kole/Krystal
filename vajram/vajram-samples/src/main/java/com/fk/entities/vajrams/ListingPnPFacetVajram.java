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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;

@VajramDef("ListingPnPVJ")
public class ListingPnPFacetVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return List.of(
                Dependency.builder().name("pnp_listing").dataAccessSpec(VajramID.vajramID("PnPVJ")).isMandatory().build());
    }

    @Override
    public ImmutableList<RequestBuilder<?>> resolveInputOfDependency(String dependency, ImmutableSet resolvableInputs,
            ExecutionContext executionContext) {
        return null;
    }

    @VajramLogic
    public Listing getListing(String pid, String lid,  Map<String, Object> pnpResponse) {
        // Entity holder can be used to fetch already initialized entity
        Listing listing = new Listing(pid, lid);
        listing.deserializePnPResponse(pnpResponse);

        return listing;
    }

    @Override
    public ImmutableList executeNonBlocking(ExecutionContext executionContext) {
        return null;
    }
}
