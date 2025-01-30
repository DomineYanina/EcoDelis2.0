package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService servicioClienteMock;

    @Mock
    private PedidoService pedidoServiceMock;

    @Mock
    private HttpSession sessionMock;

    private AutoCloseable closeable;

    private RegistroClienteViewModel registroClienteViewModel;
    private ClienteLoginViewModel clienteLoginViewModel;
    private Cliente clienteMock;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void queNoPermitaIrAModificarLosDatosDeUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irAModificarCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrAAgregarUnaDireccionDeUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irAAgregarUnaDireccionCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrAAgregarUnTelefonoDeUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irAAgregarUnTelefonoCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrARegistrarUnClienteSiYaHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irARegistrarNuevoCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrAModificarElTipoDeSuscripcionDeUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irAModificarTipoSuscripcionCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrAModificarLaClaveDeUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.irACambiarPasswordCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queNoPermitaIrAVerLosPedidosHechosPorUnClienteSiNoHaIniciadoSesion(){
        ModelAndView resultado = clienteController.verMisPedidos(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void queAlQuererModificarLosDatosDeUnClienteSeAlmacenenCorrectamente(){
        // Preparar datos de prueba
        ClienteViewModel clienteViewModel = new ClienteViewModel();
        clienteViewModel.setEmail("cliente@example.com");
        clienteViewModel.setNombre("Nuevo Nombre");

        Cliente clienteExistente = new Cliente();
        clienteExistente.setEmail("cliente@example.com");
        clienteExistente.setNombre("Nombre Anterior");

        // Simular sesión con cliente logueado
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteExistente);
        when(sessionMock.getAttribute("localLogueado")).thenReturn(null);

        // Simular la búsqueda del cliente por email
        when(servicioClienteMock.buscarPorEmail(clienteViewModel.getEmail())).thenReturn(clienteExistente);

        // Ejecutar el método del controlador
        ModelAndView resultado = clienteController.modificarCliente(sessionMock, clienteViewModel);

        // Verificar que el servicio modificar fue llamado con los datos correctos
        verify(servicioClienteMock).modificar(any(Cliente.class));

        // Verificar que la vista resultante sea "homeCliente"
        assertEquals("homeCliente", resultado.getViewName());
    }

    @Test
    void queNoQuererAgregarUnaDireccionDeUnClienteSeAlmaceneCorrectamente(){
        DireccionClienteViewModel direccionClienteViewModel = new DireccionClienteViewModel();
        direccionClienteViewModel.setCalle("Calle Ejemplo");
        direccionClienteViewModel.setCodigopostal(1234);
        direccionClienteViewModel.setLocalidad("San Justo");
        direccionClienteViewModel.setProvincia("Buenos Aires");

        // Lista mutable de calificaciones del usuario
        List<DireccionCliente> direcciones = new ArrayList<>();
        when(servicioClienteMock.obtenerDireccionesPorCliente(clienteMock)).thenReturn(direcciones);

        // Simular que "nueva" agrega la calificación a la lista
        doAnswer(invocation -> {
            DireccionCliente nuevaDireccion = invocation.getArgument(0);
            direcciones.add(nuevaDireccion);
            return null;
        }).when(servicioClienteMock).registrarDireccion(any(DireccionCliente.class));

        // Ejecutamos el método bajo prueba
        ModelAndView resultado = clienteController.agregarDireccionCliente(direccionClienteViewModel, sessionMock);

        // Actualizar el mock para devolver la lista actualizada
        when(servicioClienteMock.obtenerDireccionesPorCliente(clienteMock)).thenReturn(direcciones);

        // Verificamos que la cantidad de calificaciones haya aumentado en 1
        assertEquals(1, direcciones.size());

        // Verificar que la calificación recién dada está en la lista
        assertTrue(direcciones.stream()
                .anyMatch(c -> c.getCalle().equals("Calle Ejemplo") && c.getNumero() == 1234));
    }

    @Test
    void queAlQuererAgregarUnTelefonoDeUnClienteSeAlmaceneCorrectamente(){
        TelefonoClienteViewModel telefonoClienteViewModel = new TelefonoClienteViewModel();
        telefonoClienteViewModel.setNumero(123456789);
        telefonoClienteViewModel.setTipo(TipoTelefono.Celular);

        List<TelefonoCliente> telefonos = new ArrayList<>();
        when(servicioClienteMock.obtenerTelefonosPorCliente(clienteMock)).thenReturn(telefonos);

        doAnswer(invocation -> {
            TelefonoCliente telefonoNuevo = invocation.getArgument(0);
            telefonos.add(telefonoNuevo);
            return null;
        }).when(servicioClienteMock).registrarTelefono(any(TelefonoCliente.class));

        ModelAndView resultado = clienteController.agregarTelefonoCliente(telefonoClienteViewModel, sessionMock);

        when(servicioClienteMock.obtenerTelefonosPorCliente(clienteMock)).thenReturn(telefonos);

        assertEquals(1, telefonos.size());

        assertTrue(telefonos.stream().anyMatch(c -> c.getTipo().equals(TipoTelefono.Celular) && c.getNumero() == 123456789));
    }

    @Test
    void queAlIngresarUnaClaveDistintaALaActualLaValidacionDevuelvaUnError() {
        ClienteLoginViewModel clienteViewModel = new ClienteLoginViewModel();
        clienteViewModel.setEmail("cliente@example.com");
        clienteViewModel.setPassword("claveIncorrecta");

        BindingResult bindingResult = mock(BindingResult.class);
        when(servicioClienteMock.validarCredenciales(clienteViewModel.getEmail(), clienteViewModel.getPassword()))
                .thenReturn(false);

        ModelAndView resultado = clienteController.validarPasswordActual(bindingResult, clienteViewModel);

        assertEquals("cambiarPasswordCliente", resultado.getViewName());
        assertTrue(resultado.getModel().containsKey("error"));
        assertEquals("La clave es incorrecta", resultado.getModel().get("error"));
        verify(bindingResult).reject("error", "La clave es incorrecta");
    }

    @Test
    void queAlCancelarUnPedidoSuEstadoCambie(){
        // Mock de sesión y pedido
        HttpSession sessionMock = mock(HttpSession.class);
        PedidoViewModel pedidoViewModel = new PedidoViewModel();
        Cliente clienteMock = mock(Cliente.class);
        Pedido pedidoMock = mock(Pedido.class);

        // Simular cliente logueado en sesión
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteMock);

        // Simular transformación del modelo de vista a entidad Pedido
        doAnswer(invocation -> {
            Pedido pedidoActualizado = invocation.getArgument(0);
            assertEquals(EstadoPedido.Cancelado, pedidoActualizado.getEstado());
            return null;
        }).when(pedidoServiceMock).actualizar(any(Pedido.class));

        // Ejecutar el método
        ModelAndView resultado = clienteController.cancelarPedidoC(sessionMock, pedidoViewModel);

        // Verificar que la vista retornada sea "homeCliente"
        assertEquals("homeCliente", resultado.getViewName());

        // Verificar que el método de actualización se haya llamado una vez
        verify(pedidoServiceMock, times(1)).actualizar(any(Pedido.class));
    }

    @Test
    void queAlCalificarUnPedidoSuEstadoCambie(){
        // Mock de sesión y pedido
        HttpSession sessionMock = mock(HttpSession.class);
        PedidoViewModel pedidoViewModel = new PedidoViewModel();
        Cliente clienteMock = mock(Cliente.class);
        Pedido pedidoMock = mock(Pedido.class);

        // Simular cliente logueado en sesión
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteMock);

        // Simular transformación del modelo de vista a entidad Pedido
        doAnswer(invocation -> {
            Pedido pedidoActualizado = invocation.getArgument(0);
            assertEquals(EstadoPedido.Calificado, pedidoActualizado.getEstado());
            return null;
        }).when(pedidoServiceMock).actualizar(any(Pedido.class));

        // Ejecutar el método
        ModelAndView resultado = clienteController.cancelarPedidoC(sessionMock, pedidoViewModel);

        // Verificar que la vista retornada sea "homeCliente"
        assertEquals("homeCliente", resultado.getViewName());

        // Verificar que el método de actualización se haya llamado una vez
        verify(pedidoServiceMock, times(1)).actualizar(any(Pedido.class));
    }

    @Test
    void queAlModificarElTipoDeSuscripcionDeUnClienteSeAlmaceneCorrectamente(){
        // Preparar datos de prueba
        ClienteViewModel clienteViewModel = new ClienteViewModel();
        clienteViewModel.setEmail("cliente@example.com");
        clienteViewModel.setTipocliente(TipoCliente.Basico);

        Cliente clienteExistente = new Cliente();
        clienteExistente.setEmail("cliente@example.com");
        clienteExistente.setTipocliente(TipoCliente.Premium);

        // Simular sesión con cliente logueado
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteExistente);
        when(sessionMock.getAttribute("localLogueado")).thenReturn(null);

        // Simular la búsqueda del cliente por email
        when(servicioClienteMock.buscarPorEmail(clienteViewModel.getEmail())).thenReturn(clienteExistente);

        // Ejecutar el método del controlador
        ModelAndView resultado = clienteController.modificarCliente(sessionMock, clienteViewModel);

        // Verificar que el servicio modificar fue llamado con los datos correctos
        verify(servicioClienteMock).modificar(any(Cliente.class));

        // Verificar que la vista resultante sea "homeCliente"
        assertEquals("homeCliente", resultado.getViewName());
    }

    @Test
    void queAlRegistrarUnNuevoClienteSeAlmaceneCorrectamente(){
        // Preparar datos de prueba
        ClienteViewModel clienteViewModel = new ClienteViewModel();
        clienteViewModel.setEmail("test@email.com");
        clienteViewModel.setNombre("Test");
        clienteViewModel.setApellido("Usuario");

        Cliente clienteRegistrado = new Cliente();
        clienteRegistrado.setEmail("test@email.com");
        clienteRegistrado.setNombre("Test");
        clienteRegistrado.setApellido("Usuario");

        // Configurar mocks
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(null);
        when(servicioClienteMock.registrarCliente(any(Cliente.class))).thenReturn(clienteRegistrado);

        // Ejecutar el método
        ModelAndView resultado = clienteController.registrarNuevoCliente(sessionMock, clienteViewModel);

        // Verificar que se almacenó el cliente en la sesión
        verify(sessionMock).setAttribute(eq("clienteLogueado"), any(Cliente.class));

        // Verificar que la vista retornada es "homeCliente"
        assertEquals("homeCliente", resultado.getViewName());

        // Verificar que el modelo contiene el cliente registrado
        assertEquals(clienteViewModel, resultado.getModel().get("cliente"));
    }

    @Test
    void queAlIntentarAgregarUnTelefonoYaExistenteMeDevuelvaError(){
        // Datos de prueba
        Cliente clienteLogueado = new Cliente();
        clienteLogueado.setEmail("test@email.com");

        TelefonoClienteViewModel telefonoClienteViewModel = new TelefonoClienteViewModel();
        telefonoClienteViewModel.setNumero(123456789);

        TelefonoCliente telefonoCliente = new TelefonoCliente();
        telefonoCliente.setNumero(123456789);

        // Configurar mocks
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteLogueado);
        when(servicioClienteMock.chequearTelefonoYaExistente(any(TelefonoCliente.class))).thenReturn(true);

        // Ejecutar el método
        ModelAndView resultado = clienteController.agregarTelefonoCliente(telefonoClienteViewModel, sessionMock);

        // Verificar que se devuelve la vista "agregarTelefonoCliente"
        assertEquals("agregarTelefonoCliente", resultado.getViewName());

        // Verificar que se agregó el mensaje de error
        assertEquals("El teléfono ingresado ya existe", resultado.getModel().get("error"));

        // Verificar que NO se llamó a registrarTelefono
        verify(servicioClienteMock, never()).registrarTelefono(any(TelefonoCliente.class));
    }

    @Test
    void queAlIntentarAgregarUnaDireccionYaExistenteMeDevuelvaError(){
        // Datos de prueba
        Cliente clienteLogueado = new Cliente();
        clienteLogueado.setEmail("test@email.com");

        DireccionClienteViewModel direccionClienteViewModel = new DireccionClienteViewModel();
        direccionClienteViewModel.setNumero(1234);
        direccionClienteViewModel.setCalle("Prueba");
        direccionClienteViewModel.setProvincia("Buenos Aires");
        direccionClienteViewModel.setLocalidad("San Justo");

        DireccionCliente direccionCliente = new DireccionCliente();
        direccionCliente.setNumero(1234);
        direccionCliente.setCalle("Prueba");
        direccionCliente.setProvincia("Buenos Aires");
        direccionCliente.setLocalidad("San Justo");

        // Configurar mocks
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(clienteLogueado);
        when(servicioClienteMock.chequearTelefonoYaExistente(any(TelefonoCliente.class))).thenReturn(true);

        // Ejecutar el método
        ModelAndView resultado = clienteController.agregarDireccionCliente(direccionClienteViewModel, sessionMock);

        // Verificar que se devuelve la vista "agregarTelefonoCliente"
        assertEquals("agregarDireccionCliente", resultado.getViewName());

        // Verificar que se agregó el mensaje de error
        assertEquals("La dirección ingresada ya existe", resultado.getModel().get("error"));

        // Verificar que NO se llamó a registrarTelefono
        verify(servicioClienteMock, never()).registrarDireccion(any(DireccionCliente.class));
    }

    @Test
    void queAlIntentarRegistrarUnMailYaExistenteMeDevuelvaError() {
        // Datos de prueba
        RegistroClienteViewModel registroClienteViewModel = new RegistroClienteViewModel();
        registroClienteViewModel.setEmail("test@email.com");

        // Configurar mocks
        when(sessionMock.getAttribute("clienteLogueado")).thenReturn(null);
        when(servicioClienteMock.existeEmail("test@email.com")).thenReturn(true);

        // Ejecutar el método
        ModelAndView resultado = clienteController.chequearMailYaExistente(sessionMock, registroClienteViewModel);

        // Verificar que se devuelve la vista "registroClientePrimerPaso"
        assertEquals("registroClientePrimerPaso", resultado.getViewName());

        // Verificar que se agregó el mensaje de error
        assertEquals("Email ya existe", resultado.getModel().get("error"));

        // Verificar que se llamó al servicio para chequear si el email existe
        verify(servicioClienteMock).existeEmail("test@email.com");
    }
}
