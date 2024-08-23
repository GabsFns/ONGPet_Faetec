package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
