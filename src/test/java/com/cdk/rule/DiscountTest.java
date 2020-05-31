package com.cdk.rule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static com.cdk.Literals.BigDecimalConstants.*;
import static com.cdk.Literals.roundedAmount;

@RunWith(JUnit4.class)
public class DiscountTest {
    @Test
    public void add(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(11550.78));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(810.156));
        Discount discount = DiscountRules.regularTenPercentDiscount.add(DiscountRules.regularTwentyPercentDiscount);

        BigDecimal result = discount.apply(inputAmount);

        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void discountValue(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(5000.58));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(750.087));

        BigDecimal result =  Discount.discountValue.apply(inputAmount, FIFTEEN_PERCENT);

        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void slabAmount(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(9820.98));
        BigDecimal expected = roundedAmount.apply(new BigDecimal(4820.98));

        BigDecimal result = Discount.slabAmount(inputAmount, FIVE_THOUSAND, TEN_THOUSAND);

        Assert.assertTrue(roundedAmount.apply(result).equals(expected));
    }
}
