package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    HttpSession session;

    private ClienteService clienteService;
    private LocalService localService;

    @Autowired
    public LoginController(ClienteService clienteService, LocalService localService){
        this.clienteService = clienteService;
        this.localService = localService;
    }

    public LoginController(LoginService servicioLoginMock) {
    }

    @GetMapping("/loginCliente")
    public ModelAndView mostrarLoginCliente(){
        ModelAndView mv = new ModelAndView("loginCliente");
        mv.addObject("cliente", new ClienteLoginViewModel());
        return mv;
    }

    @GetMapping("/loginLocal")
    public ModelAndView mostrarLoginLocal(){
        ModelAndView mv = new ModelAndView("loginLocal");
        mv.addObject("cliente", new ClienteLoginViewModel());
        return mv;
    }

    @PostMapping("/iniciarSesionCliente")
    public ModelAndView iniciarSesionCliente(@ModelAttribute ClienteLoginViewModel clienteLoginViewModel, HttpSession session) {
        boolean loginExitoso = clienteService.validarCredenciales(clienteLoginViewModel.getEmail(), clienteLoginViewModel.getClave());

        if (loginExitoso) {
            // Obtener el cliente y guardarlo en la sesión
            Cliente cliente = clienteService.buscarPorEmail(clienteLoginViewModel.getEmail());
            session.setAttribute("clienteLogueado", cliente);

            return new ModelAndView("home"); // Redirige al home o a la vista que quieras
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Email o contraseña incorrectos");
            modelAndView.addObject("cliente", clienteLoginViewModel);  // Asegúrate de seguir pasando el objeto cliente
            return modelAndView;
        }
    }

    @PostMapping("/iniciarSesionLocal")
    public ModelAndView iniciarSesionLocal(@ModelAttribute LocalLoginViewModel localLoginViewModel, HttpSession session) {
        boolean loginExitoso = localService.validarCredenciales(localLoginViewModel.getEmail(), localLoginViewModel.getClave());

        if (loginExitoso) {
            // Obtener el usuario y guardarlo en la sesión
            Local local = localService.buscarPorEmail(localLoginViewModel.getEmail());
            session.setAttribute("localLogueado", local);

            return new ModelAndView("home-local"); // Redirige al home o a la vista que quieras
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Email o contraseña incorrectos");
            modelAndView.addObject("local", localLoginViewModel);  // Asegúrate de seguir pasando el objeto usuario
            return modelAndView;
        }
    }

    @GetMapping("/nuevoCliente")
    public String mosrtarFormularioRegistroCliente(Model model) {
        model.addAttribute("cliente", new RegistroViewModel());
        return "nuevoCliente";
    }


    @GetMapping("/nuevoLocal")
    public String mosrtarFormularioRegistroLocal(Model model) {
        model.addAttribute("local", new RegistroLocalViewModel());
        return "nuevoLocal";
    }

}
