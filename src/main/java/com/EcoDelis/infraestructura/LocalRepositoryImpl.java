package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.LocalRepository;
import com.EcoDelis.dominio.Sucursal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
public class LocalRepositoryImpl implements LocalRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public LocalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Local buscarLocal(String email, String password) {
        final Session session = sessionFactory.getCurrentSession();
        return (Local) session.createCriteria(Local.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public Local buscarPorEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Local WHERE email = :email";

        try {
            return session.createQuery(query, Local.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void guardar(Local local) {
        sessionFactory.getCurrentSession().save(local);
    }

    @Override
    public Sucursal buscarSucursalPorNombre(String nombre) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Sucursal WHERE nombre = :nombre";
        try{
            return session.createQuery(query, Sucursal.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void eliminarSucursal(Sucursal sucursal) {
        sessionFactory.getCurrentSession().delete(sucursal);
    }


}
