package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Calificacion;
import com.EcoDelis.dominio.CalificacionRepository;
import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CalificacionRepositoryImpl implements CalificacionRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public CalificacionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Calificacion obtener(Pedido pedido) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Calificacion WHERE pedido = :pedido";

        try {
            return session.createQuery(query, Calificacion.class)
                    .setParameter("pedido", pedido)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void agregar(Calificacion calificacion) {
        sessionFactory.getCurrentSession().save(calificacion);
    }
}
