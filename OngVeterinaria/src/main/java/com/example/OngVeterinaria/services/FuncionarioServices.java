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
public class FuncionarioServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioModel cadastrarFuncionario (FuncionarioModel funcionarioModel){
        funcionarioModel.setPassword_funcionario(passwordEncoder.encode(funcionarioModel.getPassword_funcionario()));
        return funcionarioRepository.save(funcionarioModel);
    }
}