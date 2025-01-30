package entities;

import java.time.LocalDate;

public class Contract {
    private LocalDate date;
    private Double hourlyRate;
    private Integer hours;

    public Contract(){
    }

    public Contract(LocalDate date, Double hourlyRate, Integer hours) {
        this.date = date;
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public double totalContract() {
        return hourlyRate * hours;
    }
}
