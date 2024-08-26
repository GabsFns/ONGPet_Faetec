package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import com.example.OngVeterinaria.repository.AnimalRepository;
import com.example.OngVeterinaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnimalServices {

        @Autowired
        private AnimalRepository animalRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        public AnimalModel cadastrarAnimal(Long idCliente, String nome, TipoEspecie especie, LocalDate dataNascimento, String cor, double peso, byte[] foto) {
            // Verifica se o cliente existe
            ClienteModel cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // Cria uma nova instância de Animal
            AnimalModel animal = new AnimalModel();
            animal.setNome(nome);
            animal.setEspecie(especie);
            animal.setDataNascimento(dataNascimento);
            animal.setCor(cor);
            animal.setPeso(peso);
            animal.setCliente(cliente); // Associa o animal ao cliente
            if (foto != null && foto.length > 0) {
                animal.setFotoAnimal(foto);
            }

            // Salva o animal no banco de dados
            return animalRepository.save(animal);
        }
    }

