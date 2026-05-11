package com.petcc_enfermagem.medicamentos_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petcc_enfermagem.medicamentos_api.model.Medicamento;
import com.petcc_enfermagem.medicamentos_api.service.MedicamentoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin("*")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository repository;

    @GetMapping
    public List<Medicamento> listar(@RequestParam(required = false) String name) {
        if (name != null) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscar(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Medicamento criar(@RequestBody @Valid Medicamento medicamento) {
        return repository.save(medicamento);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Medicamento> atualizar(@PathVariable String id, 
            @RequestBody @Valid Medicamento medicamento) {
        return repository.findById(id)
                .map(existing -> {
                    medicamento.setId(id);
                    return ResponseEntity.ok(repository.save(medicamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}