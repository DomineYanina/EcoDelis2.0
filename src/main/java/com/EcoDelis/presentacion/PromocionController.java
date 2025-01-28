package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.PromocionService;
import com.EcoDelis.dominio.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping("/irACrearNuevaPromocion")
    public ModelAndView irACrearNuevaPromocion(HttpSession httpSession) {
        ModelAndView mv;
        if (httpSession.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("agregarPromocion");
            mv.addObject("promocion", new PromocionViewModel());
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

    @PutMapping("/modificarPromocion")
    public ModelAndView modificarPromocion(@ModelAttribute PromocionViewModel promocionViewModel, HttpSession session){
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

}
