package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rohitha Wanni Achchige on 2/3/17.
 * Test - Sales tax calculate service, calculateTaxByProductAndPercentage method
 */
@RunWith(Parameterized.class)
public class SalesTaxServiceCalculateTaxTest {
    @Parameterized.Parameter
    public Product value;
    @Parameterized.Parameter(1)
    public BigDecimal taxPercentage;
    @Parameterized.Parameter(2)
    public BigDecimal expectedResult;

    private static SalesTaxServiceImpl salesTaxService;

    @BeforeClass
    public static void init() {
        salesTaxService = new SalesTaxServiceImpl();
    }

    @Parameterized.Parameters
    public static Collection productCollection() {
        Product product1 = new Product(ProductType.BOOK, "book", new BigDecimal("12.49"), false);
        Product product2 = new Product(ProductType.OTHER, "music CD", new BigDecimal("14.99"), false);
        Product product3 = new Product(ProductType.FOOD, "chocolate bar", new BigDecimal("0.85"), false);
        Product product4 = new Product(ProductType.FOOD, "imported box of chocolates", new BigDecimal("10.00"), true);
        Product product5 = new Product(ProductType.OTHER, "imported bottle of perfume", new BigDecimal("47.50"), true);
        Product product6 = new Product(ProductType.OTHER, "imported bottle of perfume", new BigDecimal("27.99"), true);
        Product product7 = new Product(ProductType.OTHER, "bottle of perfume", new BigDecimal("18.99"), false);
        Product product8 = new Product(ProductType.MEDICAL, "packet of headache pills", new BigDecimal("9.75"), false);
        Product product9 = new Product(ProductType.FOOD, "imported box of chocolates", new BigDecimal("11.25"), true);
        return Arrays.asList(new Object[][]{
                {product1, BigDecimal.ZERO, new BigDecimal("0.00")},
                {product2, new BigDecimal("10"), new BigDecimal("1.50")},
                {product3, BigDecimal.ZERO, new BigDecimal("0.00")},
                {product4, new BigDecimal("5"), new BigDecimal("0.50")},
                {product5, new BigDecimal("15"), new BigDecimal("7.15")},
                {product6, new BigDecimal("15"), new BigDecimal("4.20")},
                {product7, new BigDecimal("10"), new BigDecimal("1.90")},
                {product8, BigDecimal.ZERO, new BigDecimal("0.00")},
                {product9, new BigDecimal("5"), new BigDecimal("0.60")}
        });
    }

    @Test
    public void testCalculateTaxByProductAndPercentage() throws SalesTaxServicesException {
        Assert.assertEquals(expectedResult, salesTaxService.calculateTaxByProductAndPercentage(value, taxPercentage).getTax());
    }
}