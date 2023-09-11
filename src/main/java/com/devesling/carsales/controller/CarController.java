package com.devesling.carsales.controller;

import com.devesling.carsales.entities.*;
import com.devesling.carsales.iservice.ICarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequestMapping("/api/cars")
@Api(tags = "Car API", description = "API for managing cars")
@RestController
public class CarController {
    @Autowired
    ICarService carService;
  @PostMapping("/addCarToGarageCatalog/idCatalog/{idCatalog}")
  @ApiOperation(value = "Add a car to the catalog")
    public ResponseEntity<String> addCarToGarageCatalog(@RequestBody Car car,@PathVariable("idCatalog") long idCatalog){
        return carService.addCarToGarageCatalog(car,idCatalog);
    }
   @GetMapping("/filter/fuelType/{fuelType}/maxPrice/{maxPrice}")
   @ApiOperation(value = "filter cars", response = Car.class, responseContainer = "List")
    public ResponseEntity<List<Car>> filterCars(@PathVariable(name = "fuelType") fuelType fuelType, @PathVariable(name = "maxPrice")  Double maxPrice) {
      return carService.findByFuelTypeAndPriceLessThanEqual(fuelType,maxPrice);
    }
    @GetMapping("/makes/idCatalog/{idCatalog}")
    @ApiOperation(value = "Find all car makes in the catalog")
    public  ResponseEntity<List<String>> findAllCarMakes(@PathVariable("idCatalog") Long catalogId){
       List<String> carMakes = carService.findAllCarMakes(catalogId);
       return new ResponseEntity<>(carMakes, HttpStatus.OK);
   }
    @PostMapping("carId/{carId}/upload-picture")
    @ApiOperation(value = "Upload a car picture by car ID")
    public ResponseEntity<String> uploadCarPicture(
            @PathVariable("carId") Long carId,
            @RequestParam("file") MultipartFile file) {
      return carService.uploadCarPicture(carId,file);
    }
}

