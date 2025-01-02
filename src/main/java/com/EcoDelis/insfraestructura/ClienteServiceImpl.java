package com.EcoDelis.insfraestructura;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteRepository;
import com.EcoDelis.dominio.ClienteService;
import com.EcoDelis.presentacion.RegistroViewModel;
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
    private HttpServletRequest request;

    @Transactional
    public boolean existeEmail(String email) {
        Cliente cliente = clienteRepository.buscarPorEmail(email);
        return cliente != null;
    }

    public Cliente registrarCliente(RegistroViewModel registroViewModel) {
        Cliente cliente = new Cliente();
        cliente.setEmail(registroViewModel.getEmail());
        cliente.setPassword(registroViewModel.getPassword());
        cliente.setApellido(registroViewModel.getApellido());
        cliente.setTipo_cliente(registroViewModel.getTipo_cliente());
        cliente.setF_nac(registroViewModel.getF_nac());
        cliente.setF_registro(registroViewModel.getF_registro());
        cliente.setNombre(registroViewModel.getNombre());
        cliente.setNro_doc(registroViewModel.getNro_doc());
        cliente.setTipo_doc(registroViewModel.getTipo_doc());

        clienteRepository.guardar(cliente);
        return cliente;
    }
}
