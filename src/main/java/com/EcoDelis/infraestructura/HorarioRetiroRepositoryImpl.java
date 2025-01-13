package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.HorarioRetiro;
import com.EcoDelis.dominio.HorarioRetiroRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("HorarioRetiroRepository")
public class HorarioRetiroRepositoryImpl implements HorarioRetiroRepository {

    SessionFactory sessionFactory;

    public HorarioRetiroRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregar(HorarioRetiro horarioRetiro) {
        sessionFactory.getCurrentSession().save(horarioRetiro);
    }
}
