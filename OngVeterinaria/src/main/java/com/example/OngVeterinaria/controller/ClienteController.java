package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.services.ClienteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteModel> cadastrarCliente(@Valid @RequestBody ClienteModel cliente) {
        ClienteModel novoCliente = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteModel clienteAtualizado) {
        ClienteModel clienteAtualizadoResposta = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(clienteAtualizadoResposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscarClientePorId(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
