package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.EcoDelis.dominio.EstadoPedido.*;

@Controller
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private LocalService localService;

    @Autowired
    private DireccionSucursalService direccionSucursalService;

    @Autowired
    private TelefonoSucursalService telefonoSucursalService;

    @Autowired
    private HorarioRetiroService horarioRetiroService;

    @Autowired
    private PedidoService pedidoService;

    @PutMapping("/modificarTipoDePromocion")
    public ModelAndView modificarTipoDePromocion(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session, TipoSuscripcionSucursal tipoDeSuscripcionSucursal) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            Sucursal sucursal = localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), (Local) session.getAttribute("localLogueado"));
            sucursal.setTipoSuscripcion(tipoDeSuscripcionSucursal);

            sucursalService.modificar(sucursal);

            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @GetMapping("/irAModificarSucursal")
    public ModelAndView irAModificarSucursal(HttpSession session, String nombre) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("modificarSucursal");
            mv.addObject("sucursal", transformarSucursalAModeloSucursal(localService.buscarSucursalPorNombre(nombre, (Local) session.getAttribute("localLogueado"))));
            mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
        }
        return mv;
    }

    @PutMapping("/modificarSucursal")
    public ModelAndView modificarSucursal(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
            return mv;
        } else {
            Sucursal sucursal = localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), sucursalViewModel.getLocal());
            sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
            sucursal.setNombre(sucursal.getNombre());

            sucursalService.modificar(sucursal);

            return new ModelAndView("homeLocal");
        }
    }

    @GetMapping("/irAAgregarDireccionSucursal")
    public ModelAndView irAAgregarDireccionSucursal(HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
            return mv;
        } else {
            mv = new ModelAndView("modificarDireccionSucursal");
            mv.addObject("direccionSucursal", new DireccionSucursalViewModel());
            return mv;
        }
    }

    @PostMapping("/agregarDireccionSucursal")
    public ModelAndView agregarDireccionSucursal(@ModelAttribute DireccionSucursalViewModel direccionSucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            direccionSucursalService.agregar(transformarDireccionModeloADireccion(direccionSucursalViewModel));
            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @GetMapping("/irAAgregarTelefonoSucursal")
    public ModelAndView irAAgregarTelefonoSucursal(HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null ){
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("agregarTelefonoSucursal");
            mv.addObject("telefonoSucursal", new TelefonoSucursalViewModel());
            mv.addObject("tipoTelefono", TipoTelefono.values());
        }
        return mv;
    }

    @PostMapping("/agregarTelefonoSucursal")
    public ModelAndView agregarTelefonoSucursal(HttpSession session, @ModelAttribute TelefonoSucursalViewModel telefonoSucursalViewModel){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            telefonoSucursalService.agregar(transformarDeTelefonoModeloATelefono(telefonoSucursalViewModel));
            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @GetMapping("/irAAgregarHorarioSucursal")
    public ModelAndView irAAgregarHorarioSucursal(HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null ){
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("agregarHorarioSucursal");
            mv.addObject("horarioRetiro", new HorarioRetiroViewModel());
        }
        return mv;
    }

    @PostMapping("/agregarHorarioSucursal")
    public ModelAndView agregarHorarioSucursal(@ModelAttribute HorarioRetiroViewModel horarioRetiroViewModel, HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            horarioRetiroService.agregar(transformarModeloHorarioAHorario(horarioRetiroViewModel));
            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @PutMapping("/modificarHorarioSucursal")
    public ModelAndView modificarHorarioSucursal(@ModelAttribute HorarioRetiroViewModel horarioRetiroViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        horarioRetiroService.actualizar(transformarModeloHorarioAHorario(horarioRetiroViewModel));
        return mv;
    }

    @GetMapping("/irAEliminarSucursal")
    public ModelAndView irAEliminarSucursal(HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("eliminarSucursal");
            mv.addObject("sucursales", localService.obtenerSucursalesPorLocal((Local) session.getAttribute("localLogueado")));
        }
        return mv;
    }

    @DeleteMapping("/eliminarSucursal")
    public ModelAndView eliminarSucursal(@ModelAttribute SucursalViewModel sucursalViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        sucursalService.eliminar(localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), sucursalViewModel.getLocal()));
        return mv;
    }

    @GetMapping("/obtenerSucursalesPorLocal")
    public ModelAndView obtenerSucursalesPorLocal(HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            mv = new ModelAndView("obtenerSucursalesPorLocal");
            mv.addObject("sucursales", localService.obtenerSucursalesPorLocal((Local) session.getAttribute("localLogueado")));
        }
        return mv;
    }

    @GetMapping("/obtenerPedidosPorSucursal")
    public ModelAndView irAPedidosAunNoRetirados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        mv.addObject("pedidos", sucursalService.obtenerPedidosPorSucursal(transformarModeloSucursalASucursal(sucursalViewModel)));
        return mv;
    }

    @GetMapping("/obtenerPedidosNoConfirmados")
    public ModelAndView obtenerPedidosNoConfirmados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        mv.addObject("pedidos", sucursalService.obtenerPedidosNoConfirmadosPorSucursal(transformarModeloSucursalASucursal(sucursalViewModel)));
        return mv;
    }

    @GetMapping("/obtenerPedidosConfirmados")
    public ModelAndView obtenerPedidosConfirmados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        mv.addObject("pedidos", sucursalService.obtenerPedidosConfirmadosPorSucursal(transformarModeloSucursalASucursal(sucursalViewModel)));
        return mv;
    }

    @GetMapping("/obtenerPedidosEntregados")
    public ModelAndView obtenerPedidosEntregados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        mv.addObject("pedidos", sucursalService.obtenerPedidosEntregadosPorSucursal(transformarModeloSucursalASucursal(sucursalViewModel)));
        return mv;
    }

    @PutMapping("/cancelarPedidoS")
    public ModelAndView cancelarPedidoS(@ModelAttribute PedidoViewModel pedidoViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("homeLocal");
        pedidoService.actualizar(transformarModeloPedidoAPedido(pedidoViewModel, Cancelado));
        return mv;
    }

    @PutMapping("/confirmarRetiroDePedido")
    public ModelAndView confirmarRetiroDePedido(@ModelAttribute PedidoViewModel pedidoViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        pedidoService.actualizar(transformarModeloPedidoAPedido(pedidoViewModel, Entregado));
        return mv;
    }

    @PutMapping("/confirmarPedido")
    public ModelAndView confirmarPedido(@ModelAttribute PedidoViewModel pedidoViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        pedidoService.actualizar(transformarModeloPedidoAPedido(pedidoViewModel, Confirmado));
        return mv;
    }

    //Métodos auxiliares

    private SucursalViewModel transformarSucursalAModeloSucursal(Sucursal sucursall){
        SucursalViewModel sucursalViewModel = new SucursalViewModel();
        sucursalViewModel.setNombre(sucursall.getNombre());
        sucursalViewModel.setTipoSuscripcion(sucursall.getTipoSuscripcion());
        sucursalViewModel.setDireccion(sucursall.getDireccion());
        sucursalViewModel.setTelefonos(sucursall.getTelefonos());
        sucursalViewModel.setF_registro(sucursall.getF_registro());
        sucursalViewModel.setTipoLocal(sucursall.getTipoLocal());
        sucursalViewModel.setLocal(sucursall.getLocal());
        sucursalViewModel.setHorarioRetiros(sucursall.getHorarioRetiros());
        sucursalViewModel.setPromociones(sucursall.getPromociones());
        sucursalViewModel.setItems(sucursall.getItems());
        sucursalViewModel.setPedidos(sucursall.getPedidos());
        return sucursalViewModel;
    }

    private Sucursal transformarModeloSucursalASucursal(SucursalViewModel sucursalViewModel){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setHorarioRetiros(sucursalViewModel.getHorarioRetiros());
        sucursal.setPromociones(sucursalViewModel.getPromociones());
        sucursal.setItems(sucursalViewModel.getItems());
        sucursal.setPedidos(sucursalViewModel.getPedidos());
        return sucursal;
    }

    private DireccionSucursal transformarDireccionModeloADireccion(DireccionSucursalViewModel direccionSucursalViewModel) {
        DireccionSucursal direccionSucursal = new DireccionSucursal();
        direccionSucursal.setSucursal(direccionSucursalViewModel.getSucursal());
        direccionSucursal.setCalle(direccionSucursalViewModel.getCalle());
        direccionSucursal.setLocalidad(direccionSucursalViewModel.getLocalidad());
        direccionSucursal.setProvincia(direccionSucursalViewModel.getProvincia());
        direccionSucursal.setNumero(direccionSucursalViewModel.getNumero());
        return direccionSucursal;
    }

    private TelefonoSucursal transformarDeTelefonoModeloATelefono(TelefonoSucursalViewModel telefonoSucursalViewModel) {
        TelefonoSucursal telefonoSucursal = new TelefonoSucursal();
        telefonoSucursal.setSucursal(telefonoSucursalViewModel.getSucursal());
        telefonoSucursal.setNumero(telefonoSucursalViewModel.getNumero());
        telefonoSucursal.setTipo(telefonoSucursalViewModel.getTipo());
        return telefonoSucursal;
    }

    private HorarioRetiro transformarModeloHorarioAHorario(HorarioRetiroViewModel horarioRetiroViewModel) {
        HorarioRetiro horarioRetiro = new HorarioRetiro();
        horarioRetiro.setSucursal(horarioRetiroViewModel.getSucursal());
        horarioRetiro.setDia(horarioRetiroViewModel.getDia());
        horarioRetiro.setHora_inicio(horarioRetiroViewModel.getHora_inicio());
        horarioRetiro.setHora_fin(horarioRetiroViewModel.getHora_fin());
        return horarioRetiro;
    }

    private Pedido transformarModeloPedidoAPedido(PedidoViewModel pedidoViewModel, EstadoPedido estadoPedido){
        Pedido pedido = new Pedido();
        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setEstado(pedidoViewModel.getEstado());
        pedido.setCliente(pedidoViewModel.getCliente());
        pedido.setFecha_realizado(pedidoViewModel.getFecha_realizado());
        pedido.setFecha_retirado(pedidoViewModel.getFecha_retirado());
        pedido.setMonto_total(pedidoViewModel.getMonto_total());
        pedido.setEstado(estadoPedido);
        return pedido;
    }
}
