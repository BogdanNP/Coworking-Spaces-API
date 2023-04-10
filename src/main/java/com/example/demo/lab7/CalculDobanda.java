package com.example.demo.lab7;

public class CalculDobanda {
    public double dobanda(TipDobanda tipDobanda){
        double result = 1.0;
        switch(tipDobanda){
            case MARE:
                result *= 0.7;
                break;
            case MEDIE:
                result *= 0.3;
                break;
            case MICA:
                result *= 0.1;
                break;
            default:
                result *= 0.5;
                break;

        }
        return result;
    }

    public double dobanda(User user){
        switch(user.getRisk()){
            case HIGH:
                return this.dobanda(TipDobanda.MICA);
            case MEDIUM: 
                return this.dobanda(TipDobanda.MEDIE);
            case LOW:
                return this.dobanda(TipDobanda.MARE);
            default:
                return this.dobanda(TipDobanda.MEDIE);

        }
    }
}
