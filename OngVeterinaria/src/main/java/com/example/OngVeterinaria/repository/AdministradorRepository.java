package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<AdministradorModel, Long> {
    Optional<AdministradorModel> findByEmailAdm(String emailAdm);
}