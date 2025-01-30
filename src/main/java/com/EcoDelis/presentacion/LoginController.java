package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
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
    public ModelAndView irALoginLocal(HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") != null) {
            mv = new ModelAndView("homeLocal");
        } else {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        }
        return mv;
    }

    @GetMapping("/logueoCliente")
    public ModelAndView logueoCliente(ClienteLoginViewModel clienteLoginViewModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            if(clienteService.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getPassword())){
                mv = new ModelAndView("homeCliente");
                session.setAttribute("clienteLogueado", transformarClienteAModelo(clienteService.buscarPorEmail(clienteLoginViewModel.getEmail())));
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
            if(localService.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getPassword())){
                mv = new ModelAndView("homeLocal");
                session.setAttribute("localLogueado", transformarLocalAModelo(localService.buscarPorEmail(localLoginViewModel.getEmail())));
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

    @GetMapping("/cerrarSesionLocal")
    public ModelAndView cerrarSesionLocal(HttpSession session) {
        ModelAndView mv;
        session.removeAttribute("localLogueado");
        mv = new ModelAndView("loginLocal");
        mv.addObject("local", new LocalLoginViewModel());
        return mv;
    }

    @GetMapping("/cerrarSesionCliente")
    public ModelAndView cerrarSesionCliente(HttpSession session) {
        ModelAndView mv;
        session.removeAttribute("clienteLogueado");
        mv = new ModelAndView("loginCliente");
        mv.addObject("cliente", new ClienteLoginViewModel());
        return mv;
    }

    @GetMapping("/irAHomeLocal")
    public ModelAndView irAHomeLocal(HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") != null) {
            mv = new ModelAndView("homeLocal");
        } else {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        }
        return mv;
    }

    @GetMapping("/irAHomeCliente")
    public ModelAndView irAHomeCliente(HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", new ClienteLoginViewModel());
        }
        return mv;
    }

    //Métodos auxiliares

    private ClienteViewModel transformarClienteAModelo(Cliente cliente) {
        ClienteViewModel viewModel = new ClienteViewModel();
        viewModel.setNombre(cliente.getNombre());
        viewModel.setApellido(cliente.getApellido());
        viewModel.setTipocliente(cliente.getTipocliente());
        viewModel.setFnac(cliente.getFnac());
        viewModel.setTipodoc(cliente.getTipodoc());
        viewModel.setNrodoc(cliente.getNrodoc());
        viewModel.setEmail(cliente.getEmail());
        viewModel.setPassword(cliente.getPassword());
        viewModel.setFregistro(cliente.getFregistro());
        viewModel.setDirecciones(cliente.getDirecciones());
        viewModel.setTelefonoClientes(cliente.getTelefonoClientes());
        viewModel.setPedidos(cliente.getPedidos());
        return viewModel;
    }

    private LocalViewModel transformarLocalAModelo(Local local){
        LocalViewModel localViewModel = new LocalViewModel();
        localViewModel.setNombre(local.getNombre());
        localViewModel.setEmail(local.getEmail());
        localViewModel.setPassword(local.getPassword());
        localViewModel.setCUIT(local.getCUIT());
        localViewModel.setF_registro(local.getF_registro());
        localViewModel.setSucursales(local.getSucursales());
        return localViewModel;
    }
}
