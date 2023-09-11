package com.devesling.carsales.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Catalog implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCatalog;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "catalog",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    @JsonIgnore
    private List<Car> cars;
}
