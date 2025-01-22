package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionCliente;
import com.EcoDelis.dominio.DireccionClienteRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionClienteRepositoryImpl implements DireccionClienteRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public DireccionClienteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregar(DireccionCliente direccionCliente) {
        sessionFactory.getCurrentSession().save(direccionCliente);
    }

    @Override
    public void modificar(DireccionCliente direccionCliente) {
        sessionFactory.getCurrentSession().update(direccionCliente);
    }

    @Override
    public void eliminar(DireccionCliente direccionCliente) {
        sessionFactory.getCurrentSession().delete(direccionCliente);
    }
}
