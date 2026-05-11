package com.petcc_enfermagem.medicamentos_api.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcc_enfermagem.medicamentos_api.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {
    
    List<Medicamento> findByNameContainingIgnoreCase(String name);
}