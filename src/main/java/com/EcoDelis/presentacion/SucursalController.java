package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

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
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), local);
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
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(nombre, local);

            SucursalViewModel sucursalViewModel = new SucursalViewModel();
            sucursalViewModel.setNombre(sucursal.getNombre());
            sucursalViewModel.setTipoSuscripcion(sucursal.getTipoSuscripcion());
            mv = new ModelAndView("modificarSucursal");
            mv.addObject("sucursal", sucursalViewModel);
            mv.addObject("tipoSuscripciones", TipoSuscripcionSucursal.values());
        }
        return mv;
    }

    @PutMapping("/modificarSucursal")
    public ModelAndView modificarSucursal(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
            return mv;
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), sucursalViewModel.getLocal());
            sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
            sucursal.setNombre(sucursal.getNombre());

            sucursalService.modificar(sucursal);

            return new ModelAndView("homeLocal");
        }
    }

    @GetMapping("/irAAgregarDireccionSucursal")
    public ModelAndView irAAgregarDireccionSucursal(HttpSession session, String nombreSucursal) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
            return mv;
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(nombreSucursal, local);
            DireccionSucursalViewModel direccionSucursalViewModel = new DireccionSucursalViewModel();
            mv = new ModelAndView("agregarDireccionSucursal");
            mv.addObject("direccionSucursal", direccionSucursalViewModel);
            return mv;
        }
    }

    @PostMapping("/agregarDireccionSucursal")
    public ModelAndView agregarDireccionSucursal(@ModelAttribute DireccionSucursalViewModel direccionSucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            DireccionSucursal direccionSucursal = new DireccionSucursal();
            direccionSucursal.setSucursal(direccionSucursalViewModel.getSucursal());
            direccionSucursal.setCalle(direccionSucursalViewModel.getCalle());
            direccionSucursal.setLocalidad(direccionSucursalViewModel.getLocalidad());
            direccionSucursal.setProvincia(direccionSucursalViewModel.getProvincia());
            direccionSucursal.setNumero(direccionSucursalViewModel.getNumero());

            direccionSucursalService.agregar(direccionSucursal);

            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @GetMapping("/irAAgregarTelefonoSucursal")
    public ModelAndView irAAgregarTelefonoSucursal(HttpSession session, String nombreSucursal){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null ){
            mv = new ModelAndView("redirect:/loginLocal");
            return mv;
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(nombreSucursal, local);
            TelefonoSucursalViewModel telefonoSucursalViewModel = new TelefonoSucursalViewModel();
            mv = new ModelAndView("agregarTelefonoSucursal");
            mv.addObject("telefonoSucursal", telefonoSucursalViewModel);
            return mv;
        }
    }

    @PostMapping("/agregarTelefonoSucursal")
    public ModelAndView agregarTelefonoSucursal(HttpSession session, @ModelAttribute TelefonoSucursalViewModel telefonoSucursalViewModel){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            TelefonoSucursal telefonoSucursal = new TelefonoSucursal();
            telefonoSucursal.setSucursal(telefonoSucursalViewModel.getSucursal());
            telefonoSucursal.setNumero(telefonoSucursalViewModel.getNumero());
            telefonoSucursal.setTipo(telefonoSucursalViewModel.getTipo());

            telefonoSucursalService.agregar(telefonoSucursal);

            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @GetMapping("/irAAgregarHorarioSucursal")
    public ModelAndView irAAgregarHorarioSucursal(HttpSession session, String nombreSucursal){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null ){
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            Local local = (Local) session.getAttribute("localLogueado");
            Sucursal sucursal = localService.buscarSucursalPorNombre(nombreSucursal, local);
            HorarioRetiroViewModel horarioRetiroViewModel = new HorarioRetiroViewModel();
            mv = new ModelAndView("agregarHorarioSucursal");
            mv.addObject("horarioRetiro", horarioRetiroViewModel);
        }
        return mv;
    }

    @PostMapping("/agregarHorarioSucursal")
    public ModelAndView agregarHorarioSucursal(@ModelAttribute HorarioRetiroViewModel horarioRetiroViewModel, HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("loginLocal");
        } else {
            HorarioRetiro horarioRetiro = new HorarioRetiro();
            horarioRetiro.setSucursal(horarioRetiroViewModel.getSucursal());
            horarioRetiro.setDia(horarioRetiroViewModel.getDia());
            horarioRetiro.setHora_inicio(horarioRetiroViewModel.getHora_inicio());
            horarioRetiro.setHora_fin(horarioRetiroViewModel.getHora_fin());

            horarioRetiroService.agregar(horarioRetiro);

            mv = new ModelAndView("homeLocal");
        }
        return mv;
    }

    @PutMapping("/modificarHorarioSucursal")
    public ModelAndView modificarHorarioSucursal(@ModelAttribute HorarioRetiroViewModel horarioRetiroViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");

        HorarioRetiro horarioRetiro = new HorarioRetiro();
        horarioRetiro.setSucursal(horarioRetiroViewModel.getSucursal());
        horarioRetiro.setDia(horarioRetiroViewModel.getDia());
        horarioRetiro.setHora_inicio(horarioRetiroViewModel.getHora_inicio());
        horarioRetiro.setHora_fin(horarioRetiroViewModel.getHora_fin());

        horarioRetiroService.actualizar(horarioRetiro);

        return mv;
    }

    @GetMapping("/irAEliminarSucursal")
    public ModelAndView irAEliminarSucursal(HttpSession session){
        if(session.getAttribute("localLogueado") == null) {
            return new ModelAndView("loginLocal");
        } else {
            ModelAndView mv = new ModelAndView("eliminarSucursal");
            Local local  = (Local) session.getAttribute("localLogueado");
            List<Sucursal> sucursales = localService.obtenerSucursalesPorLocal(local);
            mv.addObject("sucursales", sucursales);
            return mv;
        }
    }

    @DeleteMapping("/eliminarSucursal")
    public ModelAndView eliminarSucursal(HttpSession session, @ModelAttribute SucursalViewModel sucursalViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        Sucursal sucursal = localService.buscarSucursalPorNombre(sucursalViewModel.getNombre(), sucursalViewModel.getLocal());
        sucursalService.eliminar(sucursal);
        return mv;
    }

    @GetMapping("/obtenerSucursalesPorLocal")
    public ModelAndView obtenerSucursalesPorLocal(HttpSession session){
        if(session.getAttribute("localLogueado") == null) {
            return new ModelAndView("loginLocal");
        } else {
            ModelAndView mv = new ModelAndView("obtenerSucursalesPorLocal");
            Local local = (Local) session.getAttribute("localLogueado");
            List<Sucursal> sucursales = localService.obtenerSucursalesPorLocal(local);
            mv.addObject("sucursales", sucursales);
            return mv;
        }
    }

    @GetMapping("/obtenerPedidosPorSucursal")
    public ModelAndView irAPedidosAunNoRetirados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        List<Pedido> pedidos = sucursalService.obtenerPedidosPorSucursal(sucursal);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/obtenerPedidosNoConfirmados")
    public ModelAndView obtenerPedidosNoConfirmados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        List<Pedido> pedidos = sucursalService.obtenerPedidosNoConfirmadosPorSucursal(sucursal);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/obtenerPedidosConfirmados")
    public ModelAndView obtenerPedidosConfirmados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        List<Pedido> pedidos = sucursalService.obtenerPedidosConfirmadosPorSucursal(sucursal);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/obtenerPedidosEntregados")
    public ModelAndView obtenerPedidosEntregados(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("verPedidos");
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalViewModel.getNombre());
        sucursal.setLocal(sucursalViewModel.getLocal());
        sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
        sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
        sucursal.setDireccion(sucursalViewModel.getDireccion());
        sucursal.setF_registro(sucursalViewModel.getF_registro());
        sucursal.setTelefonos(sucursalViewModel.getTelefonos());
        List<Pedido> pedidos = sucursalService.obtenerPedidosEntregadosPorSucursal(sucursal);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @PutMapping("/cancelarPedidoS")
    public ModelAndView cancelarPedidoS(@ModelAttribute PedidoViewModel pedidoViewModel, HttpSession session){
        ModelAndView mv = new ModelAndView("homeLocal");
        Pedido pedido = new Pedido();
        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setEstado(pedidoViewModel.getEstado());
        pedido.setCliente(pedidoViewModel.getCliente());
        pedido.setFecha_realizado(pedidoViewModel.getFecha_realizado());
        pedido.setFecha_retirado(pedidoViewModel.getFecha_retirado());
        pedido.setMonto_total(pedidoViewModel.getMonto_total());
        pedido.setEstado(Cancelado);
        pedidoService.actualizar(pedido);
        return mv;
    }

    @PutMapping("/confirmarRetiroDePedido")
    public ModelAndView confirmarRetiroDePedido(@ModelAttribute PedidoViewModel pedidoViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        Pedido pedido = new Pedido();

        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setEstado(pedidoViewModel.getEstado());
        pedido.setCliente(pedidoViewModel.getCliente());
        pedido.setFecha_realizado(pedidoViewModel.getFecha_realizado());
        pedido.setFecha_retirado(pedidoViewModel.getFecha_retirado());
        pedido.setMonto_total(pedidoViewModel.getMonto_total());
        pedido.setEstado(Entregado);
        pedidoService.actualizar(pedido);
        return mv;
    }

    @PutMapping("/confirmarPedido")
    public ModelAndView confirmarPedido(@ModelAttribute PedidoViewModel pedidoViewModel){
        ModelAndView mv = new ModelAndView("homeLocal");
        Pedido pedido = new Pedido();

        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setEstado(pedidoViewModel.getEstado());
        pedido.setCliente(pedidoViewModel.getCliente());
        pedido.setFecha_realizado(pedidoViewModel.getFecha_realizado());
        pedido.setFecha_retirado(pedidoViewModel.getFecha_retirado());
        pedido.setMonto_total(pedidoViewModel.getMonto_total());
        pedido.setEstado(Confirmado);
        pedidoService.actualizar(pedido);
        return mv;
    }
}
