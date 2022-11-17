package com.fk.entities.vajrams;

import com.fk.entities.models.Product;
import com.flipkart.krystal.vajram.NonBlockingVajram;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.VajramLogic;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import java.util.List;
import java.util.Map;

@VajramDef("ProductVJ")
public class ProductVajram extends NonBlockingVajram {

    @Override
    public List<VajramInputDefinition> getInputDefinitions() {
        return null;
    }

    @VajramLogic
    public Product getProduct(String pid, Map<String, Object>zuluResponse) {
        Product product = new Product(pid);
        product.deserialize(zuluResponse);

        return product;
    }
}
