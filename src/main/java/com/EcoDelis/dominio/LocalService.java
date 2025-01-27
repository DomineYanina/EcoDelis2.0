package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.RegistroLocalViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface LocalService {

    boolean validarCredenciales(String email, String clave);

    Local buscarPorEmail(String email);

    Local registrarLocal(RegistroLocalViewModel registroLocalViewModel);

    boolean existeEmail(String email);

    boolean existeSucursal(SucursalViewModel sucursalViewModel, Local local);

    void eliminarSucursal(SucursalViewModel sucursalViewModel);

    Sucursal buscarSucursalPorNombre(String nombre, Local local);

    void modificar(Local localExistente);

    void registrarLocalPrimerPaso(Local local);

    List<Sucursal> obtenerSucursalesPorLocal(Local local);

    List<Local> filtrarLocalesPorTipoLocal(TipoLocal tipoLocal);
}
