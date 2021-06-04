package com.Platzi.market.persistence;

import com.Platzi.market.domain.Purchase;
import com.Platzi.market.domain.repository.PurchaseRepo;
import com.Platzi.market.persistence.crud.CompraCrudRepo;
import com.Platzi.market.persistence.entity.Compra;
import com.Platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepo implements PurchaseRepo {

    @Autowired
    private CompraCrudRepo compraCrudRepo;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepo.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepo.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepo.save(compra));
    }
}
