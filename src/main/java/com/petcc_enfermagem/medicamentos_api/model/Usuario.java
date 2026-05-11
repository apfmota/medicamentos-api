package com.petcc_enfermagem.medicamentos_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {
    
    @Id
    private String username;
    
    @NotBlank
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private Boolean ativo = true;

    public Usuario() {
    }

    public Usuario(String username, String senha, Role role) {
        this.username = username;
        this.senha = senha;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public enum Role {
        ADMIN, USER
    }
}
