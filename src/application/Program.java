package application;


import entities.Benefit;
import entities.Contract;
import entities.Department;
import entities.Worker;
import entities.enums.BenefitType;
import entities.enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static services.ManagementSystemServices.*;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)){

            System.out.println("===== Employee Management System =====\n");
            System.out.println("Enter employee details:");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Birth Date (DD/MM/YYYY): ");
            String dataInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataInput, formatter);
            System.out.print("Base Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();


            System.out.println();
            showDepartmentOption();
            int option;
            do {
                System.out.print("Select option: ");
                option   = sc.nextInt();
                sc.nextLine();
            }while (option < 1 || option > 3);

            String departamentName = departmentOptionSelect(option);

            System.out.println();
            showLevelOption();
            do {
                System.out.print("Select option: ");
                option   = sc.nextInt();
                sc.nextLine();
            }while (option < 1 || option > 3);

            WorkerLevel level = WorkerLevel.valueOf(levelOptionSelect(option));

            Worker worker = new Worker(name, level, salary, new Department(departamentName), email, data);
            worker.getDepartment().addWorker(worker);

            System.out.println();
            System.out.print("Enter number of contracts: ");
            int n = sc.nextInt();
            sc.nextLine();

            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.printf("Contract %d%n", i + 1);
                System.out.print("Date (DD/MM/YYYY): ");
                dataInput = sc.nextLine();
                data = LocalDate.parse(dataInput, formatter);
                System.out.print("Value per Hour: ");
                double valuePerHours = sc.nextDouble();
                sc.nextLine();
                System.out.print("Hours Worked: ");
                int hoursWorked = sc.nextInt();
                sc.nextLine();

                Contract contract = new Contract(data, valuePerHours, hoursWorked);
                worker.addContract(contract);

                System.out.println();
            }

            System.out.print("Enter number of benefits: ");
            n = sc.nextInt();
            sc.nextLine();

            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.printf("Benefit %d%n", i + 1);
                System.out.print("Type (HEALTH_INSURANCE, MEAL_VOUCHER, TRANSPORT_VOUCHER, BONUS): ");
                BenefitType benefitType = BenefitType.valueOf(sc.nextLine());
                System.out.print("Value: ");
                double valueBenefit = sc.nextDouble();
                sc.nextLine();

                Benefit benefit = new Benefit(benefitType, valueBenefit);
                worker.addBenefit(benefit);
                System.out.println();
            }

            System.out.println(worker);
        }
    }
}
