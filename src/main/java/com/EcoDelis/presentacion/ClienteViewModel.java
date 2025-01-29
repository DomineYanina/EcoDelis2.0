package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteViewModel {
    private long Id;
    private String nombre;
    private String apellido;
    private TipoDocumento tipodoc;
    private long nrodoc;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fnac;
    private LocalDate fregistro;
    private TipoCliente tipocliente;
    private List<DireccionCliente> direcciones = new ArrayList<>();
    private List<TelefonoCliente> telefonoClientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private String email;
    private String password;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(TipoDocumento tipodoc) {
        this.tipodoc = tipodoc;
    }

    public long getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(long nrodoc) {
        this.nrodoc = nrodoc;
    }

    public LocalDate getFnac() {
        return fnac;
    }

    public void setFnac(LocalDate fnac) {
        this.fnac = fnac;
    }

    public LocalDate getFregistro() {
        return fregistro;
    }

    public void setFregistro(LocalDate fregistro) {
        this.fregistro = fregistro;
    }

    public TipoCliente getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(TipoCliente tipocliente) {
        this.tipocliente = tipocliente;
    }

    public List<DireccionCliente> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionCliente> direcciones) {
        this.direcciones = direcciones;
    }

    public List<TelefonoCliente> getTelefonoClientes() {
        return telefonoClientes;
    }

    public void setTelefonoClientes(List<TelefonoCliente> telefonoClientes) {
        this.telefonoClientes = telefonoClientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
