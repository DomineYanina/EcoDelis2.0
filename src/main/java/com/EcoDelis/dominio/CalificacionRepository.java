package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository {
    Calificacion obtener(Pedido pedido);

    void agregar(Calificacion calificacion);
}
