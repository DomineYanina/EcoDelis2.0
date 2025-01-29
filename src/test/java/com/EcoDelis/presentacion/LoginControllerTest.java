package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

class LoginControllerTest {

    @InjectMocks
    private LoginController controladorLogin;

    @Mock
    private Cliente usuarioMock;

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpSession sessionMock;

    @Mock
    private LoginService servicioLoginMock;

    @Mock
    private ClienteService servicioClienteMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(usuarioMock.getEmail()).thenReturn("domineyanina@hotmail.com");
    }

    @Test
    void queAlIngresarElMailCorrectoYElPasswordIncorrectoMeDevuelvaErrorYNoCambieDeVista() {
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

    /*@Test
    void queAlIntentarIrALaPantallaDeLogueoSiYaEstoyLogueadoMeRedirijaAlHome() {
        // Aquí se simularía el login fallido
    }*/
}
