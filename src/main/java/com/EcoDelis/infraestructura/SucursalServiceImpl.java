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
                              DireccionSucursalViewModel direccionSucursalViewModel) {
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

        DireccionSucursal direccion = new DireccionSucursal();
        direccion.setCalle(direccionSucursalViewModel.getCalle());
        direccion.setNumero(direccionSucursalViewModel.getNumero());
        direccion.setLocalidad(direccionSucursalViewModel.getLocalidad());
        direccion.setProvincia(direccionSucursalViewModel.getProvincia());
        sucursal.setDireccion(direccion);

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
