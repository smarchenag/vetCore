package com.demovet.service;

import com.demovet.entity.Producto;

import java.util.List;

public interface IProductoService {

    Producto saveProducto(Producto producto);

    Producto findProductoById(Long id);

    List<Producto> findAllProductos();

    String deleteProducto(Long id);
}
