package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.AdministradorModel;
import com.example.OngVeterinaria.services.AdministradorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/adm")
public class AdministradorController {

    @Autowired
    private AdministradorServices administradorServices;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAdministrador(@RequestBody AdministradorModel administrador) {
        AdministradorModel novoAdministrador = administradorServices.cadastrarAdministrador(administrador);
        if (novoAdministrador != null) {
            return ResponseEntity.ok("Administrador cadastrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar administrador.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdministradorModel loginRequest) {
        try {
            AdministradorModel administrador = administradorServices.login(loginRequest.getEmailAdm(), loginRequest.getPasswordAdm());
            return ResponseEntity.ok("Login bem-sucedido");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas: " + e.getMessage());
        }
    }
}
