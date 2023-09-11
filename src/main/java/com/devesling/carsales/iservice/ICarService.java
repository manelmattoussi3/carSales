package com.devesling.carsales.iservice;

import com.devesling.carsales.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICarService {
    public ResponseEntity<String> addCarToGarageCatalog(Car car, long idCatalog);
    public ResponseEntity<List<Car>> findByFuelTypeAndPriceLessThanEqual(fuelType fuelType, Double maxPrice);
    public List<String> findAllCarMakes(Long catalogId);
    public ResponseEntity<String> uploadCarPicture(Long carId, MultipartFile file);
}