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
        String query = "FROM Pedido WHERE cliente = :clienteLogueado";
        return session.createQuery(query, Pedido.class)
                .setParameter("clienteLogueado", clienteLogueado)
                .getResultList();
    }

    @Override
    public List<Calificacion> obtenerCalificacionesDadasPorCliente(Cliente cliente) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Calificacion WHERE cliente = :cliente";
        try{
            return session.createQuery(query,Calificacion.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<DireccionCliente> obtenerDireccionesPorCliente(Cliente cliente) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM DireccionCliente WHERE cliente = :cliente";
        try{
            return session.createQuery(query,DireccionCliente.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<TelefonoCliente> obtenerTelefonosPorCliente(Cliente cliente) {
        Session session = sessionFactory.getCurrentSession();
        String query = "From TelefonoCliente WHERE cliente = :cliente";
        try{
            return session.createQuery(query, TelefonoCliente.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public DireccionCliente chequearDireccionYaExistente(DireccionCliente direccionCliente, Cliente clienteLogueado) {
        Session session = sessionFactory.getCurrentSession();
        String calle = direccionCliente.getCalle();
        String provincia = direccionCliente.getProvincia();
        long numero = direccionCliente.getNumero();
        long cp = direccionCliente.getCodigopostal();
        String localidad = direccionCliente.getLocalidad();
        String query = "FROM DireccionCliente WHERE cliente = :clienteLogueado AND codigopostal = :cp AND calle = :calle AND provincia = :provincia AND numero = :numero AND localidad = :localidad";
        try{
            return session.createQuery(query, DireccionCliente.class)
                    .setParameter("clienteLogueado", clienteLogueado)
                    .setParameter("calle", calle)
                    .setParameter("provincia", provincia)
                    .setParameter("cp", cp)
                    .setParameter("numero", numero)
                    .setParameter("localidad", localidad)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public TelefonoCliente chequearTelefonoYaExistente(TelefonoCliente telefonoCliente) {
        Session session = sessionFactory.getCurrentSession();
        TipoTelefono tipo = telefonoCliente.getTipo();
        long numero = telefonoCliente.getNumero();
        Cliente cliente = telefonoCliente.getCliente();
        String query = "FROM TelefonoCliente WHERE tipo = :tipo AND numero = :numero AND cliente = :cliente";
        try{
            return session.createQuery(query, TelefonoCliente.class)
                    .setParameter("cliente", cliente)
                    .setParameter("tipo", tipo)
                    .setParameter("numero", numero)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

}
