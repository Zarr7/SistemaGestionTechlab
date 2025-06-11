package com.techlab.main;
import com.techlab.utils.Utils;
import com.techlab.pedidos.*;
import com.techlab.productos.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static GestorProductos gestorProductos = new GestorProductos();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n========= SISTEMA DE GESTIÓN - TECHLAB =========\n");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            opcion = Utils.leerEntero(scanner, "Elija una opción: ");

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizar();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
            }
        } while (opcion != 7);
    }

    static void agregarProducto() {
        String nombre = Utils.leerTexto(scanner, "Nombre: ");
        double precio = Utils.leerDouble(scanner, "Precio: ");
        int stock = Utils.leerEntero(scanner, "Stock: ");
        gestorProductos.agregarProducto(nombre, precio, stock);
        System.out.println("Producto agregado con éxito.");
    }

    static void listarProductos() {
        gestorProductos.listarProductos().forEach(System.out::println);
    }

    static void buscarActualizar() {
        String entrada = Utils.leerTexto(scanner, "Buscar por ID o nombre: ");
        Producto p = entrada.matches("\\d+") ? gestorProductos.buscarPorId(Integer.parseInt(entrada)) : gestorProductos.buscarPorNombre(entrada);
        if (p != null) {
            System.out.println(p);
            String tipo = Utils.leerTexto(scanner, "¿Actualizar precio o stock? (p/s): ");
            if (tipo.equalsIgnoreCase("p")) {
                double nuevoPrecio = Utils.leerDouble(scanner, "Nuevo precio: ");
            } else {
                int stock = Utils.leerEntero(scanner, "Nuevo stock: ");
                if (stock >= 0) p.setStock(stock);
                else System.out.println("Stock inválido.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    static void eliminarProducto() {
        int id = Utils.leerEntero(scanner, "ID del producto a eliminar: ");
        if (gestorProductos.eliminarProducto(id)) System.out.println("Producto eliminado.");
        else System.out.println("Producto no encontrado.");
    }

    static void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            listarProductos();
            int id = Utils.leerEntero(scanner, "ID producto (0 para terminar): ");
            if (id == 0) break;
            Producto p = gestorProductos.buscarPorId(id);
            if (p != null) {
                int cant = Utils.leerEntero(scanner, "Cantidad:");
                if (cant <= p.getStock()) {
                    pedido.agregarLinea(new LineaPedido(p, cant));
                    p.setStock(p.getStock() - cant);
                } else {
                    System.out.println("Stock insuficiente.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        }
        if (!pedido.toString().contains("x")) {
            System.out.println("Pedido vacío, no se guardó.");
        } else {
            pedidos.add(pedido);
            System.out.println("Pedido creado:");
            System.out.println(pedido);
        }
    }

    static void listarPedidos() {
        if (pedidos.isEmpty()) System.out.println("No hay pedidos registrados.");
        else pedidos.forEach(p -> System.out.println("\n" + p));
    }
}