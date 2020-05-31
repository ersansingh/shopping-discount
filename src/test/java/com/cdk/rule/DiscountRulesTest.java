package com.cdk.rule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static com.cdk.Literals.BigDecimalConstants.*;
import static com.cdk.Literals.roundedAmount;

@RunWith(JUnit4.class)
public class DiscountRulesTest {
    @Test(expected = NullPointerException.class)
    public void regularCustomerDiscount_amountNull(){
        DiscountRules.regularCustomerDiscount.apply(null);
    }

    @Test
    public void regularCustomerDiscount_amountLessThanEqualTo5k(){
        BigDecimal inputAmount = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal expectedDiscount = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(4001.78));
        result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(5000));
        result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void regularCustomerDiscount_amountBetween5kAnd10K(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(7856.85));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(285.685));
        BigDecimal result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(10000));
        expectedDiscount = roundedAmount.apply(new BigDecimal(500));
        result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void regularCustomerDiscount_amountAbove10K(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(17856.85));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(2071.37));
        BigDecimal result = DiscountRules.regularCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test(expected = NullPointerException.class)
    public void premiumCustomerDiscount_amountNull(){
        DiscountRules.premiumCustomerDiscount.apply(null);
    }

    @Test
    public void premiumCustomerDiscount_amountLessThanEqualTo4k(){
        BigDecimal inputAmount = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal expectedDiscount = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(3001.78));
        result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        expectedDiscount = roundedAmount.apply(new BigDecimal(300.178));
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(4000));
        expectedDiscount = roundedAmount.apply(new BigDecimal(400));
        result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void premiumCustomerDiscount_amountBetween4kAnd8K(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(7856.85));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(978.5275));
        BigDecimal result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(8000));
        expectedDiscount = roundedAmount.apply(new BigDecimal(1000));
        result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void premiumCustomerDiscount_amountBetween8kAnd12K(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(10856.85));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(1571.37));
        BigDecimal result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));

        inputAmount = roundedAmount.apply(new BigDecimal(12000));
        expectedDiscount = roundedAmount.apply(new BigDecimal(1800));
        result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }

    @Test
    public void premiumCustomerDiscount_amountAbove12K(){
        BigDecimal inputAmount = roundedAmount.apply(new BigDecimal(17856.85));
        BigDecimal expectedDiscount = roundedAmount.apply(new BigDecimal(3557.055));
        BigDecimal result = DiscountRules.premiumCustomerDiscount.apply(inputAmount);
        Assert.assertTrue(roundedAmount.apply(result).equals(expectedDiscount));
    }
}
