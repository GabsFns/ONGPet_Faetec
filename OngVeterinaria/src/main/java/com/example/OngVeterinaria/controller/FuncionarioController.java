package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.*;
import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.model.Enum.TipoDenucias;
import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import com.example.OngVeterinaria.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    @Autowired
    private ClienteServices clienteServices;

    @Autowired
    private ConsultaServices consultaServices;

    @Autowired
    private AnimalServices animalServices;

    @Autowired
    private AdocaoServices adocaoServices;


    //Exibir Lista de consultas



    //exibir todos os agendamentos
    @GetMapping("/ExibirConsultas")
    public List<ConsultaModel> listarTodasConsultas() {
        return consultaServices.listarTodasConsultas();
    }

    //Exibir Todas as Denunciais
    @GetMapping("/ExebirDenuncias")
    public List<DenunciaModel> listarTodasDenuncias(){
        return clienteServices.ListarTodasDenuncias();
    }


    //Busca Consultas no desktop por nome, id ou data - FILTRO GET CONSULTA
    @GetMapping("/Consultas/buscar")
    public List<ConsultaModel> buscarConsultas(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "idCliente", required = false) Long idCliente,
            @RequestParam(value = "dataComeco", required = false) LocalDate dataComeco) {
        return consultaServices.buscarConsultasPorFiltros(nome, idCliente, dataComeco);
    }

    //Pesquisar Usuario na tabela Desktop - FILTRO GET CLIENTE
    @GetMapping("/ClienteBuscar/{id}")
    public ResponseEntity<ClienteModel> buscarClientePorId(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteServices.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    // Endpoint para atualizar o status da consulta - PUT
    @PutMapping("/{id}/statusConsulta")
    public ResponseEntity<ConsultaModel> atualizarStatusConsulta(
            @PathVariable Long id,
            @RequestBody StatusGeral novoStatus) {
        ConsultaModel consultaAtualizada = consultaServices.atualizarStatusConsulta(id, novoStatus);
        return ResponseEntity.ok(consultaAtualizada);
    }

    //Cadastrar Animal Adocao
    @PostMapping("/cadastrar/animal")
    public ResponseEntity<AnimalModel> cadastrarAnimal(
            @RequestParam Long idCliente,
            @RequestParam String nome,
            @RequestParam TipoEspecie especie,
            @RequestParam String idade,
            @RequestParam String cor,
            @RequestParam double peso,
            @RequestParam(required = false) byte[] foto,
            @RequestParam(required = false) String descricao) // Parêntese corrigido aqui
    {
        // Instanciando o método cadastrarAnimal
        AnimalModel animal = animalServices.cadastrarAnimal(idCliente, nome, especie, idade, cor, peso, foto, descricao);
        return ResponseEntity.ok(animal);
    }

    // Atualizar um animal existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AnimalModel> atualizarAnimal(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) TipoEspecie especie,
            @RequestParam(required = false) String idade,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) double peso,
            @RequestParam(required = false) byte[] foto,
            @RequestParam(required = false) String descricao)
    {
        Optional<AnimalModel> atualizado = animalServices.atualizarAnimal(id, nome, especie, idade, cor, peso, foto, descricao);
        return atualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar um animal pelo ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        Optional<AnimalModel> deletado = animalServices.deletarAnimal(id);
        return deletado.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/Denuncia/{idCliente}")
    public List<DenunciaModel> listarPorCliente(@PathVariable Long idCliente) {
        return clienteServices.buscarPorCliente(idCliente);
    }

    @GetMapping("/Denuncia/{dataDenuncia}")
    public List<DenunciaModel> listarPorData(@PathVariable LocalDate dataDenuncia) {
        return clienteServices.buscarPorData(dataDenuncia);
    }

    @GetMapping("/Denuncia/{tipoDenucias}")
    public List<DenunciaModel> listarPorTipo(@PathVariable TipoDenucias tipoDenucias) {
        return clienteServices.buscarPorTipo(tipoDenucias);
    }

    @GetMapping("/Denunciafiltro")
    public List<DenunciaModel> listarPorClienteDataETipo(
            @RequestParam Long idCliente,
            @RequestParam LocalDate dataDenuncia,
            @RequestParam TipoDenucias tipoDenucias) {
        return clienteServices.buscarPorClienteDataETipo(idCliente, dataDenuncia, tipoDenucias);
    }

    @PostMapping("/AdocaoStatus/{id}")
    public ResponseEntity<String> atualizarStatus(@PathVariable Long id, @RequestParam StatusGeral novoStatus) {
        adocaoServices.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok("Status da adoção atualizado com sucesso!");
    }
}
