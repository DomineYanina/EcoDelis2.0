package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Time;

public class HorarioRetiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private DiaDeLaSemana dia;
    private Time hora_inicio;
    private Time hora_fin;

    public HorarioRetiro(DiaDeLaSemana dia, Time hora_inicio, Time hora_fin) {
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

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
}
