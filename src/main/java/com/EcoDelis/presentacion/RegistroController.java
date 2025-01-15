package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegistroController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    LocalService localService;

    @GetMapping("/irARegistrarCliente")
    public ModelAndView irARegistrarCliente(HttpSession httpSession) {
        ModelAndView mv;
        if(httpSession.getAttribute("clienteLogueado") == null) {
            Cliente cliente = new Cliente();
            mv = new ModelAndView("nuevoCliente");
            mv.addObject("cliente", cliente);
        } else{
            Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
            mv = new ModelAndView("homeCliente");
            mv.addObject("cliente", cliente);
        }
        return mv;
    }

    @GetMapping("/irARegistrarLocal")
    public ModelAndView irARegistrarLocal(HttpSession httpSession) {
        ModelAndView mv;
        if(httpSession.getAttribute("localLogueado") == null) {
            Local local = new Local();
            mv = new ModelAndView("nuevoLocal");
            mv.addObject("local", local);
        } else{
            Local local = (Local) httpSession.getAttribute("localLogueado");
            mv = new ModelAndView("homeLocal");
            mv.addObject("local", local);
        }
        return mv;
    }

    @PostMapping("/registrarCliente")
    public ModelAndView registrarCliente(@ModelAttribute("cliente") RegistroViewModel registroViewModel, BindingResult bindingResult, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("nuevoCliente");

        if(clienteService.existeEmail(registroViewModel.getEmail())){
            bindingResult.rejectValue("email", "email", "El email ya existe");
            modelAndView.addObject("error", "El email ingresado ya está registrado");
            modelAndView.addObject("usuario", registroViewModel);
            return modelAndView;
        }

        Cliente cliente = clienteService.registrarCliente(registroViewModel);

        session.setAttribute("clienteLogueado", cliente);

        return new ModelAndView("buscar");
    }

    @PostMapping("/registrarLocal")
    public ModelAndView registrarLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, BindingResult bindingResult, HttpSession session){
            ModelAndView modelAndView = new ModelAndView("nuevoLocal");

            if(localService.existeEmail(registroLocalViewModel.getEmail())){
                bindingResult.rejectValue("email", "email", "El email ya existe");
                modelAndView.addObject("error", "El email ingresado ya está registrado");
                modelAndView.addObject("local", registroLocalViewModel);
                return modelAndView;
            }
            Local local = localService.registrarLocal(registroLocalViewModel);

            session.setAttribute("localLogueado", local);

        return new ModelAndView("agregarSucursal");
    }

    public Boolean chequearLocalLogueado(HttpSession session){
        Boolean resultado;
        if(session.getAttribute("localLogueado") != null){
            resultado=true;
        } else{
            resultado=false;
        }
        return resultado;
    }

    public Boolean chequearUsuarioLogueado(HttpSession session){
        Boolean resultado;
        if(session.getAttribute("usuarioLogueado") != null){
            resultado=true;
        } else{
            resultado=false;
        }
        return resultado;
    }
}
