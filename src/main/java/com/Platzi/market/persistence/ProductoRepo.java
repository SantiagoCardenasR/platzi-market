package com.Platzi.market.persistence;

import com.Platzi.market.domain.Product;
import com.Platzi.market.domain.repository.ProductRepo;
import com.Platzi.market.persistence.crud.ProductoCrudRepo;
import com.Platzi.market.persistence.entity.Producto;
import com.Platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepo implements ProductRepo {
    @Autowired
    private ProductoCrudRepo productoCrudRepo;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepo.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepo.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepo.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return  productoCrudRepo.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save (Product product) {
        Producto producto = mapper.toProducto(product);
        if(producto != null) {
            return mapper.toProduct(productoCrudRepo.save(producto));
        } else {
            return null;
        }
    }

    @Override
    public void delete (int idProducto) {
        productoCrudRepo.deleteById(idProducto);
    }
}
