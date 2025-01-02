package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.DiaDeLaSemana;
import com.EcoDelis.dominio.Sucursal;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;

public class HorarioRetiroViewModel {
    private long id;

    private DiaDeLaSemana dia;
    private Time hora_inicio;
    private Time hora_fin;
    private Sucursal sucursal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DiaDeLaSemana getDia() {
        return dia;
    }

    public void setDia(DiaDeLaSemana dia) {
        this.dia = dia;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
