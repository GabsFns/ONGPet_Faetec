package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import com.example.OngVeterinaria.repository.AnimalRepository;
import com.example.OngVeterinaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServices {

        @Autowired
        private AnimalRepository animalRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        public AnimalModel cadastrarAnimal(Long idAnimal, String nome, TipoEspecie especie, String idade, String cor, double peso, byte[] foto, String descricao) {
            // Verifica se o cliente existe
            ClienteModel cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // Cria uma nova instância de Animal
            AnimalModel animal = new AnimalModel(idAnimal);
            animal.setNome(nome);
            animal.setEspecie(especie);
            animal.setIdade(idade);
            animal.setCor(cor);
            animal.setPeso(peso);
            animal.setCliente(cliente); // Associa o animal ao cliente
            if (foto != null && foto.length > 0) {
                animal.setFotoAnimal(foto);
            }
            if (descricao != null) {
                animal.setDescricao(descricao);
            }
            // Salva o animal no banco de dados
            return animalRepository.save(animal);
        }

    // Método para deletar um animal pelo ID
    public Optional<AnimalModel> deletarAnimal(Long idAnimal) {
        // Busca o animal pelo ID
        Optional<AnimalModel> animal = animalRepository.findById(idAnimal);
        if (animal.isPresent()) {
            // Se o animal existe, deleta-o
            animalRepository.deleteById(idAnimal);
        }
        return animal; // Retorna o animal deletado, ou Optional.empty() se não foi encontrado
    }


    // Método para atualizar os dados de um animal existente
    public Optional<AnimalModel> atualizarAnimal(Long idAnimal, String nome, TipoEspecie especie, String idade, String cor, double peso, byte[] foto, String descricao) {
        // Busca o animal existente pelo ID
        Optional<AnimalModel> optionalAnimal = animalRepository.findById(idAnimal);

        if (optionalAnimal.isPresent()) {
            AnimalModel animal = optionalAnimal.get();

            // Atualiza os campos do animal, se os novos valores forem fornecidos
            if (nome != null && !nome.isEmpty()) {
                animal.setNome(nome);
            }
            if (especie != null) {
                animal.setEspecie(especie);
            }
            if (idade != null) {
                animal.setIdade(idade);
            }
            if (cor != null && !cor.isEmpty()) {
                animal.setCor(cor);
            }
            if (peso > 0) {
                animal.setPeso(peso);
            }
            if (foto != null && foto.length > 0) {
                animal.setFotoAnimal(foto);
            }
            if (descricao != null){
                animal.setDescricao(descricao);
            }

            // Salva as mudanças no banco de dados
            animalRepository.save(animal);
            return Optional.of(animal);
        }

        // Retorna Optional.empty() se o animal não for encontrado
        return Optional.empty();
    }
    public List<AnimalModel> buscarAnimaisPorFiltros(TipoEspecie especie, String raca) {
        if (especie != null && !isValidRacaForEspecie(especie, raca)) {
            throw new IllegalArgumentException("Raça inválida para a espécie fornecida.");
        }
        return animalRepository.findByFilters(especie, raca);
    }

    private boolean isValidRacaForEspecie(TipoEspecie especie, String raca) {
        if (raca == null || raca.isEmpty()) {
            return true;
        }
        Class<? extends Enum<?>> racaEnumClass = especie.getRacaEnum();
        try {
            Enum.valueOf((Class<Enum>) racaEnumClass, raca);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
//        Enum.valueOf((Class<Enum>) racaEnumClass, raca): Tenta converter a string raca para um valor da enumeração de raça correspondente.
//        Se a conversão for bem-sucedida, a raça é válida.

//        catch (IllegalArgumentException e): Se a conversão falhar (ou seja, a raça não existe na enumeração), o método retorna false,
//        indicando que a raça é inválida para a espécie fornecida.
   }

    }

