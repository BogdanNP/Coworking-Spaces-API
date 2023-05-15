package com.example.demo.lab10;

public abstract class Account {
    private long accountNo;
    private String accountHolderName;
    private AccountType accountType;
    private String interestStrategy;
    private double amount;
    private InterestCalculationStrategy strategy;

    public Account(
        long accountNo, 
        String accountHolderName, 
        AccountType accountType) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        strategy = new SimpleCalculationStrategy();
    }

    abstract public double getInterest();
    
    public void deposit(double value){
        amount += value;
    }

    public void withdraw(double value){
        if (value > 0.0d && value < amount) {
            this.amount -= value;
        }
    }

    /**
     * @return long return the accountNo
     */
    public long getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return String return the accountHolderName
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * @param accountHolderName the accountHolderName to set
     */
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    /**
     * @return AccountType return the accountType
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @return String return the interestStrategy
     */
    public String getInterestStrategy() {
        return interestStrategy;
    }

    /**
     * @param interestStrategy the interestStrategy to set
     */
    public void setInterestStrategy(String interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    /**
     * @return double return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

     /**
     * @return InterestCalculationStrategy return the strategy
     */
    public InterestCalculationStrategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(InterestCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return 
        "account holder name=" + getAccountHolderName() + 
        ", account type=" + getAccountType() + 
        ", amount=" + getAmount() + 
        ", account no.=" + getAccountNo() +
        ", interest strategy =" + getInterestStrategy();
    }


   

}