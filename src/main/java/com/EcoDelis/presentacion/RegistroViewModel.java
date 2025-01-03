package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;

import java.util.Date;

public class RegistroViewModel {
    private String nombre;
    private String apellido;
    private TipoDocumento Tipo_doc;
    private long Nro_doc;
    private Date F_nac;
    private Date F_registro;
    private TipoCliente Tipo_cliente;
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

    public TipoDocumento getTipo_doc() {
        return Tipo_doc;
    }

    public void setTipo_doc(TipoDocumento tipo_doc) {
        Tipo_doc = tipo_doc;
    }

    public long getNro_doc() {
        return Nro_doc;
    }

    public void setNro_doc(long nro_doc) {
        Nro_doc = nro_doc;
    }

    public Date getF_nac() {
        return F_nac;
    }

    public void setF_nac(Date f_nac) {
        F_nac = f_nac;
    }

    public Date getF_registro() {
        return F_registro;
    }

    public void setF_registro(Date f_registro) {
        F_registro = f_registro;
    }

    public TipoCliente getTipo_cliente() {
        return Tipo_cliente;
    }

    public void setTipo_cliente(TipoCliente getTipo_cliente) {
        this.Tipo_cliente = getTipo_cliente;
    }
}
