package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroSucursalViewModel {
    private long id;

    private String nombre;
    private Date f_registro;
    private TipoSuscripcionSucursal tipoSuscripcion;
    private Local local;
    private DireccionSucursal direccion;
    private List<TelefonoSucursal> telefonos = new ArrayList<>();
    private List<HorarioRetiro> horarioRetiros = new ArrayList<>();
    private List<Promocion> promociones = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private Responsable responsable;

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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public DireccionSucursal getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionSucursal direccion) {
        this.direccion = direccion;
    }

    public List<TelefonoSucursal> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoSucursal> telefonos) {
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

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
