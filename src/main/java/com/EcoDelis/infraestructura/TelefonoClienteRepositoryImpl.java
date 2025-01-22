package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.TelefonoCliente;
import com.EcoDelis.dominio.TelefonoClienteRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class TelefonoClienteRepositoryImpl implements TelefonoClienteRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public TelefonoClienteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void agregar(TelefonoCliente telefonoCliente) {
        sessionFactory.getCurrentSession().save(telefonoCliente);
    }

    @Override
    @Transactional
    public void modificar(TelefonoCliente telefonoCliente) {
        sessionFactory.getCurrentSession().update(telefonoCliente);
    }

    @Override
    @Transactional
    public void eliminar(TelefonoCliente telefonoCliente) {
        sessionFactory.getCurrentSession().delete(telefonoCliente);
    }
}
