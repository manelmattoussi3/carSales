package com.devesling.carsales.controller;

import com.devesling.carsales.entities.Catalog;
import com.devesling.carsales.iservice.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatalogController {
    @Autowired
    ICatalogService catalogService;
   @PostMapping("/addCatalog")
    public ResponseEntity<String> addCatalog(@RequestBody Catalog catalog) {
        return catalogService.addCatalog(catalog);
    }
}
