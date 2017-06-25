package au.com.salestax.model;

import java.math.BigDecimal;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Product information
 */
public class Product {
    private final ProductType productType;
    private final String name;
    private final BigDecimal cost;
    private final boolean isImported;

    public Product(ProductType productType, String name, BigDecimal cost, boolean isImported) {
        this.productType = productType;
        this.name = name;
        this.cost = cost;
        this.isImported = isImported;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public boolean getIsImported() {
        return isImported;
    }
}
