package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.LocalService;
import com.EcoDelis.dominio.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LocalController {
    @Autowired
    private LocalService localService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/agregarSucursal")
    private ModelAndView agregarSucursal(@ModelAttribute("sucursal") RegistroSucursalViewModel registroSucursalViewModel, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("localLogueado") == null) {
            // Redirigir al login si el usuario no está logueado
            return new ModelAndView("redirect:/login-local");
        } else {
            ModelAndView mv = new ModelAndView("agregarSucursal");
        }

    }
}
