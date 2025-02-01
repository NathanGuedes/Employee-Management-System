package services;

import entities.*;
import entities.enums.BenefitType;
import entities.enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static List<Department> departments = new ArrayList<>();
    private static List<Worker> workers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Sistema de Gestão de Funcionários ===");
            System.out.println("1. Cadastrar Departamento");
            System.out.println("2. Cadastrar Funcionário");
            System.out.println("3. Adicionar Contrato ao Funcionário");
            System.out.println("4. Adicionar Benefício ao Funcionário");
            System.out.println("5. Calcular Salário Total");
            System.out.println("6. Exibir Relatório de Funcionários");
            System.out.println("7. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarDepartamento();
                    break;
                case 2:
                    cadastrarFuncionario();
                    break;
                case 3:
                    adicionarContrato();
                    break;
                case 4:
                    adicionarBeneficio();
                    break;
                case 5:
                    calcularSalarioTotal();
                    break;
                case 6:
                    exibirRelatorioFuncionarios();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void cadastrarDepartamento() {
        System.out.println();
        System.out.print("Digite o nome do departamento: ");
        String nome = scanner.nextLine();
        Department department = new Department(nome);
        departments.add(department);
        System.out.println("Departamento cadastrado com sucesso!");
    }

    private static void cadastrarFuncionario() {
        System.out.println();
        if (departments.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado. Cadastre um departamento primeiro.");
            return;
        }

        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();

        // Exibir opções de nível e permitir escolha
        System.out.println("Escolha o nível do funcionário:");
        System.out.println("1. JUNIOR");
        System.out.println("2. MID_LEVEL");
        System.out.println("3. SENIOR");
        System.out.print("Digite o número correspondente ao nível: ");
        int nivelEscolhido = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        WorkerLevel level;
        switch (nivelEscolhido) {
            case 1:
                level = WorkerLevel.JUNIOR;
                break;
            case 2:
                level = WorkerLevel.MID_LEVEL;
                break;
            case 3:
                level = WorkerLevel.SENIOR;
                break;
            default:
                System.out.println("Opção inválida! Nível definido como JUNIOR por padrão.");
                level = WorkerLevel.JUNIOR;
                break;
        }

        System.out.print("Digite o salário base do funcionário: ");
        Double baseSalary = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o email do funcionário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a data de nascimento do funcionário (dd/MM/yyyy): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine(), formatter);

        // Listar departamentos disponíveis
        System.out.println("Departamentos disponíveis:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        System.out.print("Escolha o número do departamento: ");
        int depIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (depIndex >= 0 && depIndex < departments.size()) {
            Worker worker = new Worker(nome, level, baseSalary, departments.get(depIndex), email, birthDate);
            workers.add(worker);
            departments.get(depIndex).addWorker(worker);
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Índice de departamento inválido!");
        }
    }

    private static void adicionarContrato() {
        System.out.println();
        if (workers.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado. Cadastre um funcionário primeiro.");
            return;
        }

        System.out.println("Funcionários disponíveis:");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println((i + 1) + ". " + workers.get(i).getName());
        }
        System.out.print("Escolha o número do funcionário: ");
        int workerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (workerIndex >= 0 && workerIndex < workers.size()) {
            System.out.print("Digite a data do contrato (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
            System.out.print("Digite o valor por hora: ");
            Double hourlyRate = scanner.nextDouble();
            System.out.print("Digite o número de horas trabalhadas: ");
            Integer hours = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            Contract contract = new Contract(date, hourlyRate, hours);
            workers.get(workerIndex).addContract(contract);
            System.out.println("Contrato adicionado com sucesso!");
        } else {
            System.out.println("Índice de funcionário inválido!");
        }
    }

    private static void adicionarBeneficio() {
        System.out.println();
        if (workers.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado. Cadastre um funcionário primeiro.");
            return;
        }

        System.out.println("Funcionários disponíveis:");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println((i + 1) + ". " + workers.get(i).getName());
        }
        System.out.print("Escolha o número do funcionário: ");
        int workerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (workerIndex >= 0 && workerIndex < workers.size()) {
            System.out.print("Digite o tipo de benefício (HEALTH, FOOD, TRANSPORT, BONUS): ");
            BenefitType type = BenefitType.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Digite o valor do benefício: ");
            Double value = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha

            Benefit benefit = new Benefit(type, value);
            workers.get(workerIndex).addBenefit(benefit);
            System.out.println("Benefício adicionado com sucesso!");
        } else {
            System.out.println("Índice de funcionário inválido!");
        }
    }

    private static void calcularSalarioTotal() {
        System.out.println();
        if (workers.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado. Cadastre um funcionário primeiro.");
            return;
        }

        System.out.println("Funcionários disponíveis:");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println((i + 1) + ". " + workers.get(i).getName());
        }
        System.out.print("Escolha o número do funcionário: ");
        int workerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (workerIndex >= 0 && workerIndex < workers.size()) {
            double totalSalary = workers.get(workerIndex).calculateTotalSalary();
            System.out.println("Salário total do funcionário " + workers.get(workerIndex).getName() + ": $" + String.format("%.2f", totalSalary));
        } else {
            System.out.println("Índice de funcionário inválido!");
        }
    }

    private static void exibirRelatorioFuncionarios() {
        System.out.println();
        if (workers.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}