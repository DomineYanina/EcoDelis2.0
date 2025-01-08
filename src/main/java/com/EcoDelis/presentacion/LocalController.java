package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.LocalService;
import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.dominio.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LocalController {
    @Autowired
    private LocalService localService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/irAAgregarSucursal")
    private ModelAndView irAAgregarSucursal(@ModelAttribute("sucursal") RegistroSucursalViewModel registroSucursalViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/login-local");
        } else {
            mv = new ModelAndView("agregarSucursal");
            mv.addObject("localLogueado", session.getAttribute("localLogueado"));
            mv.addObject("sucursal", registroSucursalViewModel);
        }
        return mv;
    }

    @PostMapping("/registrarSucursal")
    private ModelAndView registrarSucursal(@ModelAttribute("sucursal") RegistroSucursalViewModel registroSucursalViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/login-local");
        } else {
            Sucursal sucursal = sucursalService.registrar(registroSucursalViewModel);
            mv = new ModelAndView("agregarSucursal");
        }
        return mv;
    }
}
