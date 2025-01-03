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
    private TipoDocumento tipo_doc;
    private long nro_doc;
    private Date f_nac;
    private Date f_registro;
    private TipoCliente tipo_cliente;
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
        this.tipo_doc = tipo_doc;
        this.nro_doc = nro_doc;
        this.f_nac = f_nac;
        this.f_registro = f_registro;
        this.tipo_cliente = tipo_cliente;
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

    public TipoDocumento getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(TipoDocumento tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public long getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(long nro_doc) {
        this.nro_doc = nro_doc;
    }

    public Date getF_nac() {
        return f_nac;
    }

    public void setF_nac(Date f_nac) {
        this.f_nac = f_nac;
    }

    public Date getF_registro() {
        return f_registro;
    }

    public void setF_registro(Date f_registro) {
        this.f_registro = f_registro;
    }

    public TipoCliente getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(TipoCliente tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public void agregarDireccion(DireccionCliente direccionCliente){
        direcciones.add(direccionCliente);
    }

    public void agregarTelefono(TelefonoCliente telefonoCliente){
        telefonoClientes.add(telefonoCliente);
    }

    public void eliminarDireccion(DireccionCliente direccionCliente){
        direcciones.remove(direccionCliente);
    }

    public void eliminarTelefono(TelefonoCliente telefonoCliente){
        telefonoClientes.remove(telefonoCliente);
    }

    public List<DireccionCliente> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionCliente> direcciones) {
        this.direcciones = direcciones;
    }

    public List<TelefonoCliente> getTelefonos() {
        return telefonoClientes;
    }

    public void setTelefonos(List<TelefonoCliente> telefonoClientes) {
        this.telefonoClientes = telefonoClientes;
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
