package com.cdk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public interface Literals {
    class BigDecimalConstants{
        public static BigDecimal TEN_PERCENT = new BigDecimal(0.1);
        public static BigDecimal FIFTEEN_PERCENT = new BigDecimal(0.15);
        public static BigDecimal TWENTY_PERCENT = new BigDecimal(0.2);
        public static BigDecimal THIRTY_PERCENT = new BigDecimal(0.3);
        public static BigDecimal FOUR_THOUSAND = new BigDecimal(4000.0);
        public static BigDecimal FIVE_THOUSAND = new BigDecimal(5000.0);
        public static BigDecimal EIGHT_THOUSAND = new BigDecimal(8000.0);
        public static BigDecimal TWELVE_THOUSAND = new BigDecimal(12000.0);
        public static BigDecimal TEN_THOUSAND = new BigDecimal(10000.0);
    }

    Function<BigDecimal, BigDecimal> roundedAmount = (amount) -> amount.setScale(2, RoundingMode.HALF_DOWN);
}
