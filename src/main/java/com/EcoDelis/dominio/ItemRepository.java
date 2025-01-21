package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    Item buscarItemPorId(int id);

    void actualizarItem(Item item);

    List<Item> obtenerListaDeItemsPorsucursal(long id);
}
