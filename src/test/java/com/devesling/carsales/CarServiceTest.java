package com.devesling.carsales;


import com.devesling.carsales.repositories.ICarRepository;
import com.devesling.carsales.repositories.ICatalogRepository;
import com.devesling.carsales.service.CarService;
import com.devesling.carsales.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.devesling.carsales.entities.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarServiceTest {
    @Mock
    private ICarRepository carRepository;
    @Mock
    private ICatalogRepository catalogRepository;

    @InjectMocks
    private CarService carService;
    @Autowired
    private MockMvc mockMvc;
    public CarServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddCarToGarageCatalog() {
        long catalogId = 1L; // Replace with the desired catalog ID
        Car carToAdd = new Car(1L, "Polo","almand",2017,45000.0,fuelType.HYBRID,"150 000 km",transmission.AUTOMATIC,null,null);

        Catalog catalog = new Catalog(1L,"first catalog",null);
        when(catalogRepository.findById(catalogId)).thenReturn(Optional.of(catalog));
        ResponseEntity<String> response = carService.addCarToGarageCatalog(carToAdd, catalogId);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Car added to catalog successfully");
    }
    @Test
    public void testFindByFuelTypeAndPriceLessThanEqual() {
        Double maxPrice = 60000.0;

        List<Car> expectedCars = Arrays.asList(
                new Car(1L, "Polo","almand",2017,45000.0,fuelType.HYBRID,"150 000 km",transmission.AUTOMATIC,null,null),
                new Car(2L, "Ford","test",2020,85000.0,fuelType.DIESEL,"180 000 km",transmission.MANUAL,null,null)

        );

        when(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType.HYBRID, maxPrice)).thenReturn(expectedCars);
        ResponseEntity<List<Car>> response = carService.findByFuelTypeAndPriceLessThanEqual(fuelType.HYBRID, maxPrice);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(expectedCars.size());
        assertThat(response.getBody()).containsAll(expectedCars);
    }
    @Test
    public void testFindAllCarMakes() {
        Long catalogId = 1L;
        List<String> expectedCarMakes = Arrays.asList("Peugeot", "Polo", "Ford","Range Rover");
        when(carRepository.findAllCarMakes(catalogId)).thenReturn(expectedCarMakes);
        List<String> carMakes = carService.findAllCarMakes(catalogId);
        assertThat(carMakes).isNotNull();
        assertThat(carMakes).containsAll(expectedCarMakes);
        assertThat(carMakes).hasSize(expectedCarMakes.size());

    }
    @Test
    public void testUploadCarPicture() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
            "file", "1.jpg", "image/jpeg", "1694382019016_1.jpg".getBytes()
    );
    mockMvc.perform(MockMvcRequestBuilders.multipart("/api/cars/carId/{carId}/upload-picture", 1L)
            .file(file))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Car picture updated successfully"));
}

}
