package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

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
    public Sucursal buscarSucursalPorNombre(String nombre, Local local) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Sucursal WHERE nombre = :nombre AND local = :local";
        try{
            return session.createQuery(query, Sucursal.class)
                    .setParameter("nombre", nombre)
                    .setParameter("local", local)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void eliminarSucursal(Sucursal sucursal) {
        sessionFactory.getCurrentSession().delete(sucursal);
    }

    @Override
    public void modificar(Local localExistente) {
        sessionFactory.getCurrentSession().update(localExistente);
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorLocal(Local local) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Sucursal WHERE local = :local";
        try{
            return session.createQuery(query, Sucursal.class)
                    .setParameter("local", local)
                    .getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Local> filtrarLocalesPorTipoLocal(TipoLocal tipoLocal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT DISTINCT l FROM Local l JOIN l.sucursales s WHERE s.tipoLocal = :tipoLocal";
        try{
            return session.createQuery(query, Local.class)
                    .setParameter("tipoLocal", tipoLocal)
                    .getResultList();
        } catch (NoResultException e){
            return null;
        }
    }


}
