package au.com.salestax.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rohitha Wanni Achchige on 2/3/17.
 * Test - Rounded up to the nearest given factor
 */
@RunWith(Parameterized.class)
public class RoundToNearestValueTest {
    @Parameterized.Parameter
    public BigDecimal value;
    @Parameterized.Parameter(1)
    public BigDecimal roundingFactor;
    @Parameterized.Parameter(2)
    public BigDecimal expectedResult;

    @Parameterized.Parameters
    public static Collection roundUpCollection() {
        return Arrays.asList(new Object[][]{
                {new BigDecimal("3.43"), new BigDecimal("0.05"), new BigDecimal("3.45")},
                {new BigDecimal("1.57"), new BigDecimal("0.10"), new BigDecimal("1.60")},
                {new BigDecimal("1.67"), new BigDecimal("0.05"), new BigDecimal("1.70")},
                {new BigDecimal("1.67"), new BigDecimal("0.00"), new BigDecimal("1.67")},
                {new BigDecimal("1.67"), new BigDecimal("-0.00"), new BigDecimal("1.67")},
                {new BigDecimal("1.67"), null, new BigDecimal("1.67")},
                {new BigDecimal("0.00"), new BigDecimal("0.05"), new BigDecimal("0.00")},
                {new BigDecimal("-1.67"), new BigDecimal("0.05"), new BigDecimal("-1.67")},
                {null, new BigDecimal("0.05"), null}
        });
    }

    @Test
    public void testRoundUp() {
        Assert.assertEquals(expectedResult, RoundToNearestValue.roundUp(value, roundingFactor));
    }
}
