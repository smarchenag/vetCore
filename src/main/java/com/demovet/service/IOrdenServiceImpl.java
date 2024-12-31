package com.demovet.service;

import com.demovet.entity.Orden;
import com.demovet.entity.Producto;
import com.demovet.entity.Usuario;
import com.demovet.repository.OrdenRepository;
import com.demovet.repository.ProductoRepository;
import com.demovet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IOrdenServiceImpl implements IOrdenService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Orden crearOrden(Orden orden, Long idUsuario) {
        Usuario userFromDB = usuarioRepository.findById(idUsuario).orElseThrow( ()-> new IllegalArgumentException("El usuario con id: " + idUsuario + " no existe") );
        orden.setUsuarioOrden(userFromDB);
        return ordenRepository.save(orden);
    }

    @Override
    public Orden addProducto(Long idOrden, Long idproducto, int cantidad) {

        Producto productoFromDB = productoRepository.findById(idproducto).orElseThrow( () -> new IllegalArgumentException("Producto no encontrado"));

        Orden ordenFromDB = ordenRepository.findById(idOrden).orElseThrow( ()-> new IllegalArgumentException("Orden con id: " + idOrden + " no existe") );

        ordenFromDB.getProductosOrden().add(productoFromDB);
        BigDecimal total = ordenFromDB.getTotal();
        ordenFromDB.setTotal(total.add(BigDecimal.valueOf(productoFromDB.getPrecio() * cantidad)));

        return ordenRepository.save(ordenFromDB);
    }

    @Override
    public List<Orden> consultarOrdenes() {
        return ordenRepository.findAll();
    }
}
