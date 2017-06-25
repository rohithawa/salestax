package au.com.salestax;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.Product;
import au.com.salestax.model.ProductType;
import au.com.salestax.services.PrintReceiptService;
import au.com.salestax.services.SalesTaxService;
import au.com.salestax.services.impl.CalculateServiceImpl;
import au.com.salestax.services.impl.PrintReceiptServiceImpl;
import au.com.salestax.services.impl.SalesTaxServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 28/2/17.
 *
 */
public class MainApp {
    public static void main(String[] args) {
        // Sales Tax
        System.out.println("Sales Tax------------------");
        System.out.println("Input 1:");
        try {
            SalesTaxService salesTaxService = new SalesTaxServiceImpl();
            PrintReceiptService printReceiptService = new PrintReceiptServiceImpl(new CalculateServiceImpl());
            List<Product> products = new ArrayList<>();
            products.add(new Product(ProductType.BOOK, "book", new BigDecimal("12.49"), false));
            products.add(new Product(ProductType.OTHER, "music CD", new BigDecimal("14.99"), false));
            products.add(new Product(ProductType.FOOD, "chocolate bar", new BigDecimal("0.85"), false));
            printReceiptService.printByProducts(salesTaxService.calculateSalesTaxByProducts(products));
            System.out.println("");

            System.out.println("Input 2:");
            products = new ArrayList<>();
            products.add(new Product(ProductType.FOOD, "imported box of chocolates", new BigDecimal("10.00"), true));
            products.add(new Product(ProductType.OTHER, "imported bottle of perfume", new BigDecimal("47.50"), true));
            printReceiptService.printByProducts(salesTaxService.calculateSalesTaxByProducts(products));
            System.out.println("");

            System.out.println("Input 3:");
            products = new ArrayList<>();
            products.add(new Product(ProductType.OTHER, "imported bottle of perfume", new BigDecimal("27.99"), true));
            products.add(new Product(ProductType.OTHER, "bottle of perfume", new BigDecimal("18.99"), false));
            products.add(new Product(ProductType.MEDICAL, "packet of headache pills", new BigDecimal("9.75"), false));
            products.add(new Product(ProductType.FOOD, "imported box of chocolates", new BigDecimal("11.25"), true));
            printReceiptService.printByProducts(salesTaxService.calculateSalesTaxByProducts(products));
        } catch (SalesTaxServicesException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
