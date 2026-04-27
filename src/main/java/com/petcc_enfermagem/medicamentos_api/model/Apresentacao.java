package com.petcc_enfermagem.medicamentos_api.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Apresentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;
    @Positive
    private Double doseDisponivel;
    @NotBlank
    private String unidade; // mg, g, UI
    @Positive
    private Double volumeFinal;
    private Double volumeDiluente;
    private String observacoes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Double getDoseDisponivel() {
        return doseDisponivel;
    }

    public void setDoseDisponivel(Double doseDisponivel) {
        this.doseDisponivel = doseDisponivel;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getVolumeFinal() {
        return volumeFinal;
    }

    public void setVolumeFinal(Double volumeFinal) {
        this.volumeFinal = volumeFinal;
    }

    public Double getVolumeDiluente() {
        return volumeDiluente;
    }

    public void setVolumeDiluente(Double volumeDiluente) {
        this.volumeDiluente = volumeDiluente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}