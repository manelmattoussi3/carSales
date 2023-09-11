package com.devesling.carsales.service;

import com.devesling.carsales.entities.Catalog;
import com.devesling.carsales.iservice.ICatalogService;
import com.devesling.carsales.repositories.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CatalogService implements ICatalogService {
    @Autowired
    ICatalogRepository catalogRepository;
    @Override
    public ResponseEntity<String> addCatalog(Catalog catalog) {
        catalogRepository.save(catalog);
    return ResponseEntity.ok("the catalog is successufully added ");
    }
}
