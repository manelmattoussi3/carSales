package com.devesling.carsales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCar;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "registration_date")
    @Min(value = 2016, message = "Year must be 2016 or later")
    private int registrationDate;
    @Column(name = "price")
    private Double price;
    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private fuelType fuelType;
    @Column(name = "mileage")
    private String mileage;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private transmission transmission;
    @Column(name = "picture")
    private String picture;
    @ManyToOne
    private Catalog catalog;

}
