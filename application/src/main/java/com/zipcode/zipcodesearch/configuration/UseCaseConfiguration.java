package com.zipcode.zipcodesearch.configuration;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressUseCase;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public AddressUseCase addressFinder(AddressDataProvider addressDataProvider) {
        return new AddressUseCaseImpl(addressDataProvider);
    }
}
