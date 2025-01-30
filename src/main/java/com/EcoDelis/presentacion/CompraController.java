package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

import static com.EcoDelis.dominio.EstadoPedido.Creado;

@Controller
public class CompraController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PromocionService promocionService;

    @PostMapping("/realizarPedido")
    public ModelAndView realizarPedido(@ModelAttribute PedidoViewModel pedidoViewModel, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView("mostrarConfirmacionDeCompra");
        LocalDate fechaActual = LocalDate.now();
        pedidoService.agregarPedido(transformarModeloPedidoAPedido(pedidoViewModel, (Cliente) httpSession.getAttribute("clienteLogueado"), fechaActual));
        List<Promocion> promociones = pedidoViewModel.getPromociones();
        for (Promocion promocion : promociones) {
            int unidadesRestantes = promocion.getUnidadesRestantes() -1;
            promocion.setUnidadesRestantes(unidadesRestantes);
            promocionService.modificarPromocion(promocion);
        }
        mv.addObject("pedido", pedidoViewModel);
        return mv;
    }

    //Métodos auxiliares

    private Pedido transformarModeloPedidoAPedido(PedidoViewModel pedidoViewModel, Cliente cliente, LocalDate fecha){
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(Creado);
        pedido.setPromociones(pedidoViewModel.getPromociones());
        pedido.setSucursal(pedidoViewModel.getSucursal());
        pedido.setFecha_realizado(fecha);
        return pedido;
    }
}
