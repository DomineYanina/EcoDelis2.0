package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository {

    void guardar(Sucursal sucursal);

    Sucursal buscarPorNombre(String nombre);

    void modificar(Sucursal sucursal);

    void eliminar(Sucursal sucursal);

    List<Pedido> obtenerPedidos(Sucursal sucursal);

    List<Promocion> obtenerPromocionesPorSucursal(Sucursal sucursal);

    List<Pedido> obtenerPedidosNoConfirmados(Sucursal sucursal);

    List<Pedido> obtenerPedidosConfirmados(Sucursal sucursal);

    List<Pedido> obtenerPedidosEntregados(Sucursal sucursal);

    List<Item> obtenerItemsPorSucursal(Sucursal sucursal);
}
