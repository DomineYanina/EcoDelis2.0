package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.CodigoPostal;
import com.EcoDelis.dominio.CodigoPostalRepository;
import com.EcoDelis.dominio.CodigoPostalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("CodigoPostalService")
@Transactional
public class CodigoPostalServiceImpl implements CodigoPostalService {

    @Autowired
    CodigoPostalRepository codigoPostalRepository;


    @Override
    public List<CodigoPostal> filtrarCodigoPostal(String codigoPostal) {
        return codigoPostalRepository.filtrarCodigoPostal(codigoPostal);
    }
}
