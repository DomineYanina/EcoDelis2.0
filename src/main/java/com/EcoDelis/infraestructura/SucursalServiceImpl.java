package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
    @Transactional
    public Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                              DireccionSucursal direccionSucursal, Local local) {
        LocalDate fechaLocal = LocalDate.now();
        Date fechaActual = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Sucursal sucursal = new Sucursal();

        sucursal.setNombre(registroSucursalViewModel.getNombre());
        sucursal.setDireccion(registroSucursalViewModel.getDireccion());
        sucursal.setTelefonos(registroSucursalViewModel.getTelefonos());
        sucursal.setNombre(registroSucursalViewModel.getNombre());
        sucursal.setF_registro(fechaLocal);
        sucursal.setTipoSuscripcion(registroSucursalViewModel.getTipoSuscripcion());
        sucursal.setLocal(local);
        sucursal.setDireccion(direccionSucursal);
        sucursal.setTipoLocal(registroSucursalViewModel.getTipoLocal());

        sucursalRepository.guardar(sucursal);

        return sucursal;
    }

    public boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel, Local local){
        Sucursal sucursal = sucursalRepository.buscarPorNombre(registroSucursalViewModel.getNombre());
        return sucursal != null;
    }

    public Sucursal buscarSucuralPorNombre(String nombre){
        return sucursalRepository.buscarPorNombre(nombre);
    }

    @Override
    @Transactional
    public void modificar(Sucursal sucursal){
        sucursalRepository.modificar(sucursal);
    }

    @Override
    @Transactional
    public void eliminar(Sucursal sucursal) {
        sucursalRepository.eliminar(sucursal);
    }

    @Override
    public List<Promocion> obtenerPromocionesPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerPromocionesPorSucursal(sucursal);
    }

    @Override
    public List<Pedido> obtenerPedidosPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerPedidos(sucursal);
    }

    @Override
    public List<Pedido> obtenerPedidosNoConfirmadosPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerPedidosNoConfirmados(sucursal);
    }

    @Override
    public List<Pedido> obtenerPedidosConfirmadosPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerPedidosConfirmados(sucursal);
    }

    @Override
    public List<Pedido> obtenerPedidosEntregadosPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerPedidosEntregados(sucursal);
    }

    @Override
    public List<Item> obtenerItemsPorSucursal(Sucursal sucursal) {
        return sucursalRepository.obtenerItemsPorSucursal(sucursal);
    }

}
