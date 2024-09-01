package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AdministradorModel;
import com.example.OngVeterinaria.model.FuncionarioModel;
import com.example.OngVeterinaria.repository.AdministradorRepository;
import com.example.OngVeterinaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorServices {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    private final BCryptPasswordEncoder Criptografar = new BCryptPasswordEncoder();

    public AdministradorModel cadastrarAdministrador(AdministradorModel administrador) {
        // Criptografa a senha antes de salvar
        administrador.setPasswordAdm(Criptografar.encode(administrador.getPasswordAdm()));
        // Salva o administrador no banco de dados
        return administradorRepository.save(administrador);
    }

    public FuncionarioModel cadastrarFuncionario (FuncionarioModel funcionarioModel){
        funcionarioModel.setPassword_funcionario(passwordEncoder.encode(funcionarioModel.getPassword_funcionario()));
        return funcionarioRepository.save(funcionarioModel);
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
