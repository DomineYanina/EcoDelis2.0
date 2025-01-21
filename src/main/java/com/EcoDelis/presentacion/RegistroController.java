package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
            mv = new ModelAndView("registroLocalSegundoPaso");
            mv.addObject("local", local);
        } else{
            Local local = (Local) httpSession.getAttribute("localLogueado");
            mv = new ModelAndView("homeLocal");
            mv.addObject("local", local);
        }
        return mv;
    }

    @GetMapping("/irARegistroLocal")
    public ModelAndView irARegistroLocal(HttpSession httpSession) {
        if(httpSession.getAttribute("localLogueado") == null) {
            RegistroLocalViewModel registroViewModel = new RegistroLocalViewModel();
            ModelAndView modelAndView = new ModelAndView("registroLocalPrimerPaso");
            modelAndView.addObject("local", registroViewModel);
            return modelAndView;
        } else {
            return new ModelAndView("homeLocal");
        }
    }

    @GetMapping("/irARegistroCliente")
    public ModelAndView irARegistroCliente(HttpSession httpSession) {
        if(httpSession.getAttribute("localLogueado") == null) {
            ClienteViewModel clienteViewModel = new ClienteViewModel();
            ModelAndView modelAndView = new ModelAndView("registroClientePrimerPaso");
            modelAndView.addObject("cliente", clienteViewModel);
            return modelAndView;
        } else {
            return new ModelAndView("homeCliente");
        }
    }

    @GetMapping("/verificarDisponibilidadMailCliente")
    public ModelAndView verificarDisponibilidadMailCliente(@ModelAttribute("cliente") ClienteViewModel clienteViewModel, BindingResult bindingResult, HttpSession httpSession) {
        if(!localService.existeEmail(clienteViewModel.getEmail())) {
            ModelAndView modelAndView = new ModelAndView("registroClienteSegundoPaso");
            Cliente cliente = new Cliente();
            cliente.setEmail(clienteViewModel.getEmail());
            cliente.setPassword(clienteViewModel.getPassword());

            httpSession.setAttribute("password",cliente.getPassword());
            httpSession.setAttribute("email",cliente.getEmail());
            modelAndView.addObject("cliente", clienteViewModel);
            modelAndView.addObject("tiposDocumento", TipoDocumento.values());
            modelAndView.addObject("TiposDeCliente", TipoCliente.values());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("registroClientePrimerPaso");
            modelAndView.addObject("error", "El email ya existe");
            ClienteViewModel clienteViewModel2 = new ClienteViewModel();
            modelAndView.addObject("cliente", clienteViewModel2);
            return modelAndView;
        }
    }

    @PostMapping("/registrarCliente")
    public ModelAndView registrarCliente(@ModelAttribute("cliente") RegistroClienteViewModel registroClienteViewModel, BindingResult bindingResult, HttpSession session){
        ModelAndView mv = new ModelAndView("agregarDireccionCliente");
        DireccionClienteViewModel direccionClienteViewModel = new DireccionClienteViewModel();

        registroClienteViewModel.setPassword((String) session.getAttribute("password"));
        registroClienteViewModel.setEmail((String) session.getAttribute("email"));

        Cliente cliente = clienteService.registrarCliente(registroClienteViewModel);

        session.setAttribute("clienteLogueado", cliente);
        mv.addObject("cliente", cliente);
        mv.addObject("direccionCliente", direccionClienteViewModel);
        mv.addObject("localidades", Localidad.values());
        mv.addObject("provincias", Provincia.values());
        return mv;
    }

    @GetMapping("/verificarDisponibilidadMailLocal")
    public ModelAndView verificarDisponibilidadMailLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, BindingResult bindingResult, HttpSession httpSession) {
        if(!localService.existeEmail(registroLocalViewModel.getEmail())) {
            ModelAndView modelAndView = new ModelAndView("registroLocalSegundoPaso");
            Local local = new Local();
            local.setEmail(registroLocalViewModel.getEmail());
            local.setPassword(registroLocalViewModel.getPassword());

            httpSession.setAttribute("password",local.getPassword());
            httpSession.setAttribute("email",local.getEmail());
            modelAndView.addObject("local", registroLocalViewModel);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("registroLocalPrimerPaso");
            modelAndView.addObject("error", "El email ya existe");
            RegistroLocalViewModel registroLocalViewModel2 = new RegistroLocalViewModel();
            modelAndView.addObject("local", registroLocalViewModel2);
            return modelAndView;
        }
    }

    @PostMapping("/registrarLocal")
    public ModelAndView registrarLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, BindingResult bindingResult, HttpSession session){
        ModelAndView mv = new ModelAndView("agregarSucursal");
        RegistroSucursalViewModel sucursalViewModel = new RegistroSucursalViewModel();
        DireccionSucursalViewModel direccionSucursalViewModel = new DireccionSucursalViewModel();

        registroLocalViewModel.setPassword((String) session.getAttribute("password"));
        registroLocalViewModel.setEmail((String) session.getAttribute("email"));

        Local localExistente = localService.registrarLocal(registroLocalViewModel);

        session.setAttribute("localLogueado", localExistente);
        mv.addObject("local", localExistente);
        mv.addObject("sucursal", sucursalViewModel);
        mv.addObject("direccionSucursal", direccionSucursalViewModel);
        mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
        mv.addObject("localidades", Localidad.values());
        mv.addObject("provincias", Provincia.values());
        mv.addObject("tiposDocumento", TipoDocumento.values());
        return mv;
    }

}
