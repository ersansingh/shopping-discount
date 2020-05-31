package com.cdk.rule;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * Functional Interface having functions to calculate discount
 */
public interface Discount extends UnaryOperator<BigDecimal>{
    /**
     * @param after is function to be combined
     * @return function which adds result of both discount function
     */
    default Discount add(Discount after) {
        return value -> after.apply(value).add(this.apply(value));
    }

    /**
     * function takes amount and discount percentage(fraction value i.e. 10% = 0.1), returns discount value
     */
    BiFunction<BigDecimal, BigDecimal, BigDecimal> discountValue = (amount, discountFraction) -> amount.multiply(discountFraction);

    /**
     * @param amount whose slab to be calculated
     * @param lowerSlab lower bound of slab
     * @param upperSlab upper bound of slab
     * @return amount between lower and upper slab
     */
    static BigDecimal slabAmount (BigDecimal amount, BigDecimal lowerSlab, BigDecimal upperSlab){
        Objects.requireNonNull(lowerSlab);
        Objects.requireNonNull(upperSlab);
        Objects.requireNonNull(amount);
        if(amount.compareTo(lowerSlab) <= 0) return BigDecimal.ZERO;
        if(amount.compareTo(upperSlab) > 0) return upperSlab.subtract(lowerSlab);
        return amount.subtract(lowerSlab);
    }
}