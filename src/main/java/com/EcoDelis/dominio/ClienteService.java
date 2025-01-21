package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.RegistroClienteViewModel;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    boolean existeEmail(String email);

    Cliente registrarCliente(RegistroClienteViewModel registroClienteViewModel);

    boolean validarCredenciales(String email, String clave);

    Cliente buscarPorEmail(String email);

    void modificar(Cliente clienteLogueado);
}
