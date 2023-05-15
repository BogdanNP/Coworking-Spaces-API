package com.example.demo.lab10;

public class CurrentAccount extends Account {
    public CurrentAccount(long accountNo, String accountHolderName) {
        super(accountNo, accountHolderName, AccountType.CURRENT);
        setInterestStrategy("Simple Interest");
    }

    @Override
    public double getInterest() {
        return this.getStrategy().calculateInterest(getAmount(), getAccountNo(), 1);
    } 

    @Override
    public String toString() {
        return super.toString() + ", interest = " + getInterest();
    }
}