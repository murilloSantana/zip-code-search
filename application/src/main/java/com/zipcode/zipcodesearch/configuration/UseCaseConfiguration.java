package com.zipcode.zipcodesearch.configuration;

import com.zipcode.zipcodesearch.adapter.AddressDataProvider;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressFinder;
import com.zipcode.zipcodesearch.usecase.address.finder.AddressFinderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public AddressFinder addressFinder(AddressDataProvider addressDataProvider) {
        return new AddressFinderImpl(addressDataProvider);
    }
}
