package com.devesling.carsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableAspectJAutoProxy
@SpringBootApplication
public class CarSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSalesApplication.class, args);
    }

}
