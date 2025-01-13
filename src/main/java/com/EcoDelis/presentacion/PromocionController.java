package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionService;
import com.EcoDelis.dominio.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PromocionController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private PromocionService promocionService;

    @GetMapping("/irACrearNuevaPromocion")
    public ModelAndView irACrearNuevaPromocion(HttpSession httpSession) {
        if (httpSession.getAttribute("localLogueado") == null) {
            return new ModelAndView("redirect:/loginLocal");
        } else {
            PromocionViewModel promocionViewModel = new PromocionViewModel();
            ModelAndView modelAndView = new ModelAndView("agregarPromocion");
            modelAndView.addObject("promocion", promocionViewModel);
            return modelAndView;
        }
    }

    @PostMapping("/crearNuevaPromocion")
    public ModelAndView crearNuevaPromocion(HttpSession httpSession, PromocionViewModel promocionViewModel) {
        if (httpSession.getAttribute("localLogueado") == null) {
            return new ModelAndView("redirect:/loginLocal");
        } else {
            Promocion promocion = new Promocion();
            promocion.setNombre(promocionViewModel.getNombre());
            promocion.setDescuento(promocion.getDescuento());
            promocion.setItems(promocionViewModel.getItems());
            promocion.setPrecio_original(promocionViewModel.getPrecio_original());
            promocion.setPrecio_final(promocionViewModel.getPrecio_final());
            promocion.setUnidadesRestantes(promocionViewModel.getUnidadesRestantes());
            promocion.setSucursal(promocionViewModel.getSucursal());

            promocionService.agregarPromocion(promocion);
            return new ModelAndView("redirect:/homeSucursal");
        }
    }

}
