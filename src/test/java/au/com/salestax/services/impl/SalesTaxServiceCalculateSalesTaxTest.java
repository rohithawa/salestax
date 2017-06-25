package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductPrice;
import au.com.salestax.model.ProductType;
import au.com.salestax.services.SalesTaxService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 2/3/17.
 * Test - Sales tax calculate service, calculateSalesTaxByProducts method
 */
@RunWith(Parameterized.class)
public class SalesTaxServiceCalculateSalesTaxTest {
    @Parameterized.Parameter
    public Product[] value;
    @Parameterized.Parameter(1)
    public BigDecimal expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static SalesTaxService salesTaxService;

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
                {new Product[]{product1, product2, product3}, new BigDecimal("29.83"), null},
                {new Product[]{product4, product5}, new BigDecimal("65.15"), null},
                {new Product[]{product6, product7, product8, product9}, new BigDecimal("74.68"), null},
                {new Product[]{}, null, SalesTaxServicesException.class},
                {null, null, SalesTaxServicesException.class}
        });
    }

    @Test
    public void testCalculateSalesTaxByProducts() throws SalesTaxServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        BigDecimal calculatedValue = new BigDecimal("0");
        List<ProductPrice> productPrices = salesTaxService.calculateSalesTaxByProducts((value == null) ? null :
                Arrays.asList(value));
        if (productPrices != null) {
            BigDecimal totalTax = productPrices.stream().map(ProductPrice::getTax).reduce(
                    BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalPrice = productPrices.stream().map(productPrice -> productPrice.getProduct().getCost())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            calculatedValue = calculatedValue.add(totalTax).add(totalPrice);
        }
        Assert.assertEquals(expectedResult, calculatedValue);
    }
}