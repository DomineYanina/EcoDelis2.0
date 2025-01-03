package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Sucursal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalViewModel {
    private long id;
    private String nombre;
    private String CUIT;
    private Date f_registro;
    private String email;
    private String password;
    private List<Sucursal> sucursales = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public Date getF_registro() {
        return f_registro;
    }

    public void setF_registro(Date f_registro) {
        this.f_registro = f_registro;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
