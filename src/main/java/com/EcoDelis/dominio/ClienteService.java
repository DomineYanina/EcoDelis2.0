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

    void registrarTelefono(TelefonoCliente telefonoCliente);

    void modificarTelefono(TelefonoCliente telefonoCliente);

    void eliminarTelefono(TelefonoCliente telefonoCliente);

    void registrarDireccion(DireccionCliente direccionCliente);

    void modificarDireccion(DireccionCliente direccionCliente);

    void eliminarDireccion(DireccionCliente direccionCliente);

}
