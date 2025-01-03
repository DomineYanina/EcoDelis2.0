package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.LocalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class LocalRepositoryImpl implements LocalRepository {
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
}
