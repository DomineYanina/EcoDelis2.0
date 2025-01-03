/*package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ControladorLoginTest {

    private LoginController controladorLogin;
    private Cliente usuarioMock;
    private DatosLogin datosLoginMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private LoginService servicioLoginMock;

    @BeforeEach
    public void init(){
        datosLoginMock = new DatosLogin("domineyanina@hotmail.com", "Prueba1");
        usuarioMock = mock(Cliente.class);
        when(usuarioMock.getEmail()).thenReturn("domineyanina@hotmail.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioLoginMock = mock(LoginService.class);
        controladorLogin = new LoginController(servicioLoginMock);
    }

    /*@Test
    public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
        // preparacion
        when(servicioLoginMock.consultarCliente(anyString(), anyString())).thenReturn(null);

        // ejecucion
        ModelAndView modelAndView = controladorLogin.iniciarSesionLocal(datosLoginMock, requestMock);

        // validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Usuario o clave incorrecta"));
        verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");
    }

    @Test
    public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setEmail("usuario@ejemplo.com");

        when(ClienteService.validarCredenciales("usuario@ejemplo.com", "contraseña")).thenReturn(true);
        when(ClienteService.buscarPorEmail("usuario@ejemplo.com")).thenReturn(cliente);

        // When
        mockMvc.perform(post("/iniciarSesionCliente")
                        .param("email", "usuario@ejemplo.com")
                        .param("clave", "contraseña"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

    }
}*/
