package services;

public class ManagementSystemServices {

    public static void showDepartmentOption(){

        System.out.println("Choose department: ");
        System.out.println("1 - TI");
        System.out.println("2 - RH");
        System.out.println("3 - Finance");
    }

    public static String departmentOptionSelect(int option){

        return switch (option) {
            case 1 -> "TI";
            case 2 -> "RH";
            case 3 -> "Finance";
            default -> "Opcao invalida";
        };
    }

    public static void showLevelOption(){

        System.out.println("Choose worker level: ");
        System.out.println("1 - JUNIOR");
        System.out.println("2 - MID_LEVEL");
        System.out.println("3 - SENIOR");
    }

    public static String levelOptionSelect(int option){

        return switch (option) {
            case 1 -> "JUNIOR";
            case 2 -> "MID_LEVEL";
            case 3 -> "SENIOR";
            default -> "Opcao invalida";
        };
    }
}
