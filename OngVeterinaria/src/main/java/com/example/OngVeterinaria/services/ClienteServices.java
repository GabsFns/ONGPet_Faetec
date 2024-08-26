package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.ConsultaModel;
import com.example.OngVeterinaria.repository.ClienteRepository;
import com.example.OngVeterinaria.repository.ConsultaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConsultaRepository consultaRepository;

    //METODO CADASTRAR CLIENTE
    public ClienteModel cadastrarCliente(ClienteModel cliente) {
        //Criptografando a senha do cliente
        cliente.setPassword_Cliente(passwordEncoder.encode(cliente.getPassword_Cliente()));
//        cliente.setData_Cadastro(LocalDate.now()); // Define a data de cadastro automaticamente
        //Salvando o cliente
        return clienteRepository.save(cliente);
    }


    //METODO LOGIN CLIENTE
    public ClienteModel login(String email, String password_Cliente) {
        //Buscando o email do usuario
        ClienteModel cliente = clienteRepository.findByEmail(email);
        //validacao de email e a senha do cliente forem iguais
        //matches(descriptografia a senha)
        if (cliente != null && passwordEncoder.matches(password_Cliente, cliente.getPassword_Cliente())) {
            return cliente;
        }
        throw new RuntimeException("Credenciais inválidas.");
    }

    //BUSCAR O CLIENTE PELO O ID
    public Optional<ClienteModel> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    //ATUALIZAR DADOS DO CLIENTE
    public ClienteModel atualizarCliente(Long id, ClienteModel clienteAtualizado) {
        //Buscando o Id do cliente
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os campos necessários
        clienteExistente.setCpf_Cliente(clienteAtualizado.getCpf_Cliente());
        clienteExistente.setNome_Cliente(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setPassword_Cliente(clienteAtualizado.getPassword_Cliente());
        clienteExistente.setTelefone_Cliente(clienteAtualizado.getTelefone_Cliente());
        clienteExistente.setGeneroCliente(clienteAtualizado.getGeneroCliente());
        clienteExistente.setData_nascimento(clienteAtualizado.getData_nascimento());

        return clienteRepository.save(clienteExistente);
    }



    //GERAR DE TOKEN E ENVIO DE EMAIL
    private static final int TOKEN_LENGTH = 6; // Define o comprimento do token
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    //METODO GERAR TOKEN
    public void gerarTokenRecuperacao(String email) {
        ClienteModel cliente = clienteRepository.findByEmail(email); //busca o email do usuario
        if (cliente != null) {
            String token = gerarTokenAleatorio(); //puxa o metodo de geracao aleataria
            cliente.setResetToken(token);
            cliente.setResetTokenExpiration(LocalDateTime.now().plusHours(1)); //Tempo estimado para o token ficar invalido
            clienteRepository.save(cliente); //salvo o token do usuario

            try {
                SimpleMailMessage message = new SimpleMailMessage(); //envio da mensagem
                message.setTo(email); //pega o email do cliente
                message.setFrom("no-reply@suaempresa.com"); // Use um endereço de e-mail válido para enviar o e-mail
                message.setSubject("Redefinição de Senha");
                message.setText("Olá, utilize o seguinte código para redefinir sua senha: " + token);
                mailSender.send(message); //envia para o usuario
            } catch (Exception e) {
                throw new RuntimeException("Erro ao enviar o e-mail.", e);
            }
        } else {
            throw new RuntimeException("Cliente não encontrado.");
        }
    }

    //Gerador de Token aleatorio
    private String gerarTokenAleatorio() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            token.append(CHARACTERS.charAt(index));
        }
        return token.toString();
    }

    //METODO VALIDAR TOKEN DO CLIENTE
    public boolean validarToken(String email, String token) {
        //busca o email dele
        ClienteModel cliente = clienteRepository.findByEmail(email);
        return cliente != null && token.equals(cliente.getResetToken()) && LocalDateTime.now().isBefore(cliente.getResetTokenExpiration());
    }

    //METODO ATUALIZAR SENHA
    public void atualizarSenha(String email, String novaSenha) {
        ClienteModel cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            cliente.setPassword_Cliente(passwordEncoder.encode(novaSenha)); // Aplica hash na nova senha
            cliente.setResetToken(null); //deixa nulo no banco
            cliente.setResetTokenExpiration(null); //deixa nulo no banco
            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado.");
        }
    }



}
