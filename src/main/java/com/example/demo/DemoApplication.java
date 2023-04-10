package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lab7.CalculDobanda;
import com.example.demo.lab7.OpBD;
import com.example.demo.lab7.OpBDMock;
import com.example.demo.lab7.TipDobanda;
import com.example.demo.lab7.User;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
      CalculDobanda calculDobanda = new CalculDobanda();
      System.out.println("Dobanda = " + calculDobanda.dobanda(TipDobanda.MICA));

      OpBDMock opBD = new OpBDMock();
      User user = opBD.getUser("TEST USER");

      System.out.println("User Risk: " + user.getRisk());
      System.out.println("User Dobanda: " + calculDobanda.dobanda(user));
      // SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}
