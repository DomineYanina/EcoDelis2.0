package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.ClienteViewModel;
import com.EcoDelis.presentacion.RegistroClienteViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

    @Override
    @Transactional
    public boolean existeEmail(String email) {
        Cliente cliente = clienteRepository.buscarPorEmail(email);
        return cliente != null;
    }

    @Override
    @Transactional
    public Cliente registrarCliente(Cliente cliente) {
        clienteRepository.guardar(cliente);
        return cliente;
    }

    @Override
    public boolean validarCredenciales(String email, String clave) {
        Cliente cliente = clienteRepository.buscarCliente(email,clave);
        return cliente != null;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.buscarPorEmail(email);
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

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Cliente cliente) {
        return clienteRepository.obtenerPedidosPorCliente(cliente);
    }

    @Override
    public List<Calificacion> obtenerCalificacionesDadasPorCliente(Cliente cliente) {
        return clienteRepository.obtenerCalificacionesDadasPorCliente(cliente);
    }

    @Override
    public List<DireccionCliente> obtenerDireccionesPorCliente(Cliente cliente) {
        return clienteRepository.obtenerDireccionesPorCliente(cliente);
    }

    @Override
    public List<TelefonoCliente> obtenerTelefonosPorCliente(Cliente cliente) {
        return clienteRepository.obtenerTelefonosPorCliente(cliente);
    }

    @Override
    public boolean chequearDireccionYaExistente(DireccionCliente direccionCliente, Cliente clienteLogueado) {
        if(clienteRepository.chequearDireccionYaExistente(direccionCliente,clienteLogueado)==null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean chequearTelefonoYaExistente(TelefonoCliente telefonoCliente){
        if(clienteRepository.chequearTelefonoYaExistente(telefonoCliente) == null){
            return false;
        } else {
            return true;
        }
    }

}
