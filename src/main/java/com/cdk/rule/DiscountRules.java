package com.cdk.rule;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.cdk.Literals.BigDecimalConstants.*;
import static com.cdk.rule.Discount.discountValue;
import static com.cdk.rule.Discount.slabAmount;

/**
 * Discount rules generated using {@link Discount}
 */
public interface DiscountRules {
    Discount regularTenPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, FIVE_THOUSAND, TEN_THOUSAND);
        return discountValue.apply(slabAmount, TEN_PERCENT);
    };

    Discount regularTwentyPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, TEN_THOUSAND, BigDecimal.valueOf(Double.MAX_VALUE));
        return discountValue.apply(slabAmount, TWENTY_PERCENT);
    };

    Discount premiumTenPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, BigDecimal.ZERO, FOUR_THOUSAND);
        return discountValue.apply(slabAmount, TEN_PERCENT);
    };

    Discount premiumFifteenPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, FOUR_THOUSAND, EIGHT_THOUSAND);
        return discountValue.apply(slabAmount, FIFTEEN_PERCENT);
    };

    Discount premiumTwentyPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, EIGHT_THOUSAND, TWELVE_THOUSAND);
        return discountValue.apply(slabAmount, TWENTY_PERCENT);
    };

    Discount premiumThirtyPercentDiscount = amount -> {
        BigDecimal slabAmount = slabAmount(amount, TWELVE_THOUSAND, BigDecimal.valueOf(Double.MAX_VALUE));
        return discountValue.apply(slabAmount, THIRTY_PERCENT);
    };

    /**
     * Aggregation of regular customer discount rules
     */
    Discount regularCustomerDiscount = Stream.of(regularTenPercentDiscount, regularTwentyPercentDiscount)
            .reduce(Discount::add)
            .get();

    /**
     * Aggregation of premium customer discount rules
     */
    Discount premiumCustomerDiscount = Stream.of(premiumTenPercentDiscount, premiumFifteenPercentDiscount,
            premiumTwentyPercentDiscount, premiumThirtyPercentDiscount)
            .reduce(Discount::add)
            .get();
}
