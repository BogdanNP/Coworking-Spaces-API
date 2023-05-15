package com.example.demo.lab10;

public class CompoundCalculationStrategy implements InterestCalculationStrategy {
    @Override
    public double calculateInterest(double principal, double rate, int term) {
        return 2.0;
    }
}
