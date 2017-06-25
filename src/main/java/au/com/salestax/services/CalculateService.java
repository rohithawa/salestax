package au.com.salestax.services;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.ProductPrice;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rohitha on 2/03/2017.
 * Calculate service
 */
public interface CalculateService {
    /**
     * Calculate total tax.
     *
     * @param productPriceList list of products to print receipt
     * @return BigDecimal total tax
     */
    BigDecimal calculateTotalTaxByProducts(List<ProductPrice> productPriceList) throws SalesTaxServicesException;

    /**
     * Calculate total cost.
     *
     * @param productPriceList list of products to print receipt
     * @return BigDecimal total cost
     */
    BigDecimal calculateTotalCostByProducts(List<ProductPrice> productPriceList) throws SalesTaxServicesException;
}
