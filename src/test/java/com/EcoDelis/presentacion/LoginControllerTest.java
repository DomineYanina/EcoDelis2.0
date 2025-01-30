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

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

class LoginControllerTest {

    @InjectMocks
    private LoginController controladorLogin;

    @Mock
    private HttpSession sessionMock;

    @Mock
    private LoginService servicioLoginMock;

    @Mock
    private ClienteService servicioClienteMock;

    @Mock
    private LocalService servicioLocalMock;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void clienteQueAlIngresarElMailCorrectoYElPasswordIncorrectoMeDevuelvaErrorYNoCambieDeVista() {
        // Simulamos los datos ingresados por el usuario
        ClienteLoginViewModel clienteLoginViewModel = new ClienteLoginViewModel();
        clienteLoginViewModel.setEmail("domineyanina@hotmail.com");
        clienteLoginViewModel.setPassword("claveIncorrecta");

        // Simulamos que el email existe en la base de datos
        when(servicioClienteMock.existeEmail(clienteLoginViewModel.getEmail())).thenReturn(true);

        // Simulamos que las credenciales son incorrectas
        when(servicioClienteMock.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getPassword()))
                .thenReturn(false);

        // Creamos un mock de BindingResult
        BindingResult bindingResultMock = mock(BindingResult.class);

        // Ejecutamos el método logueoCliente en LoginController
        ModelAndView resultado = controladorLogin.logueoCliente(clienteLoginViewModel, bindingResultMock, sessionMock);

        // Verificamos que la vista devuelta sea "loginCliente"
        assertEquals("loginCliente", resultado.getViewName());

        // Verificamos que se haya agregado un mensaje de error al modelo
        assertTrue(resultado.getModel().containsKey("error"));
        assertEquals("La clave ingresada es incorrecta", resultado.getModel().get("error"));

