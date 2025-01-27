package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.HorarioRetiro;
import com.EcoDelis.dominio.HorarioRetiroRepository;
import com.EcoDelis.dominio.HorarioRetiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("HorarioRetiroService")
@Transactional
public class HorarioRetiroServiceImpl implements HorarioRetiroService {

    @Autowired
    HorarioRetiroRepository horarioRetiroRepository;

    @Override
    public void agregar(HorarioRetiro horarioRetiro) {
        horarioRetiroRepository.agregar(horarioRetiro);
    }

    @Override
    public void actualizar(HorarioRetiro horarioRetiro) {
        horarioRetiroRepository.modificar(horarioRetiro);
    }
}
