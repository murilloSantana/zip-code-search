package com.zipcode.zipcodesearch.configuration;

import com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCase;
import com.zipcode.zipcodesearch.usecase.address.dataprovider.AddressUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public AddressUseCase addressFinder(AddressDataProvider addressDataProvider) {
        return new AddressUseCaseImpl(addressDataProvider);
    }
}