        // Verificamos que el usuario NO se almacene en sesión
        verify(sessionMock, never()).setAttribute(eq("clienteLogueado"), any());
    }

    @Test
    void localQueAlIngresarElMailCorrectoYElPasswordIncorrectoMeDevuelvaErrorYNoCambieDeVista(){
        LocalLoginViewModel localLoginViewModel = new LocalLoginViewModel();
        localLoginViewModel.setEmail("domineyanina@hotmail.com");
        localLoginViewModel.setPassword("claveIncorrecta");

        //Simulamos que el email existe en la base de datos
        when(servicioLocalMock.existeEmail(localLoginViewModel.getEmail())).thenReturn(true);

        //Simulamos que las credenciales son incorrectas
        when(servicioLocalMock.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getPassword())).thenReturn(false);

        //Creamos un mock de BindingResult
        BindingResult bindingResultMock = mock(BindingResult.class);

        //Ejecutamos el metodo logueoLocal en loginController
        ModelAndView resultado = controladorLogin.logueoLocal(localLoginViewModel, bindingResultMock, sessionMock);

        //Verificamos que la vista devuelta sea "loginLocal"
        assertEquals("loginLocal", resultado.getViewName());

        //Verificamos que se haya agregado un mensaje de error al modelo
        assertTrue(resultado.getModel().containsKey("error"));
        assertEquals("La clave ingresada es incorrecta", resultado.getModel().get("error"));

        //Verificamos que el local no se almacene en sesión
        verify(sessionMock, never()).setAttribute(eq("localLogueado"), any());
    }

    @Test
    void clienteQueAlIngresarElMailCorrectoYElPasswordCorrectoNoDevuelvaErrorYCambieALaVistaHomeClienteYSeAlmaceneElClienteEnSesion(){
        ClienteLoginViewModel clienteLoginViewModel = new ClienteLoginViewModel();
        clienteLoginViewModel.setEmail("domineyanina@hotmail.com");
        clienteLoginViewModel.setPassword("Boruro25");
        Cliente clienteMock = new Cliente();
        clienteMock.setNombre("Yanina Domine");
        when(servicioClienteMock.existeEmail(clienteLoginViewModel.getEmail())).thenReturn(true);
        when(servicioClienteMock.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getPassword())).thenReturn(true);
        when(servicioClienteMock.buscarPorEmail(clienteLoginViewModel.getEmail())).thenReturn(clienteMock); // Asegurar que devuelva un cliente válido
        BindingResult bindingResultMock = mock(BindingResult.class);
        ModelAndView resultado = controladorLogin.logueoCliente(clienteLoginViewModel, bindingResultMock, sessionMock);
        assertEquals("homeCliente", resultado.getViewName());
        verify(sessionMock).setAttribute(eq("clienteLogueado"), any(ClienteViewModel.class)); // Verificar que se guarda el Cliente
    }

    @Test
    void localQueAlIngresarElMailCorrectoYElPasswordCorrectoNoDevuelvaErrorYCambieALaVistaHomeLocalYSeAlmaceneElLocalEnSesion(){
        LocalLoginViewModel localLoginViewModel = new LocalLoginViewModel();
        localLoginViewModel.setEmail("domineyanina@hotmail.com");
        localLoginViewModel.setPassword("Prueba1");
        Local localMock = new Local();
        localMock.setNombre("Container");
        when(servicioLocalMock.existeEmail(localLoginViewModel.getEmail())).thenReturn(true);
        when(servicioLocalMock.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getPassword())).thenReturn(true);
        when(servicioLocalMock.buscarPorEmail(localLoginViewModel.getEmail())).thenReturn(localMock);
        BindingResult bindingResultMock = mock(BindingResult.class);
        ModelAndView resultado = controladorLogin.logueoLocal(localLoginViewModel, bindingResultMock, sessionMock);
        assertEquals("homeLocal", resultado.getViewName());
        verify(sessionMock).setAttribute(eq("localLogueado"), any(LocalViewModel.class));
    }

    @Test
    void clienteQueAlCerrarSesionSeRedirijaALaVistaLoginClienteYSeElimineElObjetoClienteLogueadoDeLaSesion(){
        ClienteLoginViewModel clienteLoginViewModel = new ClienteLoginViewModel();
        clienteLoginViewModel.setEmail("domineyanina@hotmail.com");
        clienteLoginViewModel.setPassword("claveIncorrecta");
        Cliente clienteMock = new Cliente();
        clienteMock.setNombre("Yanina Domine");
        when(servicioClienteMock.existeEmail(clienteLoginViewModel.getEmail())).thenReturn(true);
        when(servicioClienteMock.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getPassword())).thenReturn(true);
        when(servicioClienteMock.buscarPorEmail(clienteLoginViewModel.getEmail())).thenReturn(clienteMock);
        BindingResult bindingResultMock = mock(BindingResult.class);
        ModelAndView resultadoLogin = controladorLogin.logueoCliente(clienteLoginViewModel, bindingResultMock, sessionMock);
        ModelAndView resultadoCierreSesion = controladorLogin.cerrarSesionCliente(sessionMock);
        assertEquals("loginCliente", resultadoCierreSesion.getViewName());
        verify(sessionMock).removeAttribute("clienteLogueado");
    }

    @Test
    void localQueAlCerrarSesionSeRedirijaALaVistaLoginLocalYSeElimineElObjetoLocalLogueadoDeLaSesion(){
        LocalLoginViewModel localLoginViewModel = new LocalLoginViewModel();
        localLoginViewModel.setEmail("domineyanina@hotmail.com");
        localLoginViewModel.setPassword("Prueba1");
        Local localMock = new Local();
        localMock.setNombre("Container");
        when(servicioLocalMock.existeEmail(localLoginViewModel.getEmail())).thenReturn(true);
        when(servicioLocalMock.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getPassword())).thenReturn(true);
        when(servicioLocalMock.buscarPorEmail(localLoginViewModel.getEmail())).thenReturn(localMock);
        BindingResult bindingResultMock = mock(BindingResult.class);
        ModelAndView resultadoLogin = controladorLogin.logueoLocal(localLoginViewModel, bindingResultMock, sessionMock);
        ModelAndView resultadoCierreSesion = controladorLogin.cerrarSesionLocal(sessionMock);
        assertEquals("loginLocal", resultadoCierreSesion.getViewName()); // Verifica que se redirige al login
        verify(sessionMock).removeAttribute("localLogueado"); // Verifica que se eliminó el atributo de sesión
    }

    @Test
    void clienteQueAlQuererIrAlHomeSinHaberIniciadoSesionMeRedirijaALaVistaLoginCliente(){
        ModelAndView resultado = controladorLogin.irAHomeCliente(sessionMock);
        assertEquals("loginCliente", resultado.getViewName());
    }

    @Test
    void localQueAlQuererIrAlHomeSinHaberIniciadoSesionMeRedirijaALaVistaLoginLocal(){
        ModelAndView resultado = controladorLogin.irAHomeLocal(sessionMock);
        assertEquals("loginLocal", resultado.getViewName());
    }
}
