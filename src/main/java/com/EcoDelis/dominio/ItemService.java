package com.EcoDelis.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item buscarPorId(int id){
        return itemRepository.buscarItemPorId(id);
    }

    public void actualizarItem(Item item){
        itemRepository.actualizarItem(item);
    }

    public List<Item> obtenerListaDeItemsPorSucursal(Sucursal sucursal){
        return itemRepository.obtenerListaDeItemsPorsucursal(sucursal.getId());
    }
}
