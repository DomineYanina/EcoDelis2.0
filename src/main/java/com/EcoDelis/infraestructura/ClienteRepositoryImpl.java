package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteRepository;
import com.EcoDelis.dominio.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository("ClienteRepository")
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public ClienteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cliente buscarCliente(String email, String password){
        final Session session = sessionFactory.getCurrentSession();
        return (Cliente) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public Cliente buscarPorEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Cliente WHERE email = :email";
        try {
            return session.createQuery(query, Cliente.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        sessionFactory.getCurrentSession().save(cliente);
    }

    @Override
    public Cliente obtenerCliente(Long id){
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Cliente WHERE id = :id";
        return session.createQuery(query, Cliente.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public void modificar(Cliente cliente){
        sessionFactory.getCurrentSession().update(cliente);
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado) {
        Session session = sessionFactory.getCurrentSession();
        Long idCliente = clienteLogueado.getId();
        String query = "FROM Pedido WHERE cliente = :clienteLogueado";
        return session.createQuery(query, Pedido.class)
                .setParameter("clienteLogueado", clienteLogueado)
                .getResultList();
    }

}
