package com.cdk.rule;

public interface RuleEngine<T, R> {
    R process(T input);
}
