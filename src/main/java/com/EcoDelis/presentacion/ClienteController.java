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

import javax.servlet.http.HttpSession;

import static com.EcoDelis.dominio.EstadoPedido.Calificado;
import static com.EcoDelis.dominio.EstadoPedido.Cancelado;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/irAModificarCliente")
    public ModelAndView irAModificarCliente(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            if (httpSession.getAttribute("localLogueado") == null) {
                mv = new ModelAndView("modificarCliente");
                mv.addObject("cliente", transformarClienteAModelo((Cliente) session.getAttribute("clienteLogueado")));
                mv.addObject("tiposDocumento", TipoDocumento.values());
                mv.addObject("TiposDeCliente", TipoDocumento.values());
            } else {
                mv = new ModelAndView("homeLocal");
            }

        }
        return mv;
    }

    @PutMapping("/modificarCliente")
    public ModelAndView modificarCliente(HttpSession session, ClienteViewModel clienteViewModel) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", new ClienteLoginViewModel());
            return mv;
        } else {
            if (httpSession.getAttribute("localLogueado") == null) {
                clienteService.modificar(transformarDeModeloACliente(clienteService.buscarPorEmail(clienteViewModel.getEmail()), clienteViewModel));
                mv = new ModelAndView("homeCliente");
            } else {
                mv = new ModelAndView("homeLocal");
            }
        }
        return mv;
    }

    @GetMapping("/irAAgregarUnaDireccionCliente")
    public ModelAndView irAAgregarUnaDireccionCliente(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", new ClienteLoginViewModel());
        } else {
            if (httpSession.getAttribute("localLogueado") == null) {
                mv = new ModelAndView("agregarDireccionCliente");
                mv.addObject("cliente", transformarClienteAModelo((Cliente) session.getAttribute("clienteLogueado")));
                mv.addObject("direccionCliente", new DireccionClienteViewModel());
                mv.addObject("codigosPostales", new CodigoPostalViewModel());
            } else {
                mv = new ModelAndView("homeLocal");
            }
        }
        return mv;
    }

    @PostMapping("/agregarDireccionCliente")
    public ModelAndView agregarDireccionCliente(@ModelAttribute DireccionClienteViewModel direccionClienteViewModel, HttpSession session) {
        ModelAndView mv;
        if(!clienteService.chequearDireccionYaExistente(transformarDeModeloDireccionADireccion(direccionClienteViewModel,(Cliente) session.getAttribute("clienteLogueado")),(Cliente) session.getAttribute("clienteLogueado"))){
            clienteService.registrarDireccion(transformarDeModeloDireccionADireccion(direccionClienteViewModel, (Cliente) session.getAttribute("clienteLogueado")));
            mv = new ModelAndView("homeCliente");
        } else {
            mv= new ModelAndView("agregarDireccionCliente");
            mv.addObject("direccionCliente", new DireccionClienteViewModel());
            mv.addObject("error", "La dirección ingresada ya existe");
        }
        return mv;
    }

    @GetMapping("/irAAgregarUnTelefonoCliente")
    public ModelAndView irAAgregarUnTelefonoCliente(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", new ClienteLoginViewModel());
        } else {
            if (httpSession.getAttribute("localLogueado") == null) {
                mv = new ModelAndView("agregarTelefonoCliente");
                mv.addObject("cliente", transformarClienteAModelo((Cliente) session.getAttribute("clienteLogueado")));
                mv.addObject("telefonoCliente", new TelefonoClienteViewModel());
                mv.addObject("tipoTelefono", TipoDocumento.values());
            } else {
                mv = new ModelAndView("homeLocal");
            }

        }
        return mv;
    }

    @GetMapping("/irARegistrarNuevoCliente")
    public ModelAndView irARegistrarNuevoCliente(HttpSession session) {
        if (session.getAttribute("clienteLogueado") != null) {
            return new ModelAndView("homeCliente");
        } else {
            ModelAndView mv = new ModelAndView("registroClientePrimerPaso");
            mv.addObject("cliente", new RegistroClienteViewModel());
            return mv;
        }
    }

    @PostMapping("/agregarTelefonoCliente")
    public ModelAndView agregarTelefonoCliente(@ModelAttribute("telefonoCliente") TelefonoClienteViewModel telefonoClienteViewModel, HttpSession session) {
        ModelAndView mv;
        if(!clienteService.chequearTelefonoYaExistente(transformarTelefonoClienteModeloATelefonoCliente(telefonoClienteViewModel,(Cliente) session.getAttribute("clienteLogueado")))){
            clienteService.registrarTelefono(transformarTelefonoClienteModeloATelefonoCliente(telefonoClienteViewModel,(Cliente) session.getAttribute("clienteLogueado")));
            mv = new ModelAndView("homeCliente");
        } else {
            mv= new ModelAndView("agregarTelefonoCliente");
            mv.addObject("telefono", new TelefonoClienteViewModel());
            mv.addObject("error", "El teléfono ingresado ya existe");
        }
        return mv;
    }

    @GetMapping("/chequearMailYaExistente")
    public ModelAndView chequearMailYaExistente(HttpSession session, RegistroClienteViewModel registroClienteViewModel) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            if (clienteService.existeEmail(registroClienteViewModel.getEmail())) {
                mv = new ModelAndView("registroClientePrimerPaso");
                mv.addObject("error", "Email ya existe");
            } else {
                mv = new ModelAndView("registroClienteSegundoPaso");
                mv.addObject("cliente", registroClienteViewModel);
            }
        } else {
            mv = new ModelAndView("homeCliente");
        }
        return mv;
    }

    @PostMapping("/registrarNuevoCliente")
    public ModelAndView registrarNuevoCliente(HttpSession session, @ModelAttribute ClienteViewModel clienteViewModel) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") != null) {
            mv = new ModelAndView("homeCliente");
        } else {
            mv = new ModelAndView("homeCliente");
            httpSession.setAttribute("clienteLogueado", clienteService.registrarCliente(transformarModeloACliente(clienteViewModel)));
            mv.addObject("cliente", clienteViewModel);
        }
        return mv;
    }

    @GetMapping("/irAModificarTipoSuscripcionCliente")
    public ModelAndView irAModificarTipoSuscripcionCliente(HttpSession httpSession) {
        ModelAndView mv;
        if (httpSession.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            mv = new ModelAndView("modificarTipoSuscripcionCliente");
            mv.addObject("cliente", transformarClienteAModelo((Cliente) httpSession.getAttribute("clienteLogueado")));
            mv.addObject("tiposSuscripcion", TipoCliente.values());
        }
        return mv;
    }

    @PutMapping("/modificarTipoSuscripcionCliente")
    public ModelAndView modificarTipoSuscripcionCliente(@ModelAttribute ClienteViewModel clienteViewModel) {
        Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        clienteService.modificar(cliente);
        return new ModelAndView("homeCliente");
    }

    @GetMapping("/irACambiarPasswordCliente")
    public ModelAndView irACambiarPasswordCliente(HttpSession httpSession) {
        ModelAndView mv;
        if (httpSession.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
        } else {
            mv = new ModelAndView("cambiarPasswordCliente");
            mv.addObject("cliente", transformarClienteAModeloCorto((Cliente) httpSession.getAttribute("clienteLogueado")));
        }
        return mv;
    }

    @GetMapping("/validarPasswordActual")
    public ModelAndView validarPasswordActual(BindingResult bindingResult, @ModelAttribute ClienteLoginViewModel clienteViewModel) {
        ModelAndView mv = new ModelAndView("cambiarPasswordCliente");
        if (!clienteService.validarCredenciales(clienteViewModel.getEmail(), clienteViewModel.getPassword())) {
            bindingResult.reject("error", "La clave es incorrecta");
            mv.addObject("error", "La clave es incorrecta");
        }
        return mv;
    }

    @PutMapping("/resetearPasswordCliente")
    public ModelAndView resetearPasswordCliente(HttpSession httpSession, @ModelAttribute ClienteLoginViewModel clienteViewModel) {
        Cliente cliente = (Cliente) httpSession.getAttribute("clienteLogueado");
        cliente.setPassword(clienteViewModel.getPassword());
        clienteService.modificar(cliente);
        return new ModelAndView("homeCliente");
    }

    @GetMapping("/verMisPedidos")
    public ModelAndView verMisPedidos(HttpSession session) {
        ModelAndView mv;
        if (session.getAttribute("clienteLogueado") == null) {
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente",new ClienteLoginViewModel());
        } else {
            mv = new ModelAndView("verMisPedidos");
            mv.addObject("pedidos", clienteService.obtenerPedidosPorCliente((Cliente) httpSession.getAttribute("clienteLogueado")));
            mv.addObject("cliente", transformarClienteAModelo((Cliente) httpSession.getAttribute("clienteLogueado")));
        }
        return mv;
    }

    @PutMapping("/calificarPedido")
    public ModelAndView calificarPedido(HttpSession httpSession, @ModelAttribute PedidoViewModel pedidoViewModel) {
        pedidoService.actualizar(transformarModeloPedidoAPedido(pedidoViewModel,(Cliente) httpSession.getAttribute("clienteLogueado"), Calificado));
        return new ModelAndView("homeCliente");
    }

    @PutMapping("/cancelarPedidoC")
    public ModelAndView cancelarPedidoC(HttpSession httpSession, @ModelAttribute PedidoViewModel pedidoViewModel) {
        pedidoService.actualizar(transformarModeloPedidoAPedido(pedidoViewModel,(Cliente) httpSession.getAttribute("clienteLogueado"), Cancelado));
        return new ModelAndView("homeCliente");
    }

    //Métodos auxiliares para evitar redundancia

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

    public Cliente transformarDeModeloACliente(Cliente cliente, ClienteViewModel clienteViewModel) {
        cliente.setNombre(clienteViewModel.getNombre());
        cliente.setApellido(clienteViewModel.getApellido());
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        cliente.setFnac(clienteViewModel.getFnac());
        cliente.setTipodoc(clienteViewModel.getTipodoc());
        cliente.setNrodoc(clienteViewModel.getNrodoc());
        cliente.setEmail(clienteViewModel.getEmail());
        return cliente;
    }

    public Cliente transformarDeModeloAClienteSinCliente(ClienteViewModel clienteViewModel) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteViewModel.getNombre());
        cliente.setApellido(clienteViewModel.getApellido());
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        cliente.setFnac(clienteViewModel.getFnac());
        cliente.setTipodoc(clienteViewModel.getTipodoc());
        cliente.setNrodoc(clienteViewModel.getNrodoc());
        cliente.setEmail(clienteViewModel.getEmail());
        return cliente;
    }

    private DireccionCliente transformarDeModeloDireccionADireccion(DireccionClienteViewModel direccionClienteViewModel, Cliente cliente) {
        DireccionCliente direccionCliente = new DireccionCliente();
        direccionCliente.setCliente(cliente);
        direccionCliente.setCalle(direccionClienteViewModel.getCalle());
        direccionCliente.setNumero(direccionClienteViewModel.getNumero());
        direccionCliente.setLocalidad(direccionClienteViewModel.getLocalidad());
        direccionCliente.setProvincia(direccionClienteViewModel.getProvincia());
        direccionCliente.setCodigopostal(direccionClienteViewModel.getCodigopostal());
        return direccionCliente;
    }

    private TelefonoCliente transformarTelefonoClienteModeloATelefonoCliente(TelefonoClienteViewModel telefonoClienteViewModel, Cliente cliente) {
        TelefonoCliente telefonoCliente = new TelefonoCliente();
        telefonoCliente.setNumero(telefonoClienteViewModel.getNumero());
        telefonoCliente.setTipo(telefonoClienteViewModel.getTipo());
        telefonoCliente.setCliente(cliente);
        return telefonoCliente;
    }

    private ClienteLoginViewModel transformarClienteAModeloCorto(Cliente cliente) {
        ClienteLoginViewModel clienteViewModel = new ClienteLoginViewModel();
        clienteViewModel.setEmail(cliente.getEmail());
        clienteViewModel.setPassword(cliente.getPassword());
        return clienteViewModel;
    }

    private Pedido transformarModeloPedidoAPedido(PedidoViewModel pedidoViewModel, Cliente cliente, EstadoPedido estadoPedido) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(estadoPedido);
        pedido.setFecha_retirado(pedidoViewModel.getFecha_retirado());
        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setFecha_realizado(pedidoViewModel.getFecha_realizado());
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setMonto_total(pedidoViewModel.getMonto_total());
        return pedido;
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
