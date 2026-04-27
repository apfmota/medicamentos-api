package com.petcc_enfermagem.medicamentos_api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petcc_enfermagem.medicamentos_api.model.Apresentacao;
import com.petcc_enfermagem.medicamentos_api.service.ApresentacaoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/apresentacoes")
@CrossOrigin("*")
public class ApresentacaoController {

    @Autowired
    private ApresentacaoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Apresentacao criar(@RequestBody @Valid Apresentacao apresentacao) {
        return repository.save(apresentacao);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}