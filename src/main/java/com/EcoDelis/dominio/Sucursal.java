package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private Date f_registro;
    private TipoSuscripcionSucursal tipoSuscripcion;

    @OneToOne
    @JoinColumn(name = "direccion_sucursal")
    private Direccion direccion;

    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> telefonos = new ArrayList<>();

    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioRetiro> horarioRetiros = new ArrayList<>();

    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Promocion> promociones = new ArrayList<>();

    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "responsable_sucursal")
    private Responsable responsable;

    public Sucursal(String nombre, Date f_registro, TipoSuscripcionSucursal tipoSuscripcion, Direccion direccion, List<Telefono> telefonos, List<HorarioRetiro> horarioRetiros, List<Promocion> promociones, List<Item> items, List<Pedido> pedidos, Responsable responsable) {
        this.nombre = nombre;
        this.f_registro = f_registro;
        this.tipoSuscripcion = tipoSuscripcion;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.horarioRetiros = horarioRetiros;
        this.promociones = promociones;
        this.items = items;
        this.pedidos = pedidos;
        this.responsable = responsable;
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

    public Date getF_registro() {
        return f_registro;
    }

    public void setF_registro(Date f_registro) {
        this.f_registro = f_registro;
    }

    public TipoSuscripcionSucursal getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcionSucursal tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<HorarioRetiro> getHorarioRetiros() {
        return horarioRetiros;
    }

    public void setHorarioRetiros(List<HorarioRetiro> horarioRetiros) {
        this.horarioRetiros = horarioRetiros;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    private void agregarPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public void agregarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
