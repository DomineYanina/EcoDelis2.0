package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            clienteViewModel.setTipo_cliente(clienteLogueado.getTipo_cliente());
            clienteViewModel.setF_nac(clienteLogueado.getF_nac());
            clienteViewModel.setTipo_doc(clienteLogueado.getTipo_doc());
            clienteViewModel.setNro_doc(clienteLogueado.getNro_doc());

            mv = new ModelAndView("modificarCliente");
            mv.addObject("cliente", clienteViewModel);
        }
        return mv;
    }

    @PostMapping("/modificarCliente")
    public ModelAndView modificarCliente(HttpSession session, ClienteViewModel clienteViewModel){
        if(session.getAttribute("clienteLogueado") == null) {
            return new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = clienteService.buscarPorEmail(clienteViewModel.getEmail());
            clienteLogueado.setNombre(clienteViewModel.getNombre());
            clienteLogueado.setApellido(clienteViewModel.getApellido());
            clienteLogueado.setTipo_cliente(clienteViewModel.getTipo_cliente());
            clienteLogueado.setF_nac(clienteViewModel.getF_nac());
            clienteLogueado.setTipo_doc(clienteViewModel.getTipo_doc());
            clienteLogueado.setNro_doc(clienteViewModel.getNro_doc());
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
            ModelAndView mv = new ModelAndView("agregarNuevaDireccionCliente");
            mv.addObject("cliente", clienteLogueado);

            return mv;
        }
    }

    @GetMapping("/irAAgregarUnTelefonoCliente")
    public ModelAndView irAAgregarUnTelefonoCliente(HttpSession session) {
        if(session.getAttribute("clienteLogueado") == null) {
            return new ModelAndView("loginCliente");
        } else {
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            ModelAndView mv = new ModelAndView("agregarNuevaTelefonoCliente");
            mv.addObject("cliente", clienteLogueado);
            return mv;
        }
    }

    @GetMapping("/irARegistrarNuevoCliente")
    public ModelAndView irARegistrarNuevoCliente(HttpSession session) {
        if(session.getAttribute("clienteLogueado") != null) {
            return new ModelAndView("homeCliente");
        } else {
            RegistroViewModel registroViewModel = new RegistroViewModel();
            ModelAndView mv = new ModelAndView("primerPasoRegistroCliente");
            mv.addObject("cliente", registroViewModel);
            return mv;
        }
    }

    @GetMapping("/chequearMailYaExistente")
    public ModelAndView chequearMailYaExistente(HttpSession session, BindingResult bindingResult, RegistroViewModel registroViewModel) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") == null) {
            if(clienteService.existeEmail(registroViewModel.getEmail())){
                mv = new ModelAndView("primerPasoRegistroCliente");
                mv.addObject("error", "Email ya existe");
            } else {
                mv = new ModelAndView("cargarDatosNuevoCliente");
                mv.addObject("cliente", registroViewModel);
            }
        } else{
            mv = new ModelAndView("homeCliente");
        }
        return mv;
    }

    @PostMapping("/registrarNuevoCliente")
    public ModelAndView registrarNuevoCliente(HttpSession session, RegistroViewModel registroViewModel) {
        ModelAndView mv;
        if(session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            mv = new ModelAndView("homeCliente");
            httpSession.setAttribute("clienteLogueado", clienteService.registrarCliente(registroViewModel));
            mv.addObject("cliente", registroViewModel);
        }
        return mv;
    }

}
