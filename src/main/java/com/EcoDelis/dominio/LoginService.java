package com.EcoDelis.dominio;
import com.EcoDelis.dominio.Excepcion.*;

public interface LoginService {
    Cliente consultarCliente(String email, String password);
    void registrar (Cliente cliente) throws ClienteExistente;
}
