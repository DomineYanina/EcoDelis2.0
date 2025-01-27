package com.EcoDelis.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nombre;
    private String apellido;
    private TipoDocumento tipodoc;
    private long nrodoc;
    private Date fnac;
    private Date fregistro;
    private TipoCliente tipocliente;
    private String email;
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DireccionCliente> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefonoCliente> telefonoClientes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, TipoDocumento tipo_doc, long nro_doc, Date f_nac, Date f_registro, TipoCliente tipo_cliente, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipodoc = tipo_doc;
        this.nrodoc = nro_doc;
        this.fnac = f_nac;
        this.fregistro = f_registro;
        this.tipocliente = tipo_cliente;
        this.email=email;
        this.password=password;
    }

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

    public Date getFnac() {
        return fnac;
    }

    public void setFnac(Date fnac) {
        this.fnac = fnac;
    }

    public Date getFregistro() {
        return fregistro;
    }

    public void setFregistro(Date fregistro) {
        this.fregistro = fregistro;
    }

    public TipoCliente getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(TipoCliente tipocliente) {
        this.tipocliente = tipocliente;
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
}
