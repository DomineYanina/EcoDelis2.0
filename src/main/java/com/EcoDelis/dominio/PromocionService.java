package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PromocionService {

    void agregarPromocion(Promocion promocion);

    void modificarPromocion(Promocion promocion);

    void eliminarPromocion(Promocion promocion);

}
