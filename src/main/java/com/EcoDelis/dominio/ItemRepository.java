package com.EcoDelis.dominio;

import java.util.List;

public interface ItemRepository {

    Item buscarItemPorId(int id);

    void actualizarItem(Item item);

    List<Item> obtenerListaDeItemsPorsucursal(long id);
}
