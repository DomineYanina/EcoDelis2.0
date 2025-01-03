package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface LocalService {
    boolean validarCredenciales(String email, String clave);

    Local buscarPorEmail(String email);
}
