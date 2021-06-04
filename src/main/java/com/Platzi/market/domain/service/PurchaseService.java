package com.Platzi.market.domain.service;

import com.Platzi.market.domain.Purchase;
import com.Platzi.market.domain.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepo purchaseRepo;

    public List<Purchase> getAll() {return purchaseRepo.getAll();}
    public Optional<List<Purchase>> getByClient(String clientId) {return purchaseRepo.getByClient(clientId);}
    public Purchase save(Purchase purchase) {return purchaseRepo.save(purchase);}
}
