package au.com.salestax.services.impl;

import au.com.salestax.exceptions.SalesTaxServicesException;
import au.com.salestax.model.ProductPrice;
import au.com.salestax.services.CalculateService;
import au.com.salestax.services.PrintReceiptService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Print receipt service implementation
 */
public class PrintReceiptServiceImpl implements PrintReceiptService {
    private final CalculateService calculateService;

    public PrintReceiptServiceImpl(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    /**
     * Print receipt for given products.
     *
     * @param productPriceList list of products to print receipt
     */
    @Override
    public boolean printByProducts(List<ProductPrice> productPriceList) throws SalesTaxServicesException {
        if (productPriceList != null && !productPriceList.isEmpty()) {
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            BigDecimal totalTax = calculateService.calculateTotalTaxByProducts(productPriceList);
            BigDecimal totalPrice = calculateService.calculateTotalCostByProducts(productPriceList).add(totalTax);
            for (ProductPrice productPrice : productPriceList) {
                BigDecimal shelfPrice = productPrice.getProduct().getCost();
                shelfPrice = shelfPrice.add(productPrice.getTax());
                System.out.println(productPrice.getQuantity() + " " + productPrice.getProduct().getName() + ": " +
                        decimalFormat.format(shelfPrice.doubleValue()));
            }
            System.out.println("Sales Taxes: " + decimalFormat.format(totalTax.doubleValue()));
            System.out.println("Total: " + decimalFormat.format(totalPrice.doubleValue()));
            return true;
        } else {
            throw new SalesTaxServicesException("Empty or null Product list");
        }
    }
}
