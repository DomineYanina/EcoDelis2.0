package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("PromocionService")
@Transactional
public class PromocionServiceImpl {

    @Autowired
    private PromocionRepository promocionRepository;


    public void agregarPromocion(Promocion promocion){
        promocionRepository.agregarPromocion(promocion);
    }
}
