package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodigoPostalRepository {
    List<CodigoPostal> filtrarCodigoPostal(String codigoPostal);
}
