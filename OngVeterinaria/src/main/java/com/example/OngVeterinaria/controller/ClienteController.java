package com.example.OngVeterinaria.controller;


import com.example.OngVeterinaria.model.*;
import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.model.Enum.TipoDenucias;
import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import com.example.OngVeterinaria.repository.AdocaoRepository;
import com.example.OngVeterinaria.services.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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

    @Autowired
    private AdocaoServices adocaoServices;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AdocaoRepository adocaoRepository;


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
    @PutMapping("/atualizarDadosCliente/{id}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteModel clienteAtualizado) {
        ClienteModel clienteAtualizadoResposta = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(clienteAtualizadoResposta);
    }


    //Cadastrar Animal para agendamento e Denuncia(PERDIDOS)
    @PostMapping("/cadastrar/animal")
    public ResponseEntity<AnimalModel> cadastrarAnimal(
            @RequestParam Long idCliente,
            @RequestParam String nome,
            @RequestParam TipoEspecie especie,
            @RequestParam String idade,
            @RequestParam String cor,
            @RequestParam double peso,
            @RequestParam(required = false) byte[] foto,
            @RequestParam(required = false) String descricao)
    {
        // Instanciando o método cadastrarAnimal
        AnimalModel animal = animalServices.cadastrarAnimal(idCliente, nome, especie, idade, cor, peso, foto, descricao);
        return ResponseEntity.ok(animal);
    }
    //Filtro para pesquisar animal
    @GetMapping("/buscaAnimalr")
    public ResponseEntity<List<AnimalModel>> buscarAnimais(
            @RequestParam(required = false) TipoEspecie especie,
            @RequestParam(required = false) String raca) {

        List<AnimalModel> animais = animalServices.buscarAnimaisPorFiltros(especie, raca);
        return ResponseEntity.ok(animais);
    }



    //Realizar Agendamento
    @PostMapping("/agendarConsulta")
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






    @PostMapping("/RealizarDenuncia")
    public ResponseEntity<String> realizarDenuncia(@RequestBody DenunciaModel denunciaModel){
        DenunciaModel novaDenuncia = clienteService.RealizarDenuncias(denunciaModel);
        if (novaDenuncia != null){
            return ResponseEntity.ok("Denuncia Realizada Com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar Denuncia");
        }
    }




    // Solicitar Adocao
    @PostMapping("/solicitarAdocao")
    public ResponseEntity<String> solicitarAdocao(
            @RequestParam Long idCliente,
            @RequestParam Long idAnimal,
            @RequestParam("comprovante") MultipartFile comprovante) {
        try {
            AdocaoModel adocao = new AdocaoModel();
            adocao.setCliente(new ClienteModel(idCliente)); // Assumindo que ClienteModel tem um construtor com ID
            adocao.setAnimal(new AnimalModel(idAnimal)); // Assumindo que AnimalModel tem um construtor com ID
            adocao.setComprovante(comprovante.getBytes());

            adocaoServices.solicitarAdocao(adocao);

            return ResponseEntity.ok("Pedido de adoção enviado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o comprovante.");
        }
    }


    //Gerar Comprovante
    @GetMapping("/comprovanteAdocao/gerar/{id}")
    public ResponseEntity<byte[]> gerarEEnviarComprovante(@PathVariable Long id) throws MessagingException {
        AdocaoModel adocao = adocaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Adoção não encontrada"));

        byte[] comprovanteAprovacao = adocaoServices.gerarComprovanteAprovacao(adocao);
        adocaoServices.enviarEmailComprovante(adocao.getCliente().getEmail(), comprovanteAprovacao);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprovante_adoção.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(comprovanteAprovacao);
    }

    //Dwonload Comprovante
    @GetMapping("/comprovanteAdocao/baixar/{id}")
    public ResponseEntity<byte[]> downloadComprovante(@PathVariable Long id) {
        AdocaoModel adocao = adocaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        byte[] comprovante = adocao.getComprovante();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprovante.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(comprovante);
    }


}

