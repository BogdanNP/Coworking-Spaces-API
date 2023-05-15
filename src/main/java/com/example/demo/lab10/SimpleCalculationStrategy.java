package com.example.demo.lab10;

public class SimpleCalculationStrategy implements InterestCalculationStrategy{

    @Override
    public double calculateInterest(double principal, double rate, int term) {
        return 1.0;
    }
    
}
