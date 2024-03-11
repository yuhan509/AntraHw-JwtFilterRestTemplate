package com.example.jwtfilterdemo.services;

import com.example.jwtfilterdemo.dtos.UniversityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SearchByCountryServiceImpl implements SearchByCountryService {

    private final RestTemplate restTemplate;
    @Value("${university_hipos_url}")
    private String resourceUrl;

    private FindByCountryAsyncTask asyncTask;

    @Autowired
    public SearchByCountryServiceImpl(RestTemplateBuilder builder, FindByCountryAsyncTask asyncTask) {
        this.asyncTask = asyncTask;
        this.restTemplate = builder.build();
    }

    @Override
    public UniversityDto[] findAll() {
        return restTemplate.getForObject(resourceUrl, UniversityDto[].class);
    }

    @Override
    public UniversityDto[] findByCountry(String country) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("country",country).build();
        return restTemplate.getForObject(uriComponents.toUriString(), UniversityDto[].class);
    }

    @Override
    public List<UniversityDto[]> findByCountries(List<String> countries) {
        List<UniversityDto[]> responseList = new LinkedList<>();
        for (String country : countries) {
            responseList.add(findByCountry(country));
        }
        return responseList;
    }

    @Override
    public List<UniversityDto[]> findByCountriesAsync(List<String> countries) {
        List<CompletableFuture<UniversityDto[]>> futuresList = new LinkedList<>();
        for (String country : countries) {
            futuresList.add(asyncTask.findByCountryAsync(country, resourceUrl, restTemplate));
        }
        return futuresList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


}


