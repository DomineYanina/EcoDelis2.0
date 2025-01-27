package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository {
    Local buscarLocal(String email, String password);

    Local buscarPorEmail(String email);

    void guardar(Local local);

    Sucursal buscarSucursalPorNombre(String nombre, Local local);

    void eliminarSucursal(Sucursal sucursal);

    void modificar(Local localExistente);

    List<Sucursal> obtenerSucursalesPorLocal(Local local);

    List<Local> filtrarLocalesPorTipoLocal(TipoLocal tipoLocal);
}
