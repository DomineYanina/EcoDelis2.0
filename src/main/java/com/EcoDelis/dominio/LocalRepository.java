package com.EcoDelis.dominio;

public interface LocalRepository {
    Local buscarLocal(String email, String password);

    Local buscarPorEmail(String email);

    void guardar(Local local);
}
