package com.devesling.carsales.repositories;

import com.devesling.carsales.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatalogRepository extends JpaRepository<Catalog,Long> {
}
