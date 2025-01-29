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
        ModelAndView mv;
        if(httpSession.getAttribute("clienteLogueado") != null){
            mv = new ModelAndView("verMisPedidos");
            mv.addObject("pedidos", pedidoService.obtenerPedidosPorCliente((Cliente) httpSession.getAttribute("clienteLogueado")));
            return mv;
        } else{
            mv = new ModelAndView("loginCliente");
            mv.addObject("cliente", new ClienteLoginViewModel());
        }
        return mv;
    }
    
}
