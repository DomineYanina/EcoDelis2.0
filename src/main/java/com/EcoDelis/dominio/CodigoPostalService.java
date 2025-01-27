package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodigoPostalService {
    List<CodigoPostal> filtrarCodigoPostal(String codigoPostal);
}
