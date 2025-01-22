package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.HorarioRetiro;
import com.EcoDelis.dominio.HorarioRetiroRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("HorarioRetiroRepository")
public class HorarioRetiroRepositoryImpl implements HorarioRetiroRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public HorarioRetiroRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregar(HorarioRetiro horarioRetiro) {
        sessionFactory.getCurrentSession().save(horarioRetiro);
    }

    @Override
    public void modificar(HorarioRetiro horarioRetiro) {
        sessionFactory.getCurrentSession().update(horarioRetiro);
    }

    @Override
    public void eliminar(HorarioRetiro horarioRetiro) {
        sessionFactory.getCurrentSession().delete(horarioRetiro);
    }
}
