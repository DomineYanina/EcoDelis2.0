package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionSucursal;
import com.EcoDelis.dominio.DireccionSucursalRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DireccionSucursalRepository")
public class DireccionSucursalRepositoryImpl implements DireccionSucursalRepository {

    SessionFactory sessionFactory;

    @Autowired
    public DireccionSucursalRepositoryImpl(SessionFactory sessionFactory) {}

    @Override
    public void agregar(DireccionSucursal direccionSucursal) {
        sessionFactory.getCurrentSession().save(direccionSucursal);
    }
}
