package com.example.demo.lab7;

public class OpBDMock implements OpBD {

    @Override
    public User getUser(String name) {
        User user = new User();
        user.setName("John");
        user.setRisk(RiskProfile.HIGH);
        return user;
    }
    
}
