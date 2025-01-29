package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    Item buscarItemPorId(long id);

    void actualizarItem(Item item);

    List<Item> obtenerListaDeItemsPorsucursal(long id);

    void agregarItem(Item item);

    void eliminarItem(Item item);
}
