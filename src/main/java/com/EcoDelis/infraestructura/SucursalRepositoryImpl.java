package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.dominio.SucursalRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("SucursalRepository")
public class SucursalRepositoryImpl implements SucursalRepository {


    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void guardar(Sucursal sucursal) {
        sessionFactory.getCurrentSession().save(sucursal);
    }
}
