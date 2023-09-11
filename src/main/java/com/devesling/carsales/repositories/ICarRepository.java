package com.devesling.carsales.repositories;

import com.devesling.carsales.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepository extends JpaRepository<Car,Long> {
 @Query("select c from Car c where c.fuelType=:fuelType and c.price<= :maxPrice")
    List<Car> findByFuelTypeAndPriceLessThanEqual(@Param("fuelType") fuelType fuelType,@Param("maxPrice") Double maxPrice);
    @Query("SELECT DISTINCT c.make FROM Car c where c.catalog.idCatalog = :catalogId")
    List<String> findAllCarMakes(@Param("catalogId") Long catalogId);
}
