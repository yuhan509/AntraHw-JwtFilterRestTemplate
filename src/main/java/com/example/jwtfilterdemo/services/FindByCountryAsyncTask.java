package com.example.jwtfilterdemo.services;


import com.example.jwtfilterdemo.dtos.UniversityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Component
public class FindByCountryAsyncTask {

    private UniversityDto[] findByCountry(String country, String resourceUrl, RestTemplate restTemplate) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("country",country).build();
        return restTemplate.getForObject(uriComponents.toUriString(), UniversityDto[].class);
    }
    @Async
    public CompletableFuture<UniversityDto[]> findByCountryAsync(String country, String resourceUrl, RestTemplate restTemplate) {
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException ex) {
//            System.out.println("INTERRUPTED!!!");
//        }
        return CompletableFuture.completedFuture(findByCountry(country, resourceUrl, restTemplate));
    }
}
