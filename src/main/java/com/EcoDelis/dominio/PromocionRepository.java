package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository {

    void agregarPromocion(Promocion promocion);

    void modificar(Promocion promocion);

    void eliminar(Promocion promocion);

}
