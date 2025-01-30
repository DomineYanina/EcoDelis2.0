package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import com.EcoDelis.presentacion.SucursalViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface SucursalService {
    Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                       DireccionSucursal direccionSucursal, Local local);

    boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel, Local local);

    void modificar(Sucursal sucursal);

    void eliminar(Sucursal sucursal);

    List<Promocion> obtenerPromocionesPorSucursal(Sucursal sucursal);

    List<Pedido> obtenerPedidosPorSucursal(Sucursal sucursal);

    List<Pedido> obtenerPedidosNoConfirmadosPorSucursal(Sucursal sucursal);

    List<Pedido> obtenerPedidosConfirmadosPorSucursal(Sucursal sucursal);

    List<Pedido> obtenerPedidosEntregadosPorSucursal(Sucursal sucursal);

    List<Item> obtenerItemsPorSucursal(Sucursal sucursal);
}
