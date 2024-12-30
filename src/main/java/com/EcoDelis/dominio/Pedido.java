package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date fecha_realizado;
    private Date fecha_retirado;
    private double monto_total;
    private EstadoPedido estado;

    @OneToMany(mappedBy = "Local", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales = new ArrayList<>();

}
