package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Pedido;
import com.EcoDelis.dominio.PedidoRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
