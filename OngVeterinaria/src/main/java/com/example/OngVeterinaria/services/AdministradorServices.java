package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AdministradorModel;
import com.example.OngVeterinaria.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorServices {

    @Autowired
    private AdministradorRepository administradorRepository;

    private final BCryptPasswordEncoder Criptografar = new BCryptPasswordEncoder();

    public AdministradorModel cadastrarAdministrador(AdministradorModel administrador) {
        // Criptografa a senha antes de salvar
        administrador.setPasswordAdm(Criptografar.encode(administrador.getPasswordAdm()));
        // Salva o administrador no banco de dados
        return administradorRepository.save(administrador);
    }


    public AdministradorModel login(String emailAdm, String passwordAdm) {
        Optional<AdministradorModel> administradorOptional = administradorRepository.findByEmailAdm(emailAdm);

        if (administradorOptional.isPresent()) {
            AdministradorModel administrador = administradorOptional.get();
            if (Criptografar.matches(passwordAdm, administrador.getPasswordAdm())) {
                return administrador;
            }
        }

        throw new RuntimeException("Credenciais inválidas");
    }
}
