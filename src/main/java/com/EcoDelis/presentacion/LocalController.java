package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    private DireccionSucursalService direccionSucursalService;

    @GetMapping("/irAAgregarSucursal")
    private ModelAndView irAAgregarSucursal(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            LocalLoginViewModel vm = new LocalLoginViewModel();
            mv.addObject("local", vm);
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            String nombre = local.getNombre();
            System.out.println(nombre);

            mv = new ModelAndView("agregarSucursal");
            mv.addObject("localLogueado", session.getAttribute("localLogueado"));
            RegistroSucursalViewModel registroSucursalViewModel = new RegistroSucursalViewModel();
            DireccionSucursalViewModel direccionSucursalViewModel = new DireccionSucursalViewModel();
            mv.addObject("sucursal", registroSucursalViewModel);
            mv.addObject("direccionSucursal", direccionSucursalViewModel);
            mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
            mv.addObject("localidades", Localidad.values());
            mv.addObject("provincias", Provincia.values());
            mv.addObject("tipoLocal", TipoLocal.values());
        }
        return mv;
    }

    @PostMapping("/registrarSucursal")
    private ModelAndView registrarSucursal(@ModelAttribute("sucursal") RegistroSucursalViewModel registroSucursalViewModel,
                                           @ModelAttribute("direccionSucursal") DireccionSucursalViewModel direccionSucursalViewModel,
                                           BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;

        // Verificar si el usuario está logueado
        if (session.getAttribute("localLogueado") == null) {
            System.out.println("No hay local logueado");
            mv = new ModelAndView("loginLocal");
            LocalLoginViewModel vm = new LocalLoginViewModel();
            mv.addObject("local", vm);
        } else {
            if (bindingResult.hasErrors()) {
                mv = new ModelAndView("agregarSucursal");
                mv.addObject("error", "Hay errores en el formulario.");
                mv.addObject("sucursal", registroSucursalViewModel);
                mv.addObject("direccionSucursal", direccionSucursalViewModel);
                mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
                mv.addObject("localidades", Localidad.values());
                mv.addObject("provincias", Provincia.values());
                mv.addObject("tiposDocumento", TipoDocumento.values());
                mv.addObject("tipoLocal", TipoLocal.values());
            } else {
                // Validar si el nombre de la sucursal ya existe
                if(sucursalService.nombreDeSucursalYaExiste(registroSucursalViewModel)) {
                    mv = new ModelAndView("agregarSucursal");
                    bindingResult.rejectValue("nombre", "nombre", "La sucursal ya existe");
                    mv.addObject("error", "La sucursal ingresada ya existe");
                    mv.addObject("sucursal", registroSucursalViewModel);
                    mv.addObject("direccionSucursal", direccionSucursalViewModel);
                    mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
                    mv.addObject("localidades", Localidad.values());
                    mv.addObject("provincias", Provincia.values());
                    mv.addObject("tiposDocumento", TipoDocumento.values());
                    mv.addObject("tipoLocal", TipoLocal.values());
                    System.out.println("Ya existe el nombre de la sucursal");
                } else {
                    // Registrar la nueva sucursal
                    System.out.println("No existe el nombre de la sucursal");
                    direccionSucursalService.agregar(registroSucursalViewModel.getDireccion());
                    DireccionSucursal direccion = registroSucursalViewModel.getDireccion();
                    Local local = (Local) session.getAttribute("localLogueado");
                    Sucursal sucursal = sucursalService.registrar(registroSucursalViewModel, direccion, local);
                    direccion.setSucursal(sucursal);
                    direccionSucursalService.modificar(direccion);
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

    @GetMapping("/irAModificarDatosLocal")
    private ModelAndView irAModificarDatosLocal(HttpSession session){
        if(session.getAttribute("localLogueado") == null) {
            return new ModelAndView("loginLocal");
        } else {
            Local localLogueado = (Local) session.getAttribute("localLogueado");
            String email = localLogueado.getEmail();

            ModelAndView mv = new ModelAndView("modificarDatosLocal");
            Local local = localService.buscarPorEmail(email);

            RegistroLocalViewModel resp = new RegistroLocalViewModel();
            resp.setCUIT(local.getCUIT());
            resp.setNombre(local.getNombre());
            resp.setEmail(local.getEmail());

            mv.addObject("local", resp);

            return mv;
        }
    }

    @PostMapping("/modificarDatosLocal")
    private ModelAndView modificarDatosLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv = new ModelAndView("modificarDatosLocal");
        Local localExistente = localService.buscarPorEmail(registroLocalViewModel.getEmail());
        localExistente.setNombre(registroLocalViewModel.getNombre());
        localExistente.setEmail(registroLocalViewModel.getEmail());
        localExistente.setCUIT(registroLocalViewModel.getCUIT());

        localService.modificar(localExistente);

        return new ModelAndView("homeLocal");
    }
}
