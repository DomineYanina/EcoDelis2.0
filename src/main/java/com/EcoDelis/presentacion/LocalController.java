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
            if(sucursalService.nombreDeSucursalYaExiste(registroSucursalViewModel)){
                mv = new ModelAndView("agregarSucursal");
                bindingResult.rejectValue("nombre", "nombre", "La sucursal ya existe");
                mv.addObject("error", "La sucursal ingresada ya existe");
                mv.addObject("sucursal", registroSucursalViewModel);
            } else {
                Sucursal sucursal = sucursalService.registrar(registroSucursalViewModel);
                mv = new ModelAndView("home-local");
            }
        }
        return mv;
    }

    @GetMapping("/irAEliminarSucursal")
    private ModelAndView irAEliminarSucursal(@ModelAttribute("sucursal") SucursalViewModel sucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            mv = new ModelAndView("eliminarSucursal");
            mv.addObject("sucursal", sucursalViewModel);
        }
        return mv;
    }

    @PostMapping ("/eliminarSucursal")
    private ModelAndView eliminarSucursal(@ModelAttribute("sucursal") SucursalViewModel sucursalViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            if(localService.existeSucursal(sucursalViewModel)){
                mv = new ModelAndView("homeLocal");
                localService.eliminarSucursal(sucursalViewModel);
            } else {
                bindingResult.rejectValue("nombre", "La sucursal ingresada no existe");
                mv = new ModelAndView("eliminarSucursal");
                mv.addObject("sucursal", sucursalViewModel);
            }
        }
        return mv;
    }
}
