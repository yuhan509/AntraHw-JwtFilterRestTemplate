package com.example.jwtfilterdemo.controllers;


import com.example.jwtfilterdemo.dtos.AuthRequestDto;
import com.example.jwtfilterdemo.dtos.JwtResponseDto;
import com.example.jwtfilterdemo.dtos.UniversityDto;
import com.example.jwtfilterdemo.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import com.example.jwtfilterdemo.services.SearchByCountryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DemoRestController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final SearchByCountryService searchByCountryService;

    @Autowired
    public DemoRestController(JwtService jwtService, AuthenticationManager authenticationManager, SearchByCountryService searchByCountryService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.searchByCountryService = searchByCountryService;
    }

    @GetMapping()
    public ResponseEntity<UniversityDto[]> getAll() {
        UniversityDto[] dtos = searchByCountryService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/search", params =  "country")
    public ResponseEntity<UniversityDto[]> getByCountry(@RequestParam("country") String country) {
        UniversityDto[] dtos = searchByCountryService.findByCountry(country);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/search", params = "countries")
    public ResponseEntity<List<UniversityDto[]>> getByCountries(@RequestParam("countries") List<String> countries) {
        List<UniversityDto[]> responseList = searchByCountryService.findByCountries(countries);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/search/async")
    public ResponseEntity<List<UniversityDto[]>> getByCountriesAsync(@RequestParam("countries") List<String> countries) {
        List<UniversityDto[]> responseList = searchByCountryService.findByCountriesAsync(countries);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> authenticateGetJwt(@RequestBody AuthRequestDto authRequestDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            JwtResponseDto response = JwtResponseDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDto.getUsername()))
                            .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request!!!");
        }
    }



}
