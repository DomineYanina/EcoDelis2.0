package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

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
                /*mv.addObject("localidades", Localidad.values());
                mv.addObject("provincias", Provincia.values());*/
                mv.addObject("tiposDocumento", TipoDocumento.values());
                mv.addObject("tipoLocal", TipoLocal.values());
            } else {
                // Validar si el nombre de la sucursal ya existe
                Local local = (Local) session.getAttribute("localLogueado");
                if(sucursalService.nombreDeSucursalYaExiste(registroSucursalViewModel, local)) {
                    mv = new ModelAndView("agregarSucursal");
                    bindingResult.rejectValue("nombre", "nombre", "La sucursal ya existe");
                    mv.addObject("error", "La sucursal ingresada ya existe");
                    mv.addObject("sucursal", registroSucursalViewModel);
                    mv.addObject("direccionSucursal", direccionSucursalViewModel);
                    mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
                    /*mv.addObject("localidades", Localidad.values());
                    mv.addObject("provincias", Provincia.values());*/
                    mv.addObject("tiposDocumento", TipoDocumento.values());
                    mv.addObject("tipoLocal", TipoLocal.values());
                    System.out.println("Ya existe el nombre de la sucursal");
                } else {
                    // Registrar la nueva sucursal
                    System.out.println("No existe el nombre de la sucursal");
                    direccionSucursalService.agregar(registroSucursalViewModel.getDireccion());
                    DireccionSucursal direccion = registroSucursalViewModel.getDireccion();
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

    @PutMapping("/modificarDatosLocal")
    private ModelAndView modificarDatosLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("modificarDatosLocal");
        Local localExistente = localService.buscarPorEmail(registroLocalViewModel.getEmail());
        localExistente.setNombre(registroLocalViewModel.getNombre());
        localExistente.setEmail(registroLocalViewModel.getEmail());
        localExistente.setCUIT(registroLocalViewModel.getCUIT());

        localService.modificar(localExistente);

        return new ModelAndView("homeLocal");
    }

    @GetMapping("/irACambiarPasswordLocal")
    public ModelAndView irACambiarPasswordLocal(HttpSession httpSession){
        ModelAndView mv;
        if(httpSession.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
        } else {
            mv = new ModelAndView("cambiarPasswordLocal");
            Local local = (Local) httpSession.getAttribute("localLogueado");
            LocalLoginViewModel localViewModel = new LocalLoginViewModel();
            localViewModel.setEmail(local.getEmail());
            localViewModel.setPassword(local.getPassword());
            mv.addObject("local", localViewModel);
        }
        return mv;
    }

    @GetMapping("/validarPasswordActualLocal")
    public ModelAndView validarPasswordActualLocal(BindingResult bindingResult, @ModelAttribute LocalLoginViewModel localViewModel){
        ModelAndView mv = new ModelAndView("cambiarPasswordLocal");
        if(!localService.validarCredenciales(localViewModel.getEmail(), localViewModel.getPassword())){
            bindingResult.reject("error", "La clave es incorrecta");
            mv.addObject("error", "La clave es incorrecta");
        }
        return mv;
    }

    @PutMapping("/cambiarPasswordLocal")
    public ModelAndView cambiarPasswordLocal(HttpSession httpSession, @ModelAttribute LocalLoginViewModel localViewModel){
        Local local = (Local) httpSession.getAttribute("localLogueado");
        local.setPassword(localViewModel.getPassword());
        localService.modificar(local);
        return new ModelAndView("homeLocal");
    }

}
