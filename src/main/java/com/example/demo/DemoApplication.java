package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lab10.Account;
import com.example.demo.lab10.AccountFactory;
import com.example.demo.lab10.CompoundCalculationStrategy;

@SpringBootApplication
@RestController
public class DemoApplication {
    // on Android app: instead of localhost use: 10.0.2.2
    // TODO: think about all the use cases and logic you need to add
    // * date-time should be in future
    // * we need a method to auto-update the desk reqests
    public static void main(String[] args) {
      // SpringApplication.run(DemoApplication.class, args);
      Account savingAccount = AccountFactory.createAccount(100, "SavingAccount", "SAVING");
      Account currentAccount = AccountFactory.createAccount(20, "CurrentAccount", "CURRENT");
      
      System.out.println("saving account simple interest: " +savingAccount.getInterest());
      System.out.println("current account simple interest: " +currentAccount.getInterest());
      
      savingAccount.setStrategy(new CompoundCalculationStrategy());
      currentAccount.setStrategy(new CompoundCalculationStrategy());

      System.out.println("saving account compound interest: " +savingAccount.getInterest());
      System.out.println("current account compound interest: " +currentAccount.getInterest());

      System.out.println(savingAccount);
      System.out.println(currentAccount);
      }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}
