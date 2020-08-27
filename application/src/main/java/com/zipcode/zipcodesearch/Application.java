package com.zipcode.zipcodesearch;

import com.zipcode.zipcodesearch.dataprovider.model.AddressEntity;
import com.zipcode.zipcodesearch.dataprovider.repository.AddressRepository;
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
            addressRepository.save(AddressEntity.builder().zipCode("22230060").build());
        };
    }
}

