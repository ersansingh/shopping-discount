package com.cdk.rule;

import com.cdk.model.Cart;
import com.cdk.model.Customer;
import com.cdk.model.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cdk.Literals.roundedAmount;
import static com.cdk.model.Customer.Type.PREMIUM;
import static com.cdk.model.Customer.Type.REGULAR;

@RunWith(JUnit4.class)
public class DiscountRuleEngineTest {
    private DiscountRuleEngine discountRuleEngine = new DiscountRuleEngine();
    //totalPurchaseAmount = (7889.95 + 688.5 + 8789.15) = 17367.6
    private static List<Item> items  = Arrays.asList(Item.builder().name("A").description("A desc").price(new BigDecimal(7889.95)).build(),
            Item.builder().name("B").description("C desc").price(new BigDecimal( 688.5)).build(),
            Item.builder().name("C").description("C desc").price(new BigDecimal(8789.15)).build()
            );
    private static Cart cart = Cart.builder().cartId(1234l).items(items).build();
    private static Customer customer = Customer.builder().name("sanjay").emailId("ersansingh1984@gmail.com").mobileNo("9818122455").cart(cart).build();

    @Test
    public void calculateDiscount_regularCustomer(){
        Customer input = customer.toBuilder().type(REGULAR).build();
        BigDecimal expected = roundedAmount.apply(new BigDecimal(1973.52));
        BigDecimal result = discountRuleEngine.process(input);
        Assert.assertTrue(roundedAmount.apply(result).equals(expected));
    }

    @Test
    public void calculateDiscount_premiumCustomer(){
        Customer input = customer.toBuilder().type(PREMIUM).build();
        BigDecimal expected = roundedAmount.apply(new BigDecimal(3410.28));
        BigDecimal result = discountRuleEngine.process(input);
        Assert.assertTrue(roundedAmount.apply(result).equals(expected));

    }

    @Test(expected = NullPointerException.class)
    public void calculateDiscount_customerTypeNull(){
        discountRuleEngine.process(customer);
    }

    @Test
    public void calculateDiscount_customerCartNull() {
        Customer input = customer.toBuilder().type(PREMIUM).cart(null).build();
        BigDecimal expected = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal result = discountRuleEngine.process(input);
        Assert.assertTrue(roundedAmount.apply(result).equals(expected));
    }

    @Test
    public void calculateDiscount_customerCartEmpty() {
        Customer input = customer.toBuilder().type(PREMIUM).cart(cart.toBuilder().items(Collections.emptyList()).build()).build();
        BigDecimal expected = roundedAmount.apply(BigDecimal.ZERO);
        BigDecimal result = discountRuleEngine.process(input);
        Assert.assertTrue(roundedAmount.apply(result).equals(expected));
    }

}
