package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.Pedido;
import com.EcoDelis.dominio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/obtenerPedidos")
    public ModelAndView obtenerPedidos(HttpSession httpSession){
        if(httpSession.getAttribute("clienteLogueado") != null){
            ModelAndView mv = new ModelAndView("verMisPedidos");
            Cliente clienteLogueado = (Cliente) httpSession.getAttribute("clienteLogueado");
            List<Pedido> listaDePedidos = pedidoService.obtenerPedidosPorCliente(clienteLogueado);
            mv.addObject("pedidos", listaDePedidos);
            return mv;
        } else{
            return new ModelAndView("loginCliente");
        }
    }
    
}
