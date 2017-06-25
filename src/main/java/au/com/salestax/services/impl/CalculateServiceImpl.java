package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.ProductPrice;
import au.com.salestax.services.CalculateService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rohitha on 2/03/2017.
 * Calculate service implementation
 */
public class CalculateServiceImpl implements CalculateService {
    /**
     * Calculate total tax.
     *
     * @param productPriceList list of products to print receipt
     * @return BigDecimal total tax
     */
    @Override
    public BigDecimal calculateTotalTaxByProducts(List<ProductPrice> productPriceList) throws
            SalesTaxServicesException {
        if (productPriceList != null && !productPriceList.isEmpty()) {
            return productPriceList.stream().map(ProductPrice::getTax).reduce(
                    BigDecimal.ZERO, BigDecimal::add);
        } else {
            throw new SalesTaxServicesException("Empty or null Product list");
        }
    }

    /**
     * Calculate total cost.
     *
     * @param productPriceList list of products to print receipt
     * @return BigDecimal total cost
     */
    @Override
    public BigDecimal calculateTotalCostByProducts(List<ProductPrice> productPriceList) throws
            SalesTaxServicesException {
        if (productPriceList != null && !productPriceList.isEmpty()) {
            return productPriceList.stream().map(productPrice -> productPrice.getProduct().getCost())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            throw new SalesTaxServicesException("Empty or null Product list");
        }
    }
}
