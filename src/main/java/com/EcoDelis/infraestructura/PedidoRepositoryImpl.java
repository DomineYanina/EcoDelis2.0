package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.Pedido;
import com.EcoDelis.dominio.PedidoRepository;
import com.EcoDelis.dominio.Promocion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void agregar(Pedido pedido) {
        sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public void actualizar(Pedido pedido) {
        sessionFactory.getCurrentSession().update(pedido);
    }

    @Override
    public void cancelar(Pedido pedido) {
        sessionFactory.getCurrentSession().delete(pedido);
    }

    @Override
    public Pedido buscarPorId(Long pedidoId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Pedido WHERE id = :pedidoId";

        try {
            return session.createQuery(query, Pedido.class)
                    .setParameter("pedidoId", pedidoId)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Promocion> obtenerPromocionesPorPedido(Long pedidoId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT p.promociones FROM Pedido p WHERE p.id = :pedidoId";
        return session.createQuery(query, Promocion.class)
                .setParameter("pedidoId", pedidoId)
                .getResultList();
    }

}
