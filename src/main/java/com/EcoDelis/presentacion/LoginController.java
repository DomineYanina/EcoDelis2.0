package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteService;
import com.EcoDelis.dominio.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    LocalService localService;

    @GetMapping("/irALoginCliente")
    public ModelAndView irALoginCliente(ClienteLoginViewModel clienteLoginViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", clienteLoginViewModel);
        }
        return mv;
    }

    @GetMapping("/irALoginLocal")
    public ModelAndView irALoginLocal(LocalLoginViewModel localLoginViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") != null) {
            mv = new ModelAndView("homeLocal");
        } else {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", localLoginViewModel);
        }
        return mv;
    }

    @GetMapping("/logueoCliente")
    public ModelAndView logueoCliente(ClienteLoginViewModel clienteLoginViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            if(clienteService.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getClave())){
                mv = new ModelAndView("homeCliente");
                session.setAttribute("clienteLogueado", clienteLoginViewModel);
            } else {
                mv = new ModelAndView("loginCliente");
                if(clienteService.existeEmail(clienteLoginViewModel.getEmail())){
                    bindingResult.rejectValue("password", "password", "La clave ingresada es incorrecta");
                    mv.addObject("error", "La clave ingresada es incorrecta");
                    mv.addObject("cliente", clienteLoginViewModel);
                } else {
                    bindingResult.rejectValue("email", "email", "El email ingresado es incorrecto");
                    mv.addObject("error", "El email ingresado es incorrecto");
                    mv.addObject("cliente", clienteLoginViewModel);
                }
            }
        }
        return mv;
    }

    @GetMapping("/logueoLocal")
    public ModelAndView logueoLocal(LocalLoginViewModel localLoginViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") != null) {
            mv = new ModelAndView("homeLocal");
        } else {
            if(localService.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getClave())){
                mv = new ModelAndView("homeLocal");
                session.setAttribute("localLogueado", localLoginViewModel);
            } else {
                mv = new ModelAndView("loginLocal");
                if(localService.existeEmail(localLoginViewModel.getEmail())){
                    bindingResult.rejectValue("password", "password", "La clave ingresada es incorrecta");
                    mv.addObject("error", "La clave ingresada es incorrecta");
                    mv.addObject("local", localLoginViewModel);
                } else {
                    bindingResult.rejectValue("email", "email", "El email ingresado es incorrecto");
                    mv.addObject("error", "El email ingresado es incorrecto");
                    mv.addObject("local", localLoginViewModel);
                }
            }
        }
        return mv;
    }

}
