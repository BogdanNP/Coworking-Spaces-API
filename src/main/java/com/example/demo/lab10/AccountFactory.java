package com.example.demo.lab10;

public class AccountFactory {
    public static Account createAccount(long accountNo, String accountHolderName, String accountType) {
        Account account = null;
        AccountType type = AccountType.valueOf(accountType);
        if(type == AccountType.CURRENT){
            account = new CurrentAccount(accountNo, accountHolderName);
        }
        if(type == AccountType.SAVING){
            account = new SavingAccount(accountNo, accountHolderName);
        }
        return account;            
    }
}