package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

public class CalificacionControllerTest {

    @InjectMocks
    private CalificacionController calificacionController;

    @Mock
    private CalificacionService calificacionServiceMock;

    @Mock
    private PedidoService pedidoServiceMock;

    @Mock
    private ClienteService servicioClienteMock;

    private AutoCloseable closeable;

    // Declarar las variables a nivel de clase
    private ClienteLoginViewModel clienteLoginViewModel;
    private Cliente clienteMock;
    private Pedido pedidoMock;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clienteLoginViewModel = new ClienteLoginViewModel();
        clienteLoginViewModel.setEmail("domineyanina@hotmail.com");
        clienteLoginViewModel.setPassword("Boruro25");

        clienteMock = new Cliente();
        clienteMock.setNombre("Yanina Domine");

        when(servicioClienteMock.existeEmail(clienteLoginViewModel.getEmail())).thenReturn(true);
        when(servicioClienteMock.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getPassword())).thenReturn(true);
        when(servicioClienteMock.buscarPorEmail(clienteLoginViewModel.getEmail())).thenReturn(clienteMock);

        // Simulación de un pedido
        pedidoMock = new Pedido();
        pedidoMock.setCliente(clienteMock);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void queMePermitaAgregarUnaCalificacionSiElUsuarioYaSeEncuentraLogueado(){
        // Preparar el objeto CalificacionViewModel
        CalificacionViewModel calificacionViewModel = new CalificacionViewModel();
        calificacionViewModel.setComentarios("Todo perfecto");
        calificacionViewModel.setPedido(pedidoMock);
        calificacionViewModel.setPuntaje(5);

        // Mock del servicio de Calificación para que no haga nada cuando se llama al método nueva
        doNothing().when(calificacionServiceMock).nueva(any(Calificacion.class));

        // Ejecutamos el método bajo prueba
        ModelAndView resultado = calificacionController.agregarCalificacion(calificacionViewModel);

        // Comprobamos que se redirige a la vista homeCliente
        assertEquals("homeCliente", resultado.getViewName());

        // Verificar que el método 'nueva' del servicio de Calificación haya sido llamado
        verify(calificacionServiceMock).nueva(any(Calificacion.class));
    }

    @Test
    void queAlAgregarUnaCalificacionYLuegoObtenerLasCalificacionesDadasPorUnUsuarioDevuelvaTambienLaCalificacionRecienDada() {
        // Preparar el objeto CalificacionViewModel
        CalificacionViewModel calificacionViewModel = new CalificacionViewModel();
        calificacionViewModel.setComentarios("Todo perfecto");
        calificacionViewModel.setPedido(pedidoMock);
        calificacionViewModel.setPuntaje(5);

        // Lista mutable de calificaciones del usuario
        List<Calificacion> calificacionesPrevias = new ArrayList<>();
        when(servicioClienteMock.obtenerCalificacionesDadasPorCliente(clienteMock)).thenReturn(calificacionesPrevias);

        // Simular que "nueva" agrega la calificación a la lista
        doAnswer(invocation -> {
            Calificacion nuevaCalificacion = invocation.getArgument(0);
            calificacionesPrevias.add(nuevaCalificacion);
            return null;
        }).when(calificacionServiceMock).nueva(any(Calificacion.class));

        // Ejecutamos el método bajo prueba
        ModelAndView resultado = calificacionController.agregarCalificacion(calificacionViewModel);

        // Actualizar el mock para devolver la lista actualizada
        when(servicioClienteMock.obtenerCalificacionesDadasPorCliente(clienteMock)).thenReturn(calificacionesPrevias);

        // Verificamos que la cantidad de calificaciones haya aumentado en 1
        assertEquals(1, calificacionesPrevias.size());

        // Verificar que la calificación recién dada está en la lista
        assertTrue(calificacionesPrevias.stream()
                .anyMatch(c -> c.getComentarios().equals("Todo perfecto") && c.getPuntaje() == 5));
    }

    @Test
    void queAlAgregarUnaNuevaCalificacionSeRedirijaNuevamenteAHomeCliente(){
        // Preparar el objeto CalificacionViewModel
        CalificacionViewModel calificacionViewModel = new CalificacionViewModel();
        calificacionViewModel.setComentarios("Todo perfecto");
        calificacionViewModel.setPedido(pedidoMock);
        calificacionViewModel.setPuntaje(5);

        // Mock del servicio de Calificación para que no haga nada cuando se llama al método nueva
        doNothing().when(calificacionServiceMock).nueva(any(Calificacion.class));

        // Ejecutamos el método bajo prueba
        ModelAndView resultado = calificacionController.agregarCalificacion(calificacionViewModel);

        // Comprobamos que se redirige a la vista homeCliente
        assertEquals("homeCliente", resultado.getViewName());
    }
}
