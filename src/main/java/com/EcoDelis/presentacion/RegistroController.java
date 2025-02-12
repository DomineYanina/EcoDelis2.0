package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class RegistroController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    LocalService localService;

    @GetMapping("/irARegistrarLocal")
    public ModelAndView irARegistrarLocal(HttpSession httpSession) {
        ModelAndView mv;
        if(httpSession.getAttribute("localLogueado") == null && httpSession.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("registroLocalSegundoPaso");
            mv.addObject("local", new RegistroLocalViewModel());
        } else{
            if(httpSession.getAttribute("clienteLogueado") != null) {
                mv = new ModelAndView("homeCliente");
            } else {
                mv = new ModelAndView("homeLocal");
            }
        }
        return mv;
    }

    @GetMapping("/irARegistroLocal")
    public ModelAndView irARegistroLocal(HttpSession httpSession) {
        if(httpSession.getAttribute("localLogueado") == null) {
            ModelAndView modelAndView = new ModelAndView("registroLocalPrimerPaso");
            modelAndView.addObject("local", new RegistroLocalViewModel());
            return modelAndView;
        } else {
            return new ModelAndView("homeLocal");
        }
    }

    @GetMapping("/irARegistroCliente")
    public ModelAndView irARegistroCliente(HttpSession httpSession) {
        if(httpSession.getAttribute("localLogueado") == null && httpSession.getAttribute("clienteLogueado") == null) {
            ModelAndView modelAndView = new ModelAndView("pruebaLoginCliente");
            modelAndView.addObject("cliente", new ClienteViewModel());
            return modelAndView;
        } else {
            if(httpSession.getAttribute("localLogueado") != null) {
                return new ModelAndView("homeLocal");
            } else {
                return new ModelAndView("homeCliente");
            }
        }
    }

    @GetMapping("/verificarDisponibilidadMailCliente")
    public ModelAndView verificarDisponibilidadMailCliente(@ModelAttribute("cliente") ClienteViewModel clienteViewModel, HttpSession httpSession) {
        if(!clienteService.existeEmail(clienteViewModel.getEmail())) {
            ModelAndView modelAndView = new ModelAndView("registroCliente");
            httpSession.setAttribute("password",transformarClienteAModeloLogin(clienteViewModel).getPassword());
            httpSession.setAttribute("email",transformarClienteAModeloLogin(clienteViewModel).getEmail());
            modelAndView.addObject("cliente", clienteViewModel);
            modelAndView.addObject("tiposDocumento", TipoDocumento.values());
            modelAndView.addObject("TiposDeCliente", TipoCliente.values());
            modelAndView.addObject("direccionCliente", new DireccionClienteViewModel());
            modelAndView.addObject("telefonoCliente", new TelefonoClienteViewModel());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("loginCliente");
            modelAndView.addObject("error", "El email ya existe");
            ClienteViewModel clienteViewModel2 = new ClienteViewModel();
            modelAndView.addObject("cliente", clienteViewModel2);
            return modelAndView;
        }
    }

    @PostMapping("/registrarCliente")
    public ModelAndView registrarCliente(@ModelAttribute("cliente") ClienteViewModel clienteViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("agregarDireccionCliente");

        clienteViewModel.setPassword((String) session.getAttribute("password"));
        clienteViewModel.setEmail((String) session.getAttribute("email"));

        LocalDate fechaActual = LocalDate.now();
        clienteViewModel.setFregistro(fechaActual);
        Cliente cliente = clienteService.registrarCliente(transformarModeloACliente(clienteViewModel));

        session.setAttribute("clienteLogueado", cliente);
        mv.addObject("cliente", clienteViewModel);
        mv.addObject("direccionCliente", new DireccionClienteViewModel());
        return mv;
    }

    @GetMapping("/verificarDisponibilidadMailLocal")
    public ModelAndView verificarDisponibilidadMailLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, HttpSession httpSession) {
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
    public ModelAndView registrarLocal(@ModelAttribute("local") RegistroLocalViewModel registroLocalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("agregarSucursal");

        registroLocalViewModel.setPassword((String) session.getAttribute("password"));
        registroLocalViewModel.setEmail((String) session.getAttribute("email"));

        Local localExistente = localService.registrarLocal(registroLocalViewModel);

        session.setAttribute("localLogueado", localExistente);
        mv.addObject("local", transformarLocalAModelo(localExistente));
        mv.addObject("sucursal", new RegistroSucursalViewModel());
        mv.addObject("direccionSucursal", new DireccionSucursalViewModel());
        mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
        mv.addObject("tiposDocumento", TipoDocumento.values());
        return mv;
    }

    //Métodos auxiliares

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

    private Cliente transformarClienteAModeloLogin(ClienteViewModel clienteViewModel){
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteViewModel.getEmail());
        cliente.setPassword(clienteViewModel.getPassword());
        return cliente;
    }

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

    private Cliente transformarModeloACliente(ClienteViewModel clienteViewModel){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteViewModel.getNombre());
        cliente.setApellido(clienteViewModel.getApellido());
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        cliente.setFnac(clienteViewModel.getFnac());
        cliente.setTipodoc(clienteViewModel.getTipodoc());
        cliente.setNrodoc(clienteViewModel.getNrodoc());
        cliente.setEmail(clienteViewModel.getEmail());
        cliente.setPassword(clienteViewModel.getPassword());
        cliente.setFregistro(clienteViewModel.getFregistro());
        cliente.setDirecciones(clienteViewModel.getDirecciones());
        cliente.setTelefonoClientes(clienteViewModel.getTelefonoClientes());
        cliente.setPedidos(clienteViewModel.getPedidos());
        return cliente;
    }
}
