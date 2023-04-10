package com.example.demo.lab7;

public class User {
    private String name;
    private RiskProfile risk;

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return RiskProfile return the risk
     */
    public RiskProfile getRisk() {
        return risk;
    }

    /**
     * @param risk the risk to set
     */
    public void setRisk(RiskProfile risk) {
        this.risk = risk;
    }

}
