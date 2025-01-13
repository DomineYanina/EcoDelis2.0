package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionRepository;
import com.EcoDelis.dominio.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    public PromocionServiceImpl(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public void agregarPromocion(Promocion promocion){
        promocionRepository.agregarPromocion(promocion);
    }
}
