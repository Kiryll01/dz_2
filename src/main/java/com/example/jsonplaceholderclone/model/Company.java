package com.example.jsonplaceholderclone.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Embeddable
public class Company {
    @NotBlank(message = "Company name is required")
    @Column(name = "company_name")
    private String name;
    
    @NotBlank(message = "Catch phrase is required")
    private String catchPhrase;
    
    @NotBlank(message = "Business statement is required")
    private String bs;
} 