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

    @PostMapping("/registrarCliente")
    public ModelAndView registrarCliente(@ModelAttribute("cliente") RegistroViewModel registroViewModel, BindingResult bindingResult, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("nuevo-cliente");

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
}
