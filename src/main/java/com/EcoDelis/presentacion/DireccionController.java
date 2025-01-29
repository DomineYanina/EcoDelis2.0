package com.EcoDelis.presentacion;

import org.springframework.stereotype.Controller;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DireccionController {

    @Autowired
    private CodigoPostalService codigoPostalService;

    @GetMapping("/filtrarCodigoPostal")
    @ResponseBody
    public Map<String, Object> filtrarCodigoPostal(@RequestParam String codigoPostal) {
        Map<String, Object> response = new HashMap<>();

        List<CodigoPostal> codigosPostales = codigoPostalService.filtrarCodigoPostal(codigoPostal);

        if (codigosPostales != null && !codigosPostales.isEmpty()) {
            response.put("codigosPostales", codigosPostales);
        } else {
            response.put("error", "El código postal no existe");
        }

        return response;
    }


}
