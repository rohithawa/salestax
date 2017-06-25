package au.com.salestax.services;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.ProductPrice;

import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Print receipt service
 */
public interface PrintReceiptService {

    /**
     * Print receipt for given products.
     *
     * @param productPriceList list of products to print receipt
     */
    boolean printByProducts(List<ProductPrice> productPriceList) throws SalesTaxServicesException;
}
