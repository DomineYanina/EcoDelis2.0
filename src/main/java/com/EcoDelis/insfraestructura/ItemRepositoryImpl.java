package com.EcoDelis.insfraestructura;

import com.EcoDelis.dominio.Item;
import com.EcoDelis.dominio.ItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ItemRepository")
public class ItemRepositoryImpl implements ItemRepository {

    SessionFactory sessionFactory;

    @Override
    public Item buscarItemPorId(int id) {
        Item item = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Item i WHERE i.id = :id";
            item = session.createQuery(hql, Item.class)
                    .setParameter("id", id) // Convertir el parámetro a mayúsculas
                    .uniqueResult(); // Obtener un único resultado
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void actualizarItem(Item item) {
        sessionFactory.getCurrentSession().update(item);
    }

    @Override
    public List<Item> obtenerListaDeItemsPorsucursal(long id) {
        List<Item> items = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Item i WHERE i.sucursal_id = :id";
            items = session.createQuery(hql, Item.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }
}
