package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.Pedido;
import com.EcoDelis.dominio.PedidoService;
import com.EcoDelis.dominio.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Date;

import static com.EcoDelis.dominio.EstadoPedido.Creado;

@Controller
public class CompraController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/realizarPedido")
    public ModelAndView realizarPedido(@ModelAttribute PedidoViewModel pedidoViewModel, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView("mostrarConfirmacionDeCompra");
        LocalDate fechaActual = LocalDate.now();
        Pedido pedido = new Pedido();
        pedido.setCliente((Cliente) httpSession.getAttribute("clienteLogueado"));
        pedido.setEstado(Creado);
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setFecha_realizado(fechaActual);
        pedidoService.agregarPedido(pedido);
        mv.addObject("pedido", pedidoViewModel);
        return mv;
    }
}
