package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponsableViewModel {
    private long id;
    private String nombre;
    private String apellido;
    private TipoDocumento tipo_doc;
    private long nro_doc;
    private LocalDate f_nac;
    private List<TelefonoResponsable> telefonos = new ArrayList<>();
    private DireccionResponsable direccion;

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

    public List<TelefonoResponsable> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoResponsable> telefonos) {
        this.telefonos = telefonos;
    }

    public DireccionResponsable getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionResponsable direccion) {
        this.direccion = direccion;
    }
}
