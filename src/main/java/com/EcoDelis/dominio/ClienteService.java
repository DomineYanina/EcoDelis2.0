package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.ClienteViewModel;
import com.EcoDelis.presentacion.RegistroClienteViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    boolean existeEmail(String email);

    Cliente registrarCliente(ClienteViewModel clienteViewModel);

    boolean validarCredenciales(String email, String clave);

    Cliente buscarPorEmail(String email);

    void modificar(Cliente clienteLogueado);

    void registrarTelefono(TelefonoCliente telefonoCliente);

    void modificarTelefono(TelefonoCliente telefonoCliente);

    void eliminarTelefono(TelefonoCliente telefonoCliente);

    void registrarDireccion(DireccionCliente direccionCliente);

    void modificarDireccion(DireccionCliente direccionCliente);

    void eliminarDireccion(DireccionCliente direccionCliente);

    List<Pedido> obtenerPedidosPorCliente(Cliente cliente);
}
