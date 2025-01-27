package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;

import java.util.Date;

public class RegistroClienteViewModel {
    private String nombre;
    private String apellido;
    private TipoDocumento Tipodoc;
    private long Nrodoc;
    private Date Fnac;
    private Date Fregistro;
    private TipoCliente Tipocliente;
    private String email;
    private String password;

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
        return Tipodoc;
    }

    public void setTipodoc(TipoDocumento tipodoc) {
        Tipodoc = tipodoc;
    }

    public long getNrodoc() {
        return Nrodoc;
    }

    public void setNrodoc(long nrodoc) {
        Nrodoc = nrodoc;
    }

    public Date getFnac() {
        return Fnac;
    }

    public void setFnac(Date fnac) {
        Fnac = fnac;
    }

    public Date getFregistro() {
        return Fregistro;
    }

    public void setFregistro(Date fregistro) {
        Fregistro = fregistro;
    }

    public TipoCliente getTipocliente() {
        return Tipocliente;
    }

    public void setTipocliente(TipoCliente tipocliente) {
        Tipocliente = tipocliente;
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
