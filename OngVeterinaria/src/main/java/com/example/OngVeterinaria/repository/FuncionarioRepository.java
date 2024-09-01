package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
}
