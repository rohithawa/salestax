package au.com.salestax.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Rohitha Wanni Achchige on 1/3/17.
 * Rounded up to the nearest given factor
 */
public class RoundToNearestValue {

    /**
     * Rounding up by given value and rounding factor.
     *
     * @param value          value to round up
     * @param roundingFactor rounding up factor
     * @return BigDecimal rounded value
     */
    public static BigDecimal roundUp(BigDecimal value, BigDecimal roundingFactor) {
        if (value == null || value.doubleValue() <= 0 || roundingFactor == null || roundingFactor.doubleValue() <= 0) {
            return value;
        }
        value = value.divide(roundingFactor, MathContext.DECIMAL32);
        value = new BigDecimal(Math.ceil(value.doubleValue()));
        value = value.multiply(roundingFactor);
        return value;
    }
}
