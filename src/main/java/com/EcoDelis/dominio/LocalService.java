package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.RegistroLocalViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface LocalService {

    boolean validarCredenciales(String email, String clave);

    Local buscarPorEmail(String email);

    Local registrarLocal(RegistroLocalViewModel registroLocalViewModel);

    boolean existeEmail(String email);

    boolean existeSucursal(SucursalViewModel sucursalViewModel);

    void eliminarSucursal(SucursalViewModel sucursalViewModel);

    Sucursal buscarSucuralPorNombre(String nombre);

    void modificar(Local localExistente);

    void registrarLocalPrimerPaso(Local local);
}
