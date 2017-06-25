package au.com.salestax.model;

import java.math.BigDecimal;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Product price information
 */
public class ProductPrice {
    private final Product product;
    private final BigDecimal tax;
    private final int quantity;

    public ProductPrice(Product product, BigDecimal tax, int quantity) {
        this.product = product;
        this.tax = tax;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public int getQuantity() {
        return quantity;
    }
}
