package com.petcc_enfermagem.medicamentos_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcc_enfermagem.medicamentos_api.model.Apresentacao;

public interface ApresentacaoRepository extends JpaRepository<Apresentacao, UUID> {

    List<Apresentacao> findByMedicamentoId(UUID medicamentoId);

    
}