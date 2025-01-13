package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.TelefonoSucursal;
import com.EcoDelis.dominio.TelefonoSucursalRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("TelefonoSucursalRepository")
public class TelefonoSucursalRepositoryImpl implements TelefonoSucursalRepository {

    SessionFactory sessionFactory;

    public TelefonoSucursalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregar(TelefonoSucursal telefonoSucursal) {
        sessionFactory.getCurrentSession().save(telefonoSucursal);
    }
}
