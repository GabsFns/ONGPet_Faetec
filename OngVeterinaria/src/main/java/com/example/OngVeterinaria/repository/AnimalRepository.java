package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {
}
