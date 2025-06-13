package com.example.jsonplaceholderclone.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @NotBlank(message = "Street is required")
    private String street;
    
    @NotBlank(message = "Suite is required")
    private String suite;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid zipcode format")
    private String zipcode;
    
    @Valid
    @Embedded
    private Geo geo;
} 