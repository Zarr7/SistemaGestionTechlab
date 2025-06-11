package com.techlab.pedidos;
import java.util.*;

public class Pedido {
    private static int contador = 1;
    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = contador++;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido lp) {
        lineas.add(lp);
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::getSubtotal).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (LineaPedido lp : lineas) {
            sb.append("  ").append(lp).append("\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}