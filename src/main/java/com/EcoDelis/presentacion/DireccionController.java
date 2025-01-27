package com.EcoDelis.presentacion;

import org.springframework.stereotype.Controller;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DireccionController {

    @Autowired
    private CodigoPostalService codigoPostalService;

    @GetMapping("/filtrarCodigoPostal") //Este método debe ejecutarse a medida que el usuario vaya ingresando datos en el inbox
    public ModelAndView filtrarCodigoPostal(@ModelAttribute CodigoPostalViewModel codigoPostalViewModel, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("agregarDireccionCliente");
        if(codigoPostalService.filtrarCodigoPostal(codigoPostalViewModel.getCodigoPostal())!=null){
            List<CodigoPostal> codigosPostales = codigoPostalService.filtrarCodigoPostal(codigoPostalViewModel.getCodigoPostal());
            mv.addObject("codigosPostales", codigosPostales);
        } else {
            //agregar el código del binding error por si no encuentra el código postal
        }
        return mv;
    }

}
