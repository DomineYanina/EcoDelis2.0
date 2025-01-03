package com.EcoDelis.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date fecha_realizado;
    private Date fecha_retirado;
    private double monto_total;
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedidos_promociones",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id")
    )
    private List<Promocion> promociones = new ArrayList<>();

    public Pedido(Date fecha_realizado, Date fecha_retirado, double monto_total, EstadoPedido estado, Sucursal sucursal, Cliente cliente, List<Promocion> promociones) {
        this.fecha_realizado = fecha_realizado;
        this.fecha_retirado = fecha_retirado;
        this.monto_total = monto_total;
        this.estado = estado;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.promociones = promociones;
    }

    public Pedido() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha_realizado() {
        return fecha_realizado;
    }

    public void setFecha_realizado(Date fecha_realizado) {
        this.fecha_realizado = fecha_realizado;
    }

    public Date getFecha_retirado() {
        return fecha_retirado;
    }

    public void setFecha_retirado(Date fecha_retirado) {
        this.fecha_retirado = fecha_retirado;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }
}
