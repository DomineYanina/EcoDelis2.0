package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Local;
import com.EcoDelis.dominio.Promocion;
import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.dominio.SucursalRepository;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service("SucursalService")
@Transactional
public class SucursalServiceImpl {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalRepositoryImpl sucursalRepositoryImpl;

    public SucursalServiceImpl(SucursalRepository sucursalRepository, SucursalRepositoryImpl sucursalRepositoryImpl) {
        this.sucursalRepository = sucursalRepository;
        this.sucursalRepositoryImpl = sucursalRepositoryImpl;
    }

    public Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel, HttpSession httpSession){

        LocalDate fechaLocal = LocalDate.now();
        Date fechaActual = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Sucursal sucursal = new Sucursal();

        sucursal.setNombre(registroSucursalViewModel.getNombre());
        sucursal.setDireccion(registroSucursalViewModel.getDireccion());
        sucursal.setResponsable(registroSucursalViewModel.getResponsable());
        sucursal.setTelefonos(registroSucursalViewModel.getTelefonos());
        sucursal.setF_registro(fechaActual);

        return sucursal;
    }

    public boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel){
        Sucursal sucursal = sucursalRepository.buscarPorNombre(registroSucursalViewModel.getNombre());
        return sucursal != null;
    }

    public Sucursal buscarSucuralPorNombre(String nombre){
        return sucursalRepository.buscarPorNombre(nombre);
    }

    public void modificar(Sucursal sucursal){
        sucursalRepository.modificar(sucursal);
    }

}
