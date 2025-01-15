package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
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
    private ModelAndView irAAgregarSucursal(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            LocalLoginViewModel vm = new LocalLoginViewModel();
            mv.addObject("local", vm);
        } else {
            mv = new ModelAndView("agregarSucursal");
            mv.addObject("localLogueado", session.getAttribute("localLogueado"));
            RegistroSucursalViewModel registroSucursalViewModel = new RegistroSucursalViewModel();
            ResponsableViewModel responsableViewModel = new ResponsableViewModel();
            DireccionSucursalViewModel direccionSucursalViewModel = new DireccionSucursalViewModel();
            mv.addObject("sucursal", registroSucursalViewModel);
            mv.addObject("responsable", responsableViewModel);
            mv.addObject("direccionSucursal", direccionSucursalViewModel);
            mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
            mv.addObject("localidades", Localidad.values());
            mv.addObject("provincias", Provincia.values());
            mv.addObject("tiposDocumento", TipoDocumento.values());
        }
        return mv;
    }

    @PostMapping("/registrarSucursal")
    private ModelAndView registrarSucursal(@ModelAttribute("sucursal") RegistroSucursalViewModel registroSucursalViewModel,
                                           @ModelAttribute("responsable") ResponsableViewModel responsableViewModel,
                                           @ModelAttribute("direccionSucursal") DireccionSucursalViewModel direccionSucursalViewModel,
                                           BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;

        // Verificar si el usuario está logueado
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            LocalLoginViewModel vm = new LocalLoginViewModel();
            mv.addObject("local", vm);
        } else {
            if (bindingResult.hasErrors()) {
                mv = new ModelAndView("agregarSucursal");
                mv.addObject("error", "Hay errores en el formulario.");
                mv.addObject("sucursal", registroSucursalViewModel);
                mv.addObject("responsable", responsableViewModel);
                mv.addObject("direccionSucursal", direccionSucursalViewModel);
                mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
                mv.addObject("localidades", Localidad.values());
                mv.addObject("provincias", Provincia.values());
                mv.addObject("tiposDocumento", TipoDocumento.values());
            } else {
                // Validar si el nombre de la sucursal ya existe
                if(sucursalService.nombreDeSucursalYaExiste(registroSucursalViewModel)) {
                    mv = new ModelAndView("agregarSucursal");
                    bindingResult.rejectValue("nombre", "nombre", "La sucursal ya existe");
                    mv.addObject("error", "La sucursal ingresada ya existe");
                    mv.addObject("sucursal", registroSucursalViewModel);
                    mv.addObject("responsable", responsableViewModel);
                    mv.addObject("direccionSucursal", direccionSucursalViewModel);
                    mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
                    mv.addObject("localidades", Localidad.values());
                    mv.addObject("provincias", Provincia.values());
                    mv.addObject("tiposDocumento", TipoDocumento.values());
                } else {
                    // Registrar la nueva sucursal
                    Sucursal sucursal = sucursalService.registrar(registroSucursalViewModel, responsableViewModel, direccionSucursalViewModel);
                    mv = new ModelAndView("homeLocal");
                    mv.addObject("localLogueado", session.getAttribute("localLogueado"));
                }
            }
        }

        return mv;
    }


    @GetMapping("/irAEliminarSucursal")
    private ModelAndView irAEliminarSucursal(@ModelAttribute("sucursal") SucursalViewModel sucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
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
            mv = new ModelAndView("loginLocal");
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
