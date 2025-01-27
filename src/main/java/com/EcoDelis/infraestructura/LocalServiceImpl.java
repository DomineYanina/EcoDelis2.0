package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.RegistroLocalViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service("LocalService")
@Transactional
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Transactional
    public boolean validarCredenciales(String email, String password) {
        Local local = localRepository.buscarLocal(email,password);
        return local != null;
    }

    public Local buscarPorEmail(String email) {
        return localRepository.buscarPorEmail(email);
    }

    public Local registrarLocal(RegistroLocalViewModel registroLocalViewModel) {
        Local local = new Local();
        LocalDate fechaLocal = LocalDate.now();
        Date fechaActual = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        local.setCUIT(registroLocalViewModel.getCUIT());
        local.setNombre(registroLocalViewModel.getNombre());
        local.setEmail(registroLocalViewModel.getEmail());
        local.setPassword(registroLocalViewModel.getPassword());
        local.setF_registro(fechaActual);

        localRepository.guardar(local);
        return local;
    }

    @Override
    public boolean existeEmail(String email) {
        Local local = localRepository.buscarPorEmail(email);
        return local != null;
    }

    @Transactional
    public boolean existeSucursal(SucursalViewModel sucursalViewModel, Local local){
        Sucursal sucursal = localRepository.buscarSucursalPorNombre(sucursalViewModel.getNombre(),local);
        return sucursal != null;
    }

    @Transactional
    public void eliminarSucursal(SucursalViewModel sucursalViewModel){
        Sucursal sucursal = localRepository.buscarSucursalPorNombre(sucursalViewModel.getNombre(), sucursalViewModel.getLocal());
        localRepository.eliminarSucursal(sucursal);
    }

    @Override
    public Sucursal buscarSucursalPorNombre(String nombre, Local local) {
        return null;
    }

    @Override
    public void modificar(Local localExistente) {
        localRepository.modificar(localExistente);
    }

    @Override
    public void registrarLocalPrimerPaso(Local local) {
        localRepository.guardar(local);
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorLocal(Local local) {
        return localRepository.obtenerSucursalesPorLocal(local);
    }

    @Override
    public List<Local> filtrarLocalesPorTipoLocal(TipoLocal tipoLocal) {
        return localRepository.filtrarLocalesPorTipoLocal(tipoLocal);
    }

}
