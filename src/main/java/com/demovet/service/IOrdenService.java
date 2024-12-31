package com.demovet.service;

import com.demovet.entity.Orden;
import com.demovet.entity.Producto;

import java.util.List;

public interface IOrdenService {

    Orden crearOrden(Orden orden, Long idUSuario);

    Orden addProducto(Long id, Long idproducto, int cantidad);

    List<Orden> consultarOrdenes();
}
