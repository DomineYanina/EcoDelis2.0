package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/modificarTipoDePromocion")
    public ModelAndView modificarTipoDePromocion(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session, TipoSuscripcionSucursal tipoDeSuscripcionSucursal) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
        } else {
            Sucursal sucursal = localService.buscarSucuralPorNombre(sucursalViewModel.getNombre());
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
            Sucursal sucursal = localService.buscarSucuralPorNombre(nombre);

            SucursalViewModel sucursalViewModel = new SucursalViewModel();
            sucursalViewModel.setNombre(sucursal.getNombre());
            sucursalViewModel.setTipoSuscripcion(sucursal.getTipoSuscripcion());
            mv = new ModelAndView("modificarSucursal");
            mv.addObject("sucursal", sucursalViewModel);
        }
        return mv;
    }

    @PostMapping("/modificarSucursal")
    public ModelAndView modificarSucursal(@ModelAttribute SucursalViewModel sucursalViewModel, HttpSession session) {
        ModelAndView mv;
        if(session.getAttribute("localLogueado") == null) {
            mv = new ModelAndView("redirect:/loginLocal");
            return mv;
        } else {
            Sucursal sucursal = localService.buscarSucuralPorNombre(sucursalViewModel.getNombre());
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
            Sucursal sucursal = localService.buscarSucuralPorNombre(nombreSucursal);
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
            Sucursal sucursal = localService.buscarSucuralPorNombre(nombreSucursal);
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
            Sucursal sucursal = localService.buscarSucuralPorNombre(nombreSucursal);
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
            mv = new ModelAndView("redirect:/loginLocal");
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


}
