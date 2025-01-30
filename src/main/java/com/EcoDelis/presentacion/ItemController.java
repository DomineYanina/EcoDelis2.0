package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Item;
import com.EcoDelis.dominio.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/irAAgregarItem")
    public ModelAndView irAAgregarItem(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null){
            if(session.getAttribute("clienteLogueado") == null){
                mv = new ModelAndView("loginLocal");
                mv.addObject("local",new LocalLoginViewModel());
            } else {
                mv = new ModelAndView("homeCliente");
            }
        } else {
            mv = new ModelAndView("agregarItem");
            mv.addObject("item", new ItemViewModel());
            mv.addObject("sucursal", sucursalViewModel);
        }
        return mv;
    }

    @PostMapping("/agregarItem")
    public ModelAndView agregarItem(ItemViewModel itemViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        itemService.agregar(transformarModeloAItem(itemViewModel));
        return mv;
    }

    @GetMapping("/irAModificarItem")
    public ModelAndView irAModificarItem(HttpSession session, Item item){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null){
            if(session.getAttribute("clienteLogueado") == null){
                mv = new ModelAndView("loginLocal");
                mv.addObject("local",new LocalLoginViewModel());
            } else {
                mv = new ModelAndView("homeCliente");
            }
        } else {
            mv = new ModelAndView("modificarItem");
            mv.addObject("item", transformarItemAModelo(itemService.buscarPorId(item.getId())));
        }
        return mv;
    }

    @PutMapping("/modificarItem")
    public ModelAndView modificarItem(ItemViewModel itemViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        itemService.actualizarItem(transformarModeloAItem(itemViewModel));
        return mv;
    }

    //Métodos auxiliares

    private Item transformarModeloAItem(ItemViewModel itemViewModel){
        Item item = new Item();
        item.setNombre(itemViewModel.getNombre());
        item.setPrecio(itemViewModel.getPrecio());
        item.setStock(itemViewModel.getStock());
        item.setSucursal(itemViewModel.getSucursal());
        return item;
    }

    private ItemViewModel transformarItemAModelo(Item item){
        ItemViewModel itemViewModel = new ItemViewModel();
        itemViewModel.setNombre(item.getNombre());
        itemViewModel.setPrecio(item.getPrecio());
        itemViewModel.setStock(item.getStock());
        itemViewModel.setSucursal(item.getSucursal());
        return itemViewModel;
    }
}
