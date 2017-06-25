package au.com.salestax.exceptions;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Exception handling
 */
public class SalesTaxServicesException extends Exception {
    private final String errorMessage;

    /**
     * SalesTaxServicesException constructor.
     *
     * @param errorMessage errorMessage
     */
    public SalesTaxServicesException(final String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
