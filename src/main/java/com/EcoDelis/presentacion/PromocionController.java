package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/irACrearNuevaPromocion")
    public ModelAndView irACrearNuevaPromocion(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession httpSession) {
        ModelAndView mv;
        if (httpSession.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("agregarPromocion");
            mv.addObject("promocion", new PromocionViewModel());
            mv.addObject("items", sucursalService.obtenerItemsPorSucursal(transformarModeloASucursal(sucursalViewModel)));
        }
        return mv;
    }

    @PostMapping("/crearNuevaPromocion")
    public ModelAndView crearNuevaPromocion(HttpSession httpSession, PromocionViewModel promocionViewModel) {
        ModelAndView mv;
        if (httpSession.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            promocionService.agregarPromocion(transformarModeloAPromocion(promocionViewModel));
            mv = new ModelAndView("homeSucursal");
        }
        return mv;
    }

    @GetMapping("/irAModificarPromocion")
    public ModelAndView irAModificarPromocion(@ModelAttribute SucursalViewModel sucursalViewModel, @ModelAttribute PromocionViewModel promocionViewModel, HttpSession httpSession) {
        ModelAndView mv;
        if (httpSession.getAttribute("localLogueado") == null) {
            if(httpSession.getAttribute("clienteLogueado") == null) {
                mv = new ModelAndView("loginLocal");
                mv.addObject("local", new LocalLoginViewModel());
            } else {
                mv = new ModelAndView("homeCliente");
            }
        } else {
            mv = new ModelAndView("modificarPromocion");
            mv.addObject("promocion", promocionViewModel);
            mv.addObject("items", sucursalService.obtenerItemsPorSucursal(transformarModeloASucursal(sucursalViewModel)));
        }
        return mv;
    }

    @PutMapping("/modificarPromocion")
    public ModelAndView modificarPromocion(@ModelAttribute PromocionViewModel promocionViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        promocionService.modificarPromocion(transformarModeloAPromocion(promocionViewModel));
        return mv;
    }

    //Métodos auxiliares

    private Promocion transformarModeloAPromocion(PromocionViewModel promocionViewModel) {
        Promocion promocion = new Promocion();
        promocion.setNombre(promocionViewModel.getNombre());
        promocion.setDescuento(promocion.getDescuento());
        promocion.setItems(promocionViewModel.getItems());
        promocion.setPrecio_original(promocionViewModel.getPrecio_original());
        promocion.setPrecio_final(promocionViewModel.getPrecio_final());
        promocion.setUnidadesRestantes(promocionViewModel.getUnidadesRestantes());
        promocion.setSucursal(promocionViewModel.getSucursal());
        return promocion;
    }

    private Sucursal transformarModeloASucursal(SucursalViewModel sucursalViewModel){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        sucursal.setHorarioRetiros(sucursalViewModel.getHorarioRetiros());
        sucursal.setPromociones(sucursalViewModel.getPromociones());
        sucursal.setItems(sucursalViewModel.getItems());
        sucursal.setPedidos(sucursalViewModel.getPedidos());
        return sucursal;
    }

}
