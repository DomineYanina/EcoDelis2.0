package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.RegistroLocalViewModel;
import com.EcoDelis.presentacion.RegistroViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service("LocalService")
@Transactional
public class LocalServiceImpl {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalRepositoryImpl localRepositoryImpl;

    @Transactional
    public boolean validarCredenciales(String email, String password) {
        Local local = localRepository.buscarLocal(email,password);
        return local != null;
    }

    public Local buscarPorEmail(String email) {
        return localRepository.buscarPorEmail(email);
    }

    @Transactional
    public boolean existeEmail(String email) {
        Local local = localRepository.buscarPorEmail(email);
        return local != null;
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

    @Transactional
    public boolean existeSucursal(SucursalViewModel sucursalViewModel){
        Sucursal sucursal = localRepository.buscarSucursalPorNombre(sucursalViewModel.getNombre());
        return sucursal != null;
    }

    @Transactional
    public void eliminarSucursal(SucursalViewModel sucursalViewModel){
        Sucursal sucursal = localRepository.buscarSucursalPorNombre(sucursalViewModel.getNombre());
        localRepository.eliminarSucursal(sucursal);
    }

}
