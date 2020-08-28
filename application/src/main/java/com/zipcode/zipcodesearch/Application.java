package com.zipcode.zipcodesearch;

import com.zipcode.zipcodesearch.address.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.address.dataprovider.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner populateAddress(AddressRepository addressRepository) {
        return args -> {
            addressRepository.save(AddressEntity.builder()
                    .state("Rio de Janeiro")
                    .city("Rio de Janeiro")
                    .district("Flamengo")
                    .street("Rua Marques de Abrantes")
                    .zipCode("22230060")
                    .build());
        };
    }
}

