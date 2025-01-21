package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                              DireccionSucursal direccionSucursal, long idLocal) {
        LocalDate fechaLocal = LocalDate.now();
        Date fechaActual = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Sucursal sucursal = new Sucursal();

        sucursal.setNombre(registroSucursalViewModel.getNombre());
        sucursal.setDireccion(registroSucursalViewModel.getDireccion());
        sucursal.setTelefonos(registroSucursalViewModel.getTelefonos());

        // Lógica para registrar la sucursal, responsable y dirección

        sucursal.setNombre(registroSucursalViewModel.getNombre());
        sucursal.setF_registro(fechaLocal);
        sucursal.setTipoSuscripcion(registroSucursalViewModel.getTipoSuscripcion());
        //Agregar el código para setear el local a la sucursal

        sucursal.setDireccion(direccionSucursal);

        // Guardar la sucursal en la base de datos (por ejemplo, usando un repositorio)
        sucursalRepository.guardar(sucursal);

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
