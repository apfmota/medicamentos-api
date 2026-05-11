package com.petcc_enfermagem.medicamentos_api.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcc_enfermagem.medicamentos_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByUsername(String username);
}
