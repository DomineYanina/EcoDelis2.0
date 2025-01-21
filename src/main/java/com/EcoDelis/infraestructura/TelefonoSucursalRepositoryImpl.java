package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.TelefonoSucursal;
import com.EcoDelis.dominio.TelefonoSucursalRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("TelefonoSucursalRepository")
public class TelefonoSucursalRepositoryImpl implements TelefonoSucursalRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public TelefonoSucursalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregar(TelefonoSucursal telefonoSucursal) {
        sessionFactory.getCurrentSession().save(telefonoSucursal);
    }
}
