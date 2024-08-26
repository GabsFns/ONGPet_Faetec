package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.ConsultaModel;
import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.model.FuncionarioModel;
import com.example.OngVeterinaria.services.ConsultaServices;
import com.example.OngVeterinaria.services.FuncionarioServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    private ConsultaServices consultaServices;

    @GetMapping("/listar")
    public ResponseEntity<List<ConsultaModel>> listarConsultas() {
        List<ConsultaModel> consultas = consultaServices.listarTodasConsultas();
        return ResponseEntity.ok(consultas);
    }
    // Endpoint para buscar consulta pelo ID
    @GetMapping("/cliente/{id}")
    public ResponseEntity<ConsultaModel> buscarConsultaPorId(@PathVariable Long id) {
        ConsultaModel consulta = consultaServices.buscarConsultaPorId(id);
        return ResponseEntity.ok(consulta);
    }

    // Endpoint para buscar consulta pelo nome do usuário


    // Endpoint para atualizar o status da consulta
    @PutMapping("/{id}/status")
    public ResponseEntity<ConsultaModel> atualizarStatusConsulta(
            @PathVariable Long id,
            @RequestBody StatusGeral novoStatus) {
        ConsultaModel consultaAtualizada = consultaServices.atualizarStatusConsulta(id, novoStatus);
        return ResponseEntity.ok(consultaAtualizada);
    }
}
