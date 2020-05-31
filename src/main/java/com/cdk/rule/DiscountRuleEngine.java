package com.cdk.rule;

import com.cdk.model.Cart;
import com.cdk.model.Customer;
import com.cdk.model.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cdk.model.Customer.Type.PREMIUM;
import static com.cdk.model.Customer.Type.REGULAR;
import static com.cdk.rule.DiscountRules.*;

/**
 * Discount rule engine calculates discount on customer's shopping cart total amount
 */
public class DiscountRuleEngine implements RuleEngine<Customer, BigDecimal>  {
    private Map<Customer.Type, Discount> discountByCustomerType = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>(PREMIUM, premiumCustomerDiscount),
            new AbstractMap.SimpleImmutableEntry<>(REGULAR, regularCustomerDiscount))
            .collect(Collectors.collectingAndThen(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue), Collections::unmodifiableMap));

    @Override
    public BigDecimal process(Customer customer) {
        Objects.requireNonNull(customer);
        Objects.requireNonNull(customer.getType());

        List<Item> items = Optional.of(customer)
                .map(Customer::getCart)
                .map(Cart::getItems).orElse(Collections.emptyList());

        BigDecimal totalAmount = items.stream()
                .map(Item::getPrice)
                .filter(Objects::nonNull)
                .reduce((p1, p2) -> p1.add(p2)).orElse(BigDecimal.ZERO);

        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) return BigDecimal.ZERO;
        return discountByCustomerType.get(customer.getType()).apply(totalAmount);
    }
}
