package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductPrice;
import au.com.salestax.model.ProductType;
import au.com.salestax.services.CalculateService;
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

/**
 * Created by rohitha on 2/03/2017.
 * Test - Calculate service
 */
@RunWith(Parameterized.class)
public class CalculateServiceImplTest {
    @Parameterized.Parameter
    public ProductPrice[] value;
    @Parameterized.Parameter(1)
    public BigDecimal expectedTaxResult;
    @Parameterized.Parameter(2)
    public BigDecimal expectedTotalResult;
    @Parameterized.Parameter(3)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static CalculateService calculateService;

    @BeforeClass
    public static void init() {
        calculateService = new CalculateServiceImpl();
    }

    @Parameterized.Parameters
    public static Collection productCollection() {
        ProductPrice product1 = new ProductPrice(new Product(ProductType.BOOK, "book", new BigDecimal("12.49"),
                false), BigDecimal.ZERO, 1);
        ProductPrice product2 = new ProductPrice(new Product(ProductType.OTHER, "music CD", new BigDecimal("14.99"),
                false), new BigDecimal("1.50"), 1);
        ProductPrice product3 = new ProductPrice(new Product(ProductType.FOOD, "chocolate bar", new BigDecimal("0" +
                ".85"), false), BigDecimal.ZERO, 1);

        ProductPrice product4 = new ProductPrice(new Product(ProductType.FOOD, "imported box of chocolates", new
                BigDecimal("10.00"), true), new BigDecimal("0.50"), 1);
        ProductPrice product5 = new ProductPrice(new Product(ProductType.OTHER, "imported bottle of perfume", new
                BigDecimal("47.50"), true), new BigDecimal("7.15"), 1);

        ProductPrice product6 = new ProductPrice(new Product(ProductType.OTHER, "imported bottle of perfume", new
                BigDecimal("27.99"), true), new BigDecimal("4.20"), 1);
        ProductPrice product7 = new ProductPrice(new Product(ProductType.OTHER, "bottle of perfume", new BigDecimal
                ("18.99"), false), new BigDecimal("1.90"), 1);
        ProductPrice product8 = new ProductPrice(new Product(ProductType.MEDICAL, "packet of headache pills", new
                BigDecimal("9.75"), false), BigDecimal.ZERO, 1);
        ProductPrice product9 = new ProductPrice(new Product(ProductType.FOOD, "imported box of chocolates", new
                BigDecimal("11.25"), true), new BigDecimal("0.60"), 1);
        return Arrays.asList(new Object[][]{
                {new ProductPrice[]{product1, product2, product3}, new BigDecimal("1.50"), new BigDecimal("28.33"),
                        null},
                {new ProductPrice[]{product4, product5}, new BigDecimal("7.65"), new BigDecimal("57.50"), null},
                {new ProductPrice[]{product6, product7, product8, product9}, new BigDecimal("6.70"), new BigDecimal
                        ("67.98"), null},
                {new ProductPrice[]{}, null, null, SalesTaxServicesException.class},
                {null, null, null, SalesTaxServicesException.class}
        });
    }

    @Test
    public void testCalculateTotalTaxByProducts() throws SalesTaxServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        Assert.assertEquals(expectedTaxResult, calculateService.calculateTotalTaxByProducts((value == null) ? null :
                Arrays.asList(value)));
    }

    @Test
    public void testCalculateTotalCostByProducts() throws SalesTaxServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        Assert.assertEquals(expectedTotalResult, calculateService.calculateTotalCostByProducts((value == null) ? null
                : Arrays.asList(value)));
    }
}
