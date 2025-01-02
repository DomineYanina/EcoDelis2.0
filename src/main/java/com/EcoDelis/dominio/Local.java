package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String password;
    private String CUIT;
    private Date f_registro;

    @OneToMany(mappedBy = "Local", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales = new ArrayList<>();

    public Local(String nombre, String email, String password, String CUIT, Date f_registro) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.CUIT = CUIT;
        this.f_registro = f_registro;
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

    public void agregarSucursal(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
