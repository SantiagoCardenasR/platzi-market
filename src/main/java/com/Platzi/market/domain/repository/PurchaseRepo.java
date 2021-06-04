package com.Platzi.market.domain.repository;

import com.Platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepo {
    List<Purchase> getAll();
    Optional<List<Purchase>>  getByClient(String clientId);
    Purchase save(Purchase purchase);
}
