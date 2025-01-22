package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.CalificacionService;
import com.EcoDelis.dominio.ClienteService;
import com.EcoDelis.dominio.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LocalService localService;

}
