package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("PromocionRepository")
public class PromocionRepositoryImpl implements PromocionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public PromocionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregarPromocion(Promocion promocion) {
        sessionFactory.getCurrentSession().save(promocion);
    }
}
