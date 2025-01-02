package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;

import java.util.ArrayList;
import java.util.List;

public class PromocionViewModel {
    private long id;
    private String nombre;
    private int descuento;
    private double precio_original;
    private double precio_final;
    private int unidadesRestantes;
    private Sucursal sucursal;
    private List<Item> items = new ArrayList<>();

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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
