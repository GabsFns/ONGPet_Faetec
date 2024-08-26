package com.example.OngVeterinaria.repository;


import com.example.OngVeterinaria.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    // Buscar Email
    ClienteModel findByEmail(String email);
    // Buscar por nome
    List<ClienteModel> findByNome(String nome);
    // Buscar Por id
    ClienteModel findByIdCliente(long idCliente);
}
