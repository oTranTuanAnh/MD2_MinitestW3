package src;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Material implements Serializable {
    String id;
    String name;
    LocalDate manufacturingDate;
    int cost;
    public static final double DISCOUNT_PERCENT40 = 0.4;
    public static final double DISCOUNT_PERCENT30 = 0.3;
    public static final double DISCOUNT_PERCENT20 = 0.2;
    public static final double DISCOUNT_PERCENT10 = 0.1;
    public static final double DISCOUNT_PERCENT5 = 0.05;

    public Material() {
    }

    public Material(String id, String name, LocalDate manufacturingDate, int cost) {
        this.id = id;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", cost=" + cost +
                '}';
    }

    public abstract double getAmount();
    public abstract LocalDate getExpiryDate();

}
