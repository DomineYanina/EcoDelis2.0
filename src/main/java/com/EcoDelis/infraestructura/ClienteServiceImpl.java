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
    public Cliente registrarCliente(ClienteViewModel clienteViewModel) {
        Local local = new Local();
        LocalDate fechaLocal = LocalDate.now();
        Date fechaActual = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Cliente cliente = new Cliente();
        cliente.setFregistro(fechaActual);
        cliente.setEmail(clienteViewModel.getEmail());
        cliente.setPassword(clienteViewModel.getPassword());
        cliente.setApellido(clienteViewModel.getApellido());
        cliente.setTipocliente(clienteViewModel.getTipocliente());
        cliente.setFnac(clienteViewModel.getFnac());
        cliente.setFregistro(clienteViewModel.getFregistro());
        cliente.setNombre(clienteViewModel.getNombre());
        cliente.setNrodoc(clienteViewModel.getNrodoc());
        cliente.setTipodoc(clienteViewModel.getTipodoc());

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

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Cliente cliente) {
        return clienteRepository.obtenerPedidosPorCliente(cliente);
    }

}
