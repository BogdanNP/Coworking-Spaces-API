package com.example.demo.lab10;

public class SavingAccount extends Account {
    public SavingAccount(long accountNo, String accountHolderName) {
        super(accountNo, accountHolderName, AccountType.SAVING);
        setInterestStrategy("Compound Interest");
    }

    @Override
    public double getInterest() {
        return this.getStrategy().calculateInterest(getAmount(), getAccountNo(), 0);
    } 

    @Override
    public String toString() {
        return super.toString() + ", interest=" + getInterest();
    }
}