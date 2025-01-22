package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CalificacionRepositoryImpl implements CalificacionRepository {

    @Autowired
    private SessionFactory sessionFactory;

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

    @Override
    public List<Calificacion> obtenerCalificacionesPorSucursal(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Calificacion WHERE sucursal = :sucursal";
        try{
            return session.createQuery(query, Calificacion.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Calificacion> obtenerCalificacionesPorCliente(Cliente cliente) {
        Session session = sessionFactory.getCurrentSession();;
        String query = "FROM Calificacion WHERE cliente = :cliente";
        try{
            return session.createQuery(query, Calificacion.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
