package com.EcoDelis.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Responsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellido;
    private TipoDocumento tipo_doc;
    private long nro_doc;
    private LocalDate f_nac;

    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefonoResponsable> telefonos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "direccion_responsable")
    private DireccionResponsable direccion;

    public Responsable(String nombre, String apellido, TipoDocumento tipo_doc, long nro_doc, LocalDate f_nac, DireccionResponsable direccion, TelefonoResponsable telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_doc = tipo_doc;
        this.nro_doc = nro_doc;
        this.f_nac = f_nac;
        this.direccion = direccion;
        this.telefonos.add(telefono);
    }

    public Responsable() {

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

    public LocalDate getF_nac() {
        return f_nac;
    }

    public void setF_nac(LocalDate f_nac) {
        this.f_nac = f_nac;
    }

    public DireccionResponsable getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionResponsable direccion) {
        this.direccion = direccion;
    }

    public void agregarTelefono(TelefonoResponsable telefono){
        telefonos.add(telefono);
    }

    public void eliminarTelefono(TelefonoResponsable telefono){
        telefonos.remove(telefono);
    }

    public List<TelefonoResponsable> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoResponsable> telefonos) {
        this.telefonos = telefonos;
    }
}
