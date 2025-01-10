package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.dominio.SucursalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository("SucursalRepository")
public class SucursalRepositoryImpl implements SucursalRepository {


    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void guardar(Sucursal sucursal) {
        sessionFactory.getCurrentSession().save(sucursal);
    }

    @Override
    public Sucursal buscarPorNombre(String nombre){
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Sucursal WHERE nombre = :nombre";
        try {
            return session.createQuery(query, Sucursal.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void modificar(Sucursal sucursal) {
        sessionFactory.getCurrentSession().update(sucursal);
    }
}
