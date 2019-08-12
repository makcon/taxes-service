package test.maksim.taxes.domain.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class NumberUtils {

    public double scale(double value,
                        int scale) {
        return new BigDecimal(Double.toString(value))
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double scale(double value) {
        return scale(value, 2);
    }

    public double roundUpTo(double nearest,
                            double value) {
        return scale(Math.ceil(value / nearest) * nearest);
    }
}
