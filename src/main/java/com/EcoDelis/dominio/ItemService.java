package com.EcoDelis.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface ItemService {

    public Item buscarPorId(long id);

    public void actualizarItem(Item item);

    public List<Item> obtenerListaDeItemsPorSucursal(Sucursal sucursal);

    public void agregar(Item item);
}
