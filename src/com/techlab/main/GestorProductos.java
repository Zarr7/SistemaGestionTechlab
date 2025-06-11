package com.techlab.main;
import com.techlab.productos.Producto;
import java.util.*;

public class GestorProductos {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(String nombre, double precio, int stock) {
        productos.add(new Producto(nombre, precio, stock));
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Producto buscarPorNombre(String nombre) {
        return productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }

    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }
}