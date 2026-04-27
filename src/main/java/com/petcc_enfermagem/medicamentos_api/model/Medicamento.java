package com.petcc_enfermagem.medicamentos_api.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Medicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String nome;
    private String descricao;
    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
    private List<Apresentacao> apresentacoes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Apresentacao> getApresentacoes() {
        return apresentacoes;
    }

    public void setApresentacoes(List<Apresentacao> apresentacoes) {
        this.apresentacoes = apresentacoes;
    }

}