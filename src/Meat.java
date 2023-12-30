package src;

import java.io.Serializable;
import java.time.LocalDate;

public class Meat extends Material implements Discount, Serializable {
    public static final int EXP_DAY_OF_MEAT = 7;
    public static final int EXP_DAYS_TO_APPLY_DISCOUNT30 = 5;
    double weight;

    public Meat() {
    }

    public Meat(double weight) {
        this.weight = weight;
    }

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Meat{" +
                "id= " + id +
                ", name= " + name +
                ", manufacturingDate= " + manufacturingDate +
                ", cost= " + cost +
                ", weight= " + weight +
                "} ";
    }

    @Override
    public double getAmount() {
        return cost*weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return manufacturingDate.plusDays(EXP_DAY_OF_MEAT);
    }

    @Override
    public double getRealMoney() {
        LocalDate toDay = LocalDate.now();
        if (toDay.isAfter(getExpiryDate().minusDays(EXP_DAYS_TO_APPLY_DISCOUNT30))){
            return (cost*weight*(1-DISCOUNT_PERCENT30));
        }else {
            return (cost*weight*(1-DISCOUNT_PERCENT10));
        }
    }
}
