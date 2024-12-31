package com.demovet.service;

import com.demovet.entity.Producto;
import com.demovet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public Producto saveProducto(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Producto findProductoById(Long id) {
        return repository.findById(id).orElseThrow( () -> new IllegalArgumentException("Id no valido"));
    }

    @Override
    public List<Producto> findAllProductos() {
        return repository.findAll();
    }

    @Override
    public String deleteProducto(Long id) {
        repository.deleteById(id);
        return "Producto Eliminado";
    }
}
