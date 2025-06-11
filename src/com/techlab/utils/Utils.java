package com.techlab.utils;
import java.util.Scanner;

public class Utils {

    public static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Debe ingresar un número entero. Intente de nuevo:");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.println("Debe ingresar un número decimal. Intente de nuevo:");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static String leerOpcion(Scanner sc, String mensaje, String... opcionesValidas) {
        String input;
        do {
            System.out.println(mensaje);
            input = sc.nextLine().trim().toLowerCase();
            for (String op : opcionesValidas) {
                if (input.equalsIgnoreCase(op)) {
                    return input;
                }
            }
            System.out.println("Opción inválida. Intente de nuevo.");
        } while (true);
    }
}