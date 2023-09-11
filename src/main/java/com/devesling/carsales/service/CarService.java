package com.devesling.carsales.service;

import com.devesling.carsales.entities.*;
import com.devesling.carsales.entities.Catalog;
import com.devesling.carsales.iservice.ICarService;
import com.devesling.carsales.repositories.ICarRepository;
import com.devesling.carsales.repositories.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    ICarRepository carRepository;
    @Autowired
    ICatalogRepository catalogRepository;
    @Override
    public ResponseEntity<String> addCarToGarageCatalog(Car car, long idCatalog) {
        if (car.getRegistrationDate() > 2016) {
        Catalog catalog =catalogRepository.findById(idCatalog).get();
        car.setCatalog(catalog);
        carRepository.save(car);
        return ResponseEntity.ok("the car is successufully added and assigned to catalog ");
    }
            return ResponseEntity.badRequest().body("Year must be 2016 or later");
    }

    @Override
    public ResponseEntity<List<Car>> findByFuelTypeAndPriceLessThanEqual(fuelType fuelType, Double maxPrice) {
        if (fuelType == null && maxPrice == null) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        List<Car> filteredCars;

        if (fuelType != null && maxPrice != null) {
            filteredCars = carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
        }  else {
            filteredCars = new ArrayList<>();
        }

        return new ResponseEntity<>(filteredCars, HttpStatus.OK);
    }

    @Override
    public List<String> findAllCarMakes(Long catalogId) {
        return carRepository.findAllCarMakes(catalogId);
    }

    @Override
    public ResponseEntity<String> uploadCarPicture(Long carId, MultipartFile file) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (!optionalCar.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Car car = optionalCar.get();
        try {
            // Replace this with your actual logic to save the file
            String pictureUrl = savePicture(file);
            car.setPicture(pictureUrl);
            carRepository.save(car);

            return ResponseEntity.ok("Car picture updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading picture.");
        }
    }
    private String savePicture(MultipartFile file) throws IOException {
        String uploadDir = "./src/main/resources";
        Path uploadPath = Path.of(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path filePath = uploadPath.resolve(uniqueFileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String pictureUrl = "/uploads/" + uniqueFileName;
            return pictureUrl;
        } catch (IOException e) {
            throw new IOException("Failed to save picture: " + e.getMessage());
        }
        }
    }





