package com.microservice.producto.service;

import com.microservice.producto.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO obtenerProducto(Long id);
    List<ProductoDTO> listarProductos();
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
    public ProductoDTO reducirStock(Long id, int cantidad);
}
