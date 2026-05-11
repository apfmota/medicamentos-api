package com.petcc_enfermagem.medicamentos_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petcc_enfermagem.medicamentos_api.dto.LoginRequest;
import com.petcc_enfermagem.medicamentos_api.dto.LoginResponse;
import com.petcc_enfermagem.medicamentos_api.model.Usuario;
import com.petcc_enfermagem.medicamentos_api.model.Usuario.Role;
import com.petcc_enfermagem.medicamentos_api.security.JwtProvider;
import com.petcc_enfermagem.medicamentos_api.service.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername());
        
        if (usuario == null || !usuario.getAtivo()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }
        
        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }
        
        String token = jwtProvider.generateToken(usuario);
        return ResponseEntity.ok(new LoginResponse(token, usuario.getRole().toString()));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody @Valid LoginRequest request) {
        if (usuarioRepository.existsById(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário já existe");
        }
        
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setRole(Role.USER); // New users are USER role
        
        usuarioRepository.save(usuario);
        
        String token = jwtProvider.generateToken(usuario);
        return ResponseEntity.ok(new LoginResponse(token, usuario.getRole().toString()));
    }
}
