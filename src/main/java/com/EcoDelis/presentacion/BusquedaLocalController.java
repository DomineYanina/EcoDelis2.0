package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BusquedaLocalController {

    @Autowired
    private LocalService localService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/irAFiltrarLocales")
    public ModelAndView irAFiltrarLocales(HttpSession httpSession){
        ModelAndView mv;
        if(httpSession.getAttribute("clienteLogueado") == null){
            mv = new ModelAndView("loginCliente");
        } else {
            mv = new ModelAndView("filtrarLocales");
            mv.addObject("tiposLocales", TipoLocal.values());
        }
        return mv;
    }

    @GetMapping("/fitrarLocalesPorTipo")
    public ModelAndView fitrarLocalesPorTipo(HttpSession httpSession, @RequestParam TipoLocal tipoLocal){
        List<Local> localesFiltrados = localService.filtrarLocalesPorTipoLocal(tipoLocal);
        ModelAndView mv = new ModelAndView("filtrarLocales");
        mv.addObject("tiposLocales", TipoLocal.values());
        mv.addObject("locales", localesFiltrados);
        return mv;
    }

    @GetMapping("/mostrarSucursalesPorLocal")
    public ModelAndView mostrarSucursalesPorLocal(HttpSession httpSession, @RequestParam Local local){
        List<Sucursal> sucursales = localService.obtenerSucursalesPorLocal(local);
        ModelAndView mv = new ModelAndView("mostrarSucursalesPorLocal");
        mv.addObject("sucursales", sucursales);
        return mv;
    }

    @GetMapping("/obtenerPromocionesPorSucursal")
    public ModelAndView obtenerPromocionesPorSucursal(HttpSession httpSession, @RequestParam Sucursal sucursal){
        List<Promocion> promociones = sucursalService.obtenerPromocionesPorSucursal(sucursal);
        ModelAndView mv = new ModelAndView("mostrarPromocionesPorSucursal");
        mv.addObject("promociones", promociones);
        return mv;
    }
}
