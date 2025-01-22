package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Calificacion;
import com.EcoDelis.dominio.CalificacionRepository;
import com.EcoDelis.dominio.CalificacionService;
import com.EcoDelis.dominio.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("CalificacionService")
@Transactional
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion obtener(Pedido pedido) {
        return calificacionRepository.obtener(pedido);
    }

    @Override
    public void nueva(Calificacion calificacion) {
        calificacionRepository.agregar(calificacion);
    }
}
