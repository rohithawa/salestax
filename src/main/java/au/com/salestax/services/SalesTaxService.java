package au.com.salestax.services;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductPrice;

import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Sales tax calculate service
 */
public interface SalesTaxService {
    /**
     * Calculate sales tax.
     *
     * @param products list of products to calculate tax
     * @return List<ProductPrice> list product prices including tax
     */
    List<ProductPrice> calculateSalesTaxByProducts(List<Product> products) throws SalesTaxServicesException;
}
