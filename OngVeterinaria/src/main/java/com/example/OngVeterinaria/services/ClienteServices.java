package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel cadastrarCliente(ClienteModel cliente) {
        cliente.setData_Cadastro(LocalDate.now()); // Define a data de cadastro automaticamente
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteModel atualizarCliente(Long id, ClienteModel clienteAtualizado) {
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os campos necessários
        clienteExistente.setCpf_Cliente(clienteAtualizado.getCpf_Cliente());
        clienteExistente.setNome_Cliente(clienteAtualizado.getNome_Cliente());
        clienteExistente.setEmail_Cliente(clienteAtualizado.getEmail_Cliente());
        clienteExistente.setPassword_Cliente(clienteAtualizado.getPassword_Cliente());
        clienteExistente.setTelefone_Cliente(clienteAtualizado.getTelefone_Cliente());
        clienteExistente.setGeneroCliente(clienteAtualizado.getGeneroCliente());
        clienteExistente.setData_nascimento(clienteAtualizado.getData_nascimento());

        return clienteRepository.save(clienteExistente);
    }
}
