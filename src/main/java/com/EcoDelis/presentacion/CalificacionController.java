package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/obtenerCalificacionesObtenidas")
    public ModelAndView obtenerCalificacionesObtenidas(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session){
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null ){
            mv = new ModelAndView("loginLocal");
            mv.addObject("local", new LocalLoginViewModel());
        } else {
            if(session.getAttribute("clienteLogueado") != null){
                mv = new ModelAndView("homeCliente");
            } else {
                Sucursal sucursal = new Sucursal();
                sucursal.setId(sucursalViewModel.getId());
                sucursal.setTipoLocal(sucursalViewModel.getTipoLocal());
                sucursal.setTipoSuscripcion(sucursalViewModel.getTipoSuscripcion());
                sucursal.setNombre(sucursalViewModel.getNombre());
                double promedio = 0.0;
                int calificaciones = 0;
                List<Calificacion> calificacionesObtenidas = calificacionService.obtenerCalificacionesPorSucursal(sucursal);
                if(!calificacionesObtenidas.isEmpty()){
                    for(Calificacion calificacion : calificacionesObtenidas){
                        calificaciones=calificaciones+calificacion.getPuntaje();
                    }
                    promedio = (double) calificaciones /calificacionesObtenidas.size();
                }
                mv = new ModelAndView("calificacionesObtenidas");
                mv.addObject("calificaciones", calificacionesObtenidas);
            }
        }
        return mv;
    }

    @PostMapping("/agregarCalificacion")
    public ModelAndView agregarCalificacion(@ModelAttribute CalificacionViewModel calificacionViewModel){
        ModelAndView mv;
        Calificacion calificacion = new Calificacion();
        calificacion.setPedido(calificacionViewModel.getPedido());
        calificacion.setComentarios(calificacionViewModel.getComentarios());
        calificacion.setFecha(calificacionViewModel.getFecha());
        calificacion.setPuntaje(calificacionViewModel.getPuntaje());
        calificacionService.nueva(calificacion);
        Pedido pedido = calificacion.getPedido();
        pedido.setEstado(EstadoPedido.Calificado);
        pedidoService.actualizar(pedido);
        return mv = new ModelAndView("homeCliente");
    }

    public Calificacion transformarModeloACalificacion(CalificacionViewModel viewModel){
        Calificacion calificacion = new Calificacion();
        calificacion.setPedido(viewModel.getPedido());
        calificacion.setComentarios(viewModel.getComentarios());
        calificacion.setFecha(viewModel.getFecha());
        calificacion.setPuntaje(viewModel.getPuntaje());
        calificacion.setCliente(viewModel.getCliente());
        return calificacion;
    }


}
