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
    String n;
    /** Número de tickets emitidos durante la sesión. */
    int c;
    /** Total facturado (IVA incluido) durante la sesión. */
    double t;
    /** Lista de productos pendientes de cobro en el ticket actual. */
    ArrayList<Producto> ps;
    
    /**
     * Crea un nuevo cajero con el nombre n e inicia el contadorcde tickets y el total facturado a 0
     * @param n nombre del cajero
     */
    public Cajero(String n) {
        this.n = n;
        this.c = 0;
        this.t = 0;
        this.ps = new ArrayList<>();
    }
    
    /**
     * Sirve para añadir un producto al ticket actual 
     * @param p es el producto que se quiere añadir
     */
    public void ANADIRPRODUCTO(Producto p) {
        ps.add(p);
    }
    
    /**
     * Sirve para eliminar un producto del ticket actual
     * @param p es el producto que se quiere borrar
     */
    public void eliminarProDUCTO(Producto p) {
        ps.remove(p);
    }
    
    /**
     * Sirve para hacer el cobro del ticket actual
     * Sirve para calcular el total de todos los tickets, le suma el IVA del 21%
     * y muestra los tickets de forma detallada. Para terminar, incrementa
     * el contador de tickets, acumula el total y vacía la lista de productos.
     */
    public void cobrar() {
        double subt = 0;
        for (Producto p : ps) {
            subt = subt + p.calcularImporte();
        }
        double iva = subt * 0.21;
        double tot = subt + iva;

        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + n);
        for (Producto p : ps) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subt) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", tot) + " EUR");
        System.out.println("==================");

        c = c + 1;
        t = t + tot;
        ps.clear();
    }
    
    /**
     * Sirve para mostrar por consola el resumen de los tickets emitidos al hacer
     * el cierre de caja.
     * Muestra el nombre del cajero, el numero de tickets emitidos, el total
     * facturado sin IVA, el total facturado SOLO de IVA a partir del total facturado
     */
    public void cierreCaja() {
        double ivaRec = t - (t / (1 + 0.21));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + n);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + c);
        System.out.println("Total facturado:  " + String.format("%.2f", t) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }
    
    /**
     * Sirve para indicar i el ticket esta lleno o vacio
     * @return {@code true} si la lista de productos está vacía, {@code false} si tiene productos.
     */
    public boolean ticketVacio() {
        return ps.isEmpty();
    }
    
    /**
     * 
     * @return el total de tickets emitidos
     */
    public int getTicketsEmitidos() {
        return c;
    }
    
    /**
     * 
     * @return el total facturado con IVA durante la sesion
     */
    public double getTotalDia() {
        return t;
    }
}
