package src;

import java.io.Serializable;
import java.time.LocalDate;

public class CrispyFlour extends Material implements Discount, Serializable {

    public static final int EXP_YEAR_OF_FLOUR = 1;
    public static final int EXP_MONTH_TO_APPLY_DISCOUNT40 = 2;
    public static final int EXP_MONTHS_TO_APPLY_DISCOUNT20 = 4;
    double quantity;

    public CrispyFlour() {
    }

    public CrispyFlour(double quantity) {
        this.quantity = quantity;
    }

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, double quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CrispyFlour{" +
                "id=" + id +
                ", name= " + name +
                ", manufacturingDate= " + manufacturingDate+
                ", cost= " + cost +
                ", quantity= " + quantity +
                "} ";
    }

    @Override
    public double getAmount() {
        return quantity*cost;
    }

    @Override
    public LocalDate getExpiryDate() {
        return (manufacturingDate.plusYears(EXP_YEAR_OF_FLOUR));
    }

    @Override
    public double getRealMoney() {
        LocalDate toDay = LocalDate.now();
        if (toDay.isAfter(getExpiryDate().minusMonths(EXP_MONTH_TO_APPLY_DISCOUNT40))){
            return (quantity*cost*(1- DISCOUNT_PERCENT40));
        } else if (toDay.isAfter(getExpiryDate().minusMonths(EXP_MONTHS_TO_APPLY_DISCOUNT20))){
            return (quantity*cost*(1-DISCOUNT_PERCENT20));
        } else {
            return (quantity*cost*(1-DISCOUNT_PERCENT5));
        }

    }
}
