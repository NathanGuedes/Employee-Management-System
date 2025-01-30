package entities;

import entities.enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String name;
    private WorkerLevel level;
    private Double baseSalary;
    private Department department;
    private String email;
    private LocalDate birthDate;

    List<Contract> contractList = new ArrayList<>();
    List<Benefit> benefitList = new ArrayList<>();

    public Worker(){
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department, String email, LocalDate birthDate) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addContract(Contract contract) {
        contractList.add(contract);
    }

    public void removeContract(Contract contract) {
        contractList.remove(contract);
    }

    public void addBenefit(Benefit benefit) {
        benefitList.add(benefit);
    }

    public void removeBenefit(Benefit benefit) {
        benefitList.remove(benefit);
    }

    public double calculateTotalSalary() {
        double totalSum = baseSalary;

        for (Contract contract : contractList) {
            totalSum += contract.totalContract();
        }

        for (Benefit benefit : benefitList) {
            totalSum += benefit.getValue();
        }
        return totalSum;
    }

    public double calculateTotalContract() {
        double totalSum = 0;

        for (Contract contract : contractList) {
            totalSum += contract.totalContract();
        }

        for (Benefit benefit : benefitList) {
            totalSum += benefit.getValue();
        }
        return totalSum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double totalSum = 0;

        // Cabeçalho
        sb.append("===== Employee Summary =====").append("\n\n")
                .append("Name: ").append(name).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Birth Date: ").append(formatter.format(birthDate)).append("\n")
                .append("Department: ").append(department.getName()).append("\n")
                .append("Level: ").append(level).append("\n")
                .append("Base Salary: $").append(String.format("%.2f", baseSalary)).append("\n\n");

        // Contratos
        sb.append("\uD83D\uDCCC Contracts:").append("\n");
        for (Contract contract : contractList) {
            sb.append("  - Date: ").append(formatter.format(contract.getDate())).append("\n")
                    .append("    Value per Hour: $").append(String.format("%.2f", contract.getHourlyRate())).append("\n")
                    .append("    Hours Worked: ").append(contract.getHours()).append("\n")
                    .append("    Total: $").append(String.format("%.2f", contract.totalContract())).append("\n\n");
        }

        // Benefícios
        sb.append("\uD83D\uDCCC Benefits:").append("\n");
        for (Benefit benefit : benefitList) {
            sb.append("  - ").append(benefit.getType().name()).append(": $").append(String.format("%.2f", benefit.getValue())).append("\n");
        }

        // Salário Final
        sb.append("\n")
                .append("-----------------------------------").append("\n")
                .append("Final Salary: $").append(String.format("%.2f", calculateTotalSalary())).append("\n");

        return sb.toString();
    }
}
