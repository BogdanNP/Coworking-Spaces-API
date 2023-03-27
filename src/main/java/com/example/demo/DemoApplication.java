package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
      ValueChange valueChange = new ValueChange();
      int initValue = valueChange.getValue();

      System.out.println("init value = " + initValue);
      UpdateValueChange updateValueChange = new UpdateValueChange(valueChange);
      updateValueChange.onUpdate(100);
      int newValue = valueChange.getValue();

      System.out.println("new value = " + newValue);

      //SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}
