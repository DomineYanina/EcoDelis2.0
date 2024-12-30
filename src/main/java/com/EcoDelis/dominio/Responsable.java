package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Responsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellido;
    private TipoDocumento tipo_doc;
    private long nro_doc;
    private Date f_nac;

    @OneToMany(mappedBy = "Responsable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> telefonos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "direccion_responsable")
    private Direccion direccion;

    public Responsable(String nombre, String apellido, TipoDocumento tipo_doc, long nro_doc, Date f_nac, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_doc = tipo_doc;
        this.nro_doc = nro_doc;
        this.f_nac = f_nac;
        this.direccion = direccion;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void agregarTelefono(Telefono telefono){
        telefonos.add(telefono);
    }

    public void eliminarTelefono(Telefono telefono){
        telefonos.remove(telefono);
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
