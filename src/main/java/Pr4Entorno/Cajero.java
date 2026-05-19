/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pr4Entorno;

import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class Cajero {

    /** Nombre del cajero. */
    private String nombre;
    /** Número de tickets emitidos durante la sesión. */
    private int ticketsEmitidos;
    /** Total facturado (IVA incluido) durante la sesión. */
    private double totalDia;
    /** Lista de productos pendientes de cobro en el ticket actual. */
    private ArrayList<Producto> productos;

    /**
     * Crea un nuevo cajero con el nombre indicado.
     * Inicializa el contador de tickets y el total facturado a cero.
     * @param nombreCajero Nombre del cajero.
     */
    public Cajero(String nombreCajero) {
        this.nombre = nombreCajero;
        this.ticketsEmitidos = 0;
        this.totalDia = 0;
        this.productos = new ArrayList<>();
    }

    /**
     * Añade un producto al ticket actual.
     * @param producto Producto que se desea añadir.
     */
    public void añadirProducto(Producto producto) {
        getProductos().add(producto);
    }

    /**
     * Elimina un producto del ticket actual.
     * @param producto Producto que se desea eliminar.
     */
    public void eliminarProducto(Producto producto) {
        getProductos().remove(producto);
    }

    /**
     * Procesa el cobro del ticket actual.
     * Calcula el subtotal, aplica el IVA del 21% y muestra el ticket detallado.
     * Al finalizar, incrementa el contador de tickets, acumula el total
     * y vacía la lista de productos.
     */
    public void cobrar() {
        double subtotal = calcularSubtotal();
        double iva = calcularIva(subtotal);
        double total = subtotal + iva;

        imprimirTicket(subtotal, iva, total);
        registrarVenta(total);
    }

    private void registrarVenta(double total) {
        setTicketsEmitidos(getTicketsEmitidos() + 1);
        setTotalDia(getTotalDia() + total);
        getProductos().clear();
    }

    private void imprimirTicket(double subtotal, double iva, double total) {
        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + getNombre());
        for (Producto p : getProductos()) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", total) + " EUR");
        System.out.println("==================");
    }

    private double calcularSubtotal() {
        double subtotal = 0;
        for (Producto p : getProductos()) {
            subtotal = subtotal + p.calcularImporte();
        }
        return subtotal;
    }

    private double calcularIva(double subtotal) {
        return subtotal * IVA;
    }

    private static final double IVA = 0.21;

    /**
     * Muestra por consola el resumen de cierre de caja.
     * Incluye el nombre del cajero, el número de tickets emitidos,
     * el total facturado con IVA y el IVA recaudado.
     */
    public void cierreCaja() {
        double ivaRec = getTotalDia() - (getTotalDia() / (1 + IVA));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + getNombre());
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + getTicketsEmitidos());
        System.out.println("Total facturado:  " + String.format("%.2f", getTotalDia()) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }

    /**
     * Indica si el ticket actual no tiene ningún producto.
     * @return {@code true} si la lista de productos está vacía; {@code false} en caso contrario.
     */
    public boolean ticketVacio() {
        return getProductos().isEmpty();
    }

    /**
     * @return el total de tickets emitidos durante la sesión.
     */
    public int getTicketsEmitidos() {
        return ticketsEmitidos;
    }

    /**
     * @return el total facturado con IVA durante la sesión.
     */
    public double getTotalDia() {
        return totalDia;
    }

    /**
     * @return el nombre del cajero.
     */
    private String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setTicketsEmitidos(int ticketsEmitidos) {
        this.ticketsEmitidos = ticketsEmitidos;
    }

    private void setTotalDia(double totalDia) {
        this.totalDia = totalDia;
    }

    private ArrayList<Producto> getProductos() {
        return productos;
    }

    private void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
}
