package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionSucursal;
import com.EcoDelis.dominio.DireccionSucursalRepository;
import com.EcoDelis.dominio.Sucursal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("DireccionSucursalRepository")
public class DireccionSucursalRepositoryImpl implements DireccionSucursalRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public DireccionSucursalRepositoryImpl(SessionFactory sessionFactory) {}

    @Override
    @Transactional
    public void agregar(DireccionSucursal direccionSucursal) {
        sessionFactory.getCurrentSession().save(direccionSucursal);
    }

    @Override
    @Transactional
    public void modificar(DireccionSucursal direccionSucursal) {
        sessionFactory.getCurrentSession().update(direccionSucursal);
    }

    @Override
    @Transactional
    public void eliminar(DireccionSucursal direccionSucursal) {
        sessionFactory.getCurrentSession().delete(direccionSucursal);
    }
}
