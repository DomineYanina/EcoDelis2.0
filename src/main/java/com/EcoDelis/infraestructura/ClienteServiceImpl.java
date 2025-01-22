package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.RegistroClienteViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service("UsuarioService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefonoClienteRepository telefonoClienteRepository;

    @Autowired
    private DireccionClienteRepository direccionClienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    public boolean existeEmail(String email) {
        Cliente cliente = clienteRepository.buscarPorEmail(email);
        return cliente != null;
    }

    @Override
    @Transactional
    public Cliente registrarCliente(RegistroClienteViewModel registroClienteViewModel) {
        Cliente cliente = new Cliente();
        cliente.setEmail(registroClienteViewModel.getEmail());
        cliente.setPassword(registroClienteViewModel.getPassword());
        cliente.setApellido(registroClienteViewModel.getApellido());
        cliente.setTipo_cliente(registroClienteViewModel.getTipo_cliente());
        cliente.setF_nac(registroClienteViewModel.getF_nac());
        cliente.setF_registro(registroClienteViewModel.getF_registro());
        cliente.setNombre(registroClienteViewModel.getNombre());
        cliente.setNro_doc(registroClienteViewModel.getNro_doc());
        cliente.setTipo_doc(registroClienteViewModel.getTipo_doc());

        clienteRepository.guardar(cliente);
        return cliente;
    }

    @Override
    public boolean validarCredenciales(String email, String clave) {
        return false;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return null;
    }

    @Override
    public void modificar(Cliente clienteLogueado) {
        clienteRepository.modificar(clienteLogueado);
    }

    @Override
    public void registrarTelefono(TelefonoCliente telefonoCliente) {
        telefonoClienteRepository.agregar(telefonoCliente);
    }

    @Override
    public void modificarTelefono(TelefonoCliente telefonoCliente){
        telefonoClienteRepository.modificar(telefonoCliente);
    }

    @Override
    public void eliminarTelefono(TelefonoCliente telefonoCliente) {
        telefonoClienteRepository.eliminar(telefonoCliente);
    }

    @Override
    public void registrarDireccion(DireccionCliente direccionCliente) {
        direccionClienteRepository.agregar(direccionCliente);
    }

    @Override
    public void modificarDireccion(DireccionCliente direccionCliente){
        direccionClienteRepository.modificar(direccionCliente);
    }

    @Override
    public void eliminarDireccion(DireccionCliente direccionCliente) {
        direccionClienteRepository.eliminar(direccionCliente);
    }

}
