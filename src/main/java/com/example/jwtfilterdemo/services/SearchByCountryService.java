package com.example.jwtfilterdemo.services;

import com.example.jwtfilterdemo.dtos.UniversityDto;

import java.util.List;

public interface SearchByCountryService {

    UniversityDto[] findAll();
    UniversityDto[] findByCountry(String country);
    List<UniversityDto[]> findByCountries(List<String> countries);
    List<UniversityDto[]> findByCountriesAsync(List<String> countries);

}
