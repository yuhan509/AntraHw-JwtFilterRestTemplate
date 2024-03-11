package com.example.jwtfilterdemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"alpha_two_code", "state-province", "web_pages", "name", "domains", "country"})
public class UniversityDto {
    public String alpha_two_code;
    @JsonProperty("state-province")
    public String stateProvince;
    public String[] web_pages;
    public String name;
    public String[] domains;
    public String country;
}