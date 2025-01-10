package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private LocalService localService;

    @PostMapping
    public ModelAndView modificarTipoDePromocion(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session, TipoSuscripcionSucursal tipoDeSuscripcionSucursal) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            Sucursal sucursal = localService.buscarSucuralPorNombre(sucursalViewModel.getNombre());
            sucursal.setTipoSuscripcion(tipoDeSuscripcionSucursal);

            sucursalService.modificar(sucursal);

            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }
}
