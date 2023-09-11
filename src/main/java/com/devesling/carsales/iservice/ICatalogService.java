package com.devesling.carsales.iservice;

import com.devesling.carsales.entities.Car;
import com.devesling.carsales.entities.Catalog;
import org.springframework.http.ResponseEntity;

public interface ICatalogService {
    public ResponseEntity<String> addCatalog(Catalog catalog);
}
