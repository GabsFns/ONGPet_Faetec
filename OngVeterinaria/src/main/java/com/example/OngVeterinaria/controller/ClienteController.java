package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.AdministradorModel;
import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.ConsultaModel;
import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import com.example.OngVeterinaria.services.AnimalServices;
import com.example.OngVeterinaria.services.ClienteServices;
import com.example.OngVeterinaria.services.ConsultaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteService;

    @Autowired
    private AnimalServices animalServices;

    @Autowired
    private ConsultaServices consultaServices;

    //Cadastrar Usuario
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteModel clienteModel, BindingResult result) {
        //tratamento de erro
        if (result.hasErrors()) {
            List<String> erros = result.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(erros);
        }
        // Criar DataCadastra Automatica
        clienteModel.setData_Cadastro(LocalDate.now());
        try {
            //Salavando Novo usuario
            ClienteModel clienteSalvo = clienteService.cadastrarCliente(clienteModel);
            return ResponseEntity.ok(clienteSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    //Atualizar dados do usuario
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteModel clienteAtualizado) {
        ClienteModel clienteAtualizadoResposta = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(clienteAtualizadoResposta);
    }


    //Cadastrar Animal para agendamento e para adocao
    @PostMapping("/cadastrar/animal")
    public ResponseEntity<AnimalModel> cadastrarAnimal(
            @RequestParam Long idCliente,
            @RequestParam String nome,
            @RequestParam TipoEspecie especie,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
            @RequestParam String cor,
            @RequestParam double peso,
            @RequestParam(required = false) byte[] foto) {

        //instanciando o metodo cadastrar animal
        AnimalModel animal = animalServices.cadastrarAnimal(idCliente, nome, especie, dataNascimento, cor, peso, foto);
        return ResponseEntity.ok(animal);
    }

    //Realizar Agendamento
    @PostMapping("/agendar")
    public ResponseEntity<ConsultaModel> agendarConsulta(
            @RequestParam Long idCliente,
            @RequestParam Long idAnimal,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataComeco,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam String descricao) {

        //instaciando o metodo agendar consulta
        ConsultaModel consulta = consultaServices.agendarConsulta(idCliente, idAnimal, dataComeco, dataFinal, descricao);
        return ResponseEntity.ok(consulta);
    }

    //Sistema Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClienteModel loginRequest) {
        try {
            //se os dados baterem vai deixar o usuario logar, instaciando o metodo login
            ClienteModel clienteModel = clienteService.login(loginRequest.getEmail(), loginRequest.getPassword_Cliente());
            return ResponseEntity.ok("Login bem-sucedido");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas: " + e.getMessage());
        }
    }

    @PostMapping("/esqueci-senha")
    public ResponseEntity<?> gerarTokenRecuperacao(@RequestParam("email") String email) {
        try {
            //instaciando o metodd gerarToken passando o email do cliente
            clienteService.gerarTokenRecuperacao(email);
            return ResponseEntity.ok().body("Token enviado para o e-mail.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao enviar o token: " + e.getMessage());
        }
    }

    //Validcao do token
    @PostMapping("/validar-token")
    public ResponseEntity<?> validarToken(@RequestParam("email") String email, @RequestParam("token") String token) {
        //Validacao se o email e token baterem e instaciando o metodo Validar Token
        boolean valido = clienteService.validarToken(email, token);
        if (valido) {
            return ResponseEntity.ok().body("Token válido.");
        } else {
            return ResponseEntity.status(400).body("Token inválido ou expirado.");
        }
    }

    //Atualizacao de senha
    @PatchMapping("/atualizar-senha")
    public ResponseEntity<?> atualizarSenha(@RequestParam("email") String email, @RequestParam("novaSenha") String novaSenha) {
        try {
            //instanciando o metodo Atualizar senha
            clienteService.atualizarSenha(email, novaSenha);
            return ResponseEntity.ok().body("Senha atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar a senha.");
        }
    }

}
