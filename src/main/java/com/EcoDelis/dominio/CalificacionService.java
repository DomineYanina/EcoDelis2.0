package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

@Service
public interface CalificacionService {
    Calificacion obtener(Pedido pedido);
    void nueva(Calificacion calificacion);
}
