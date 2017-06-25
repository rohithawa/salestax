package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductPrice;
import au.com.salestax.model.ProductType;
import au.com.salestax.services.SalesTaxService;
import au.com.salestax.util.Constants;
import au.com.salestax.util.RoundToNearestValue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Sales tax calculate service implementation
 */
public class SalesTaxServiceImpl implements SalesTaxService {

    /**
     * Calculate sales tax.
     *
     * @param products list of products to calculate tax
     * @return List<ProductPrice> list product prices including tax
     */
    @Override
    public List<ProductPrice> calculateSalesTaxByProducts(List<Product> products) throws SalesTaxServicesException {
        if (products == null || products.isEmpty()) {
            throw new SalesTaxServicesException("Empty or null Product list");
        }
        return products.stream().map(product -> calculateTaxByProductAndPercentage(product,
                calculateTaxPercentageByProduct(product))).collect(Collectors.toList());
    }

    /**
     * Calculate tax percentage by given product
     *
     * @param product product to calculate tax percentage
     * @return BigDecimal percentage apply for product
     */
    protected BigDecimal calculateTaxPercentageByProduct(Product product) {
        BigDecimal taxPercentage;
        if (product.getProductType().equals(ProductType.BOOK) || product.getProductType().equals(ProductType.FOOD) ||
                product.getProductType().equals(ProductType.MEDICAL)) {
            taxPercentage = BigDecimal.ZERO;
        } else {
            taxPercentage = Constants.TAX_PERCENTAGE;
        }

        if (product.getIsImported()) {
            taxPercentage = taxPercentage.add(Constants.ADDITIONAL_IMPORTED_TAX_PERCENTAGE);
        }
        return taxPercentage;
    }

    /**
     * Calculate tax by given product and tax percentage.
     *
     * @param product       product to calculate tax
     * @param taxPercentage percentage to calculate tax
     * @return ProductPrice product with tax percentage
     */
    protected ProductPrice calculateTaxByProductAndPercentage(Product product, BigDecimal taxPercentage) {
        BigDecimal salesTax = product.getCost().multiply(taxPercentage).divide(new BigDecimal("100"), MathContext
                .DECIMAL32);
        salesTax = RoundToNearestValue.roundUp(salesTax, Constants.ROUNDING_FACTOR);
        return new ProductPrice(product, salesTax, 1);
    }
}
