package com.EcoDelis.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private int descuento;
    private double precio_original;
    private double precio_final;
    private int unidadesRestantes;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToMany
    @JoinTable(
            name = "items_promociones",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    public Promocion(String nombre, int descuento, double precio_original, double precio_final, int unidadesRestantes, List<Item> items) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.precio_original = precio_original;
        this.precio_final = precio_final;
        this.unidadesRestantes = unidadesRestantes;
        this.items = items;
    }

    public Promocion() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getPrecio_original() {
        return precio_original;
    }

    public void setPrecio_original(double precio_original) {
        this.precio_original = precio_original;
    }

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    public int getUnidadesRestantes() {
        return unidadesRestantes;
    }

    public void setUnidadesRestantes(int unidadesRestantes) {
        this.unidadesRestantes = unidadesRestantes;
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
