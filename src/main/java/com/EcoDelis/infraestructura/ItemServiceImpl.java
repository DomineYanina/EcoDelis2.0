package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Item;
import com.EcoDelis.dominio.ItemRepository;
import com.EcoDelis.dominio.ItemService;
import com.EcoDelis.dominio.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Item buscarPorId(long id) {
        return itemRepository.buscarItemPorId(id);
    }

    @Override
    public void actualizarItem(Item item) {
        itemRepository.actualizarItem(item);
    }

    @Override
    public List<Item> obtenerListaDeItemsPorSucursal(Sucursal sucursal) {
        return itemRepository.obtenerListaDeItemsPorsucursal(sucursal.getId());
    }

    @Override
    public void agregar(Item item) {
        itemRepository.agregarItem(item);
    }
}
