package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository("SucursalRepository")
public class SucursalRepositoryImpl implements SucursalRepository {

    @Autowired
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
    @Transactional
    public void modificar(Sucursal sucursal) {
        sessionFactory.getCurrentSession().update(sucursal);
    }

    @Override
    @Transactional
    public void eliminar(Sucursal sucursal) {
        sessionFactory.getCurrentSession().delete(sucursal);
    }

    @Override
    public List<Pedido> obtenerPedidos(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Pedido WHERE sucursal = :sucursal";
        try{
            return session.createQuery(query, Pedido.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Promocion> obtenerPromocionesPorSucursal(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Promocion WHERE sucursal = :sucursal";
        try{
            return session.createQuery(query, Promocion.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Pedido> obtenerPedidosNoConfirmados(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Pedido WHERE sucursal = :sucursal AND estado = 'Creado'";
        try{
            return session.createQuery(query, Pedido.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Pedido> obtenerPedidosConfirmados(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Pedido WHERE sucursal = :sucursal AND estado = 'Confirmado'";
        try{
            return session.createQuery(query, Pedido.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Pedido> obtenerPedidosEntregados(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Pedido WHERE sucursal = :sucursal AND estado = 'Entregado'";
        try{
            return session.createQuery(query, Pedido.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Item> obtenerItemsPorSucursal(Sucursal sucursal) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Item WHERE sucursal = :sucursal";
        try{
            return session.createQuery(query, Item.class)
                    .setParameter("sucursal", sucursal)
                    .getResultList();
            } catch (NoResultException e){
            return null;
        }
    }
}
