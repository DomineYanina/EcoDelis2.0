package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteService;
import com.EcoDelis.dominio.TelefonoCliente;
import com.EcoDelis.dominio.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/irAModificarCliente")
    public ModelAndView irAModificarCliente(HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");

            ClienteViewModel clienteViewModel = new ClienteViewModel();

            clienteViewModel.setNombre(clienteLogueado.getNombre());
            clienteViewModel.setApellido(clienteLogueado.getApellido());
            clienteViewModel.setTipocliente(clienteLogueado.getTipocliente());
            clienteViewModel.setFnac(clienteLogueado.getFnac());
            clienteViewModel.setTipodoc(clienteLogueado.getTipodoc());
            clienteViewModel.setNrodoc(clienteLogueado.getNrodoc());

            mv = new ModelAndView("modificarCliente");
            mv.addObject("cliente", clienteViewModel);
            mv.addObject("tiposDocumento", TipoDocumento.values());
            mv.addObject("TiposDeCliente", TipoDocumento.values());
        }
        return mv;
    }

    @PutMapping("/modificarCliente")
    public ModelAndView modificarCliente(HttpSession session, ClienteViewModel clienteViewModel){
        if(session.getAttribute("clienteLogueado") == null) {
            return new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = clienteService.buscarPorEmail(clienteViewModel.getEmail());
            clienteLogueado.setNombre(clienteViewModel.getNombre());
            clienteLogueado.setApellido(clienteViewModel.getApellido());
            clienteLogueado.setTipocliente(clienteViewModel.getTipocliente());
            clienteLogueado.setFnac(clienteViewModel.getFnac());
            clienteLogueado.setTipodoc(clienteViewModel.getTipodoc());
            clienteLogueado.setNrodoc(clienteViewModel.getNrodoc());
            clienteLogueado.setEmail(clienteViewModel.getEmail());

            clienteService.modificar(clienteLogueado);

            return new ModelAndView("homeCliente");
        }
    }

    @GetMapping("/irAAgregarUnaDireccionCliente")
    public ModelAndView irAAgregarUnaDireccionCliente(HttpSession session) {
        if(session.getAttribute("clienteLogueado") == null) {
            return new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            ModelAndView mv = new ModelAndView("agregarDireccionCliente");
            mv.addObject("cliente", clienteLogueado);
            mv.addObject("direccionCliente", new DireccionClienteViewModel());

            return mv;
        }
    }

    @GetMapping("/irAAgregarUnTelefonoCliente")
    public ModelAndView irAAgregarUnTelefonoCliente(HttpSession session) {
        if(session.getAttribute("clienteLogueado") == null) {
            return new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            ModelAndView mv = new ModelAndView("agregarTelefonoCliente");
            mv.addObject("cliente", clienteLogueado);
            mv.addObject("telefonoCliente", new TelefonoClienteViewModel());
            mv.addObject("tipoTelefono", TipoDocumento.values());
            return mv;
        }
    }

    @GetMapping("/irARegistrarNuevoCliente")
    public ModelAndView irARegistrarNuevoCliente(HttpSession session) {
        if(session.getAttribute("clienteLogueado") != null) {
            return new ModelAndView("homeCliente");
        } else {
            RegistroClienteViewModel registroClienteViewModel = new RegistroClienteViewModel();
            ModelAndView mv = new ModelAndView("registroClientePrimerPaso");
            mv.addObject("cliente", registroClienteViewModel);
            return mv;
        }
    }

    @PostMapping("/agregarTelefonoCliente")
    public ModelAndView agregarTelefonoCliente(@ModelAttribute("telefonoCliente") TelefonoClienteViewModel telefonoClienteViewModel, HttpSession session) {
        TelefonoCliente telefonoCliente = new TelefonoCliente();
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
        telefonoCliente.setNumero(telefonoClienteViewModel.getNumero());
        telefonoCliente.setTipo(telefonoClienteViewModel.getTipo());
        telefonoCliente.setCliente(cliente);

        clienteService.registrarTelefono(telefonoCliente);
        return new ModelAndView("homeCliente");
    }

    @GetMapping("/chequearMailYaExistente")
    public ModelAndView chequearMailYaExistente(HttpSession session, BindingResult bindingResult, RegistroClienteViewModel registroClienteViewModel) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") == null) {
            if(clienteService.existeEmail(registroClienteViewModel.getEmail())){
                mv = new ModelAndView("primerPasoRegistroCliente");
                mv.addObject("error", "Email ya existe");
            } else {
                mv = new ModelAndView("cargarDatosNuevoCliente");
                mv.addObject("cliente", registroClienteViewModel);
            }
        } else{
            mv = new ModelAndView("homeCliente");
        }
        return mv;
    }

    @PostMapping("/registrarNuevoCliente")
    public ModelAndView registrarNuevoCliente(HttpSession session, @ModelAttribute ClienteViewModel clienteViewModel) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            mv = new ModelAndView("homeCliente");
            httpSession.setAttribute("clienteLogueado", clienteService.registrarCliente(clienteViewModel));
            mv.addObject("cliente", clienteViewModel);
        }
        return mv;
    }

    @GetMapping("/irAModificarTipoSuscripcionCliente")
    public ModelAndView irAModificarTipoSuscripcionCliente(HttpSession httpSession){
        ModelAndView mv;
        if(httpSession.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            mv = new ModelAndView("modificarTipoSuscripcionCliente");
            ClienteViewModel clienteViewModel = new ClienteViewModel();
            Cliente clienteLogueado = (Cliente) httpSession.getAttribute("clienteLogueado");
            clienteViewModel.setTipocliente(clienteLogueado.getTipocliente());
            clienteViewModel.setApellido(clienteLogueado.getApellido());
            clienteViewModel.setNombre(clienteLogueado.getNombre());
            clienteViewModel.setEmail(clienteLogueado.getEmail());
            clienteViewModel.setFnac(clienteLogueado.getFnac());
            clienteViewModel.setNrodoc(clienteLogueado.getNrodoc());
            clienteViewModel.setFregistro(clienteLogueado.getFregistro());
            clienteViewModel.setTipodoc(clienteLogueado.getTipodoc());
            clienteViewModel.setPassword(clienteLogueado.getPassword());
            mv.addObject("cliente", clienteViewModel);
        }
        return mv;
    }

    @PutMapping("/modificarTipoSuscripcionCliente")
    public ModelAndView modificarTipoSuscripcionCliente(@ModelAttribute ClienteViewModel clienteViewModel){
        Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        clienteService.modificar(cliente);
        return new ModelAndView("homeCliente");
    }

    @GetMapping("/irACambiarPassword")
    public ModelAndView irACambiarPassword(HttpSession httpSession){
        ModelAndView mv;
        if(httpSession.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            mv = new ModelAndView("cambiarPasswordCliente");
            Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
            ClienteLoginViewModel clienteViewModel = new ClienteLoginViewModel();
            clienteViewModel.setEmail(cliente.getEmail());
            clienteViewModel.setClave(cliente.getPassword());
            mv.addObject("cliente", clienteViewModel);
        }
        return mv;
    }

    @GetMapping("/validarPasswordActual")
    public ModelAndView validarPasswordActual(HttpSession httpSession, BindingResult bindingResult, @ModelAttribute ClienteLoginViewModel clienteViewModel){
        ModelAndView mv = new ModelAndView("cambiarPasswordCliente");
        if(!clienteService.validarCredenciales(clienteViewModel.getEmail(), clienteViewModel.getClave())){
            bindingResult.reject("error", "La clave es incorrecta");
            mv.addObject("error", "La clave es incorrecta");
        }
        return mv;
    }

    @PutMapping("/resetearPasswordCliente")
    public ModelAndView resetearPasswordCliente(@ModelAttribute ClienteLoginViewModel clienteViewModel){
        Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
        cliente.setPassword(clienteViewModel.getClave());
        clienteService.modificar(cliente);
        return new ModelAndView("homeCliente");
    }
}
