package com.petcc_enfermagem.medicamentos_api.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Medicamento {
    
    @Id
    private String id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String variation;
    
    @Positive
    private Double volumeMl;
    
    @Positive
    private Double amountMg;
    
    @Positive
    private Double mgPerKgDefault;
    
    private String description;
    
    @ElementCollection
    private List<String> indications;
    
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public Double getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(Double volumeMl) {
        this.volumeMl = volumeMl;
    }

    public Double getAmountMg() {
        return amountMg;
    }

    public void setAmountMg(Double amountMg) {
        this.amountMg = amountMg;
    }

    public Double getMgPerKgDefault() {
        return mgPerKgDefault;
    }

    public void setMgPerKgDefault(Double mgPerKgDefault) {
        this.mgPerKgDefault = mgPerKgDefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIndications() {
        return indications;
    }

    public void setIndications(List<String> indications) {
        this.indications = indications;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}