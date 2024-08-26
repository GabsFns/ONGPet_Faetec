package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.ConsultaModel;
import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.repository.AnimalRepository;
import com.example.OngVeterinaria.repository.ClienteRepository;
import com.example.OngVeterinaria.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultaServices {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalRepository animalRepository;


    //METODO AGENDAR CONSULTA
    public ConsultaModel agendarConsulta(Long idCliente, Long idAnimal, LocalDate dataComeco, LocalDate dataFinal, String descricao) {
        // Verifica se o cliente existe
        ClienteModel cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Verifica se o animal existe e pertence ao cliente
        AnimalModel animal = animalRepository.findById(idAnimal)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado ou não pertence ao cliente"));

        if (animal.getCliente().getIdCliente() != idCliente) {
            throw new RuntimeException("O animal não pertence ao cliente especificado");
        }

        // Cria uma nova consulta
        ConsultaModel consulta = new ConsultaModel();
        consulta.setCliente(cliente);
        consulta.setAnimal(animal);
        consulta.setData_comeco(dataComeco);
        consulta.setData_final(dataFinal);
        consulta.setDescricao(descricao);
        consulta.setStatus_consulta(StatusGeral.PENDENTE); // Define um status inicial, por exemplo, "Pendente"

        // Salva a consulta no banco de dados
        return consultaRepository.save(consulta);
    }

    //METODO PARA LISTAR TODAS AS CONSULTAS
    public List<ConsultaModel> listarTodasConsultas() {
        return consultaRepository.findAll();
    }


    // Método para buscar consultas pelo nome do usuário, id e data.
    public List<ConsultaModel> buscarConsultasPorFiltros(String nome, Long idCliente, LocalDate dataConsulta) {
        return consultaRepository.findByFilters(nome, idCliente, dataConsulta);
    }


    //METODO PARA O FUNCIONARIO/ADM ATUALIZAR O STATUS DA CONSULTA
    public ConsultaModel atualizarStatusConsulta(Long id, StatusGeral novoStatus) {
        ConsultaModel consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));
        consulta.setStatus_consulta(novoStatus);
        return consultaRepository.save(consulta);
    }
}
