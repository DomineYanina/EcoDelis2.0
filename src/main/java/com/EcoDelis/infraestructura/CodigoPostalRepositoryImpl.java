package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.CodigoPostal;
import com.EcoDelis.dominio.CodigoPostalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CodigoPostalRepositoryImpl implements CodigoPostalRepository {


    @Autowired
    private SessionFactory sessionFactory;

    public CodigoPostalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CodigoPostal> filtrarCodigoPostal(String codigoPostal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM CodigoPostal WHERE codigoPostal = :codigoPostal";
        try {
            return session.createQuery(query, CodigoPostal.class)
                    .setParameter("codigoPostal", codigoPostal)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
