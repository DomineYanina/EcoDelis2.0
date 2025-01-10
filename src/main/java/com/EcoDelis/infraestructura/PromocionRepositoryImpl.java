package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("PromocionRepository")
public class PromocionRepositoryImpl implements PromocionRepository {

    private SessionFactory sessionFactory;

    @Override
    public void agregarPromocion(Promocion promocion) {
        sessionFactory.getCurrentSession().save(promocion);
    }
}
