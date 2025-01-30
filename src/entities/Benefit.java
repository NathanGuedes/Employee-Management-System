package entities;

import entities.enums.BenefitType;

public class Benefit {

    private String description;
    private Double value;
    private BenefitType type;

    public Benefit(){
    }

    public Benefit(BenefitType type, Double value) {
        this.type = type;
        this.value = value;
    }

    public Benefit(String description, Double value, BenefitType type) {
        this.value = value;
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public BenefitType getType() {
        return type;
    }

    public void setType(BenefitType type) {
        this.type = type;
    }
}
