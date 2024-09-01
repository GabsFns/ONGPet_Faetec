package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.*;
import com.example.OngVeterinaria.services.AdministradorServices;
import com.example.OngVeterinaria.services.ClienteServices;
import com.example.OngVeterinaria.services.ConsultaServices;
import com.example.OngVeterinaria.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/adm")
public class AdministradorController {

    @Autowired
    private AdministradorServices administradorServices;

    @Autowired
    private ConsultaServices consultaServices;

    @Autowired
    private ClienteServices clienteServices;

    @Autowired
    private FuncionarioServices funcionarioServices;

    //Cadastra Admin
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAdministrador(@RequestBody AdministradorModel administrador) {
        AdministradorModel novoAdministrador = administradorServices.cadastrarAdministrador(administrador);
        if (novoAdministrador != null) {
            return ResponseEntity.ok("Administrador cadastrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar administrador.");
        }
    }
    //Login do Admin
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdministradorModel loginRequest) {
        try {
            AdministradorModel administrador = administradorServices.login(loginRequest.getEmailAdm(), loginRequest.getPasswordAdm());
            return ResponseEntity.ok("Login bem-sucedido");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas: " + e.getMessage());
        }
    }

    //Cadastrar Funcionario
    @PostMapping("/cadastrar/funcionario")
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody FuncionarioModel funcionarioModel){
        FuncionarioModel novoFuncionario = funcionarioServices.cadastrarFuncionario(funcionarioModel);
        if (novoFuncionario != null) {
           return ResponseEntity.ok("Funcionario cadastrado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar Funcionario");
        }
    }
    


    //exibir todos os agendamentos
    @GetMapping("/agendamento/todos")
    public List<ConsultaModel> listarTodasConsultas() {
        return consultaServices.listarTodasConsultas();
    }

    //Exibir Todas as Denunciais
    @GetMapping("/ExebirDenuncias")
    public List<DenunciaModel> listarTodasDenuncias(){
        return clienteServices.ListarTodasDenuncias();
    }

    //Busca agendamento no desktop por nome, id ou data
    @GetMapping("/agendamento/buscar")
    public List<ConsultaModel> buscarConsultas(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "idCliente", required = false) Long idCliente,
            @RequestParam(value = "dataComeco", required = false) LocalDate dataComeco) {
        return consultaServices.buscarConsultasPorFiltros(nome, idCliente, dataComeco);
    }



}
