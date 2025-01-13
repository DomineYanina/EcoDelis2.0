package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.RegistroViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
public interface ClienteService {

    boolean existeEmail(String email);

    Cliente registrarCliente(RegistroViewModel registroViewModel);

    boolean validarCredenciales(String email, String clave);

    Cliente buscarPorEmail(String email);

    void modificar(Cliente clienteLogueado);
}
