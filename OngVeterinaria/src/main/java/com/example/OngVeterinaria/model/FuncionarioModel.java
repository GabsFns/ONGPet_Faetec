package com.example.OngVeterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.example.OngVeterinaria.model.Enum.Genero;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity @Table(name = "Tb_Funcionario")
public class FuncionarioModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_funcionario;


    public @NotBlank(message = "Campo Obrigatorio") String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(@NotBlank(message = "Campo Obrigatorio") String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public @NotNull LocalDate getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(@NotNull LocalDate data_emissao) {
        this.data_emissao = data_emissao;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getPassword_funcionario() {
        return password_funcionario;
    }

    public void setPassword_funcionario(@NotBlank(message = "Campo Obrigatorio") String password_funcionario) {
        this.password_funcionario = password_funcionario;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getEmail_funcionario() {
        return email_funcionario;
    }

    public void setEmail_funcionario(@NotBlank(message = "Campo Obrigatorio") String email_funcionario) {
        this.email_funcionario = email_funcionario;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getCpf_funcionario() {
        return cpf_funcionario;
    }

    public void setCpf_funcionario(@NotBlank(message = "Campo Obrigatorio") String cpf_funcionario) {
        this.cpf_funcionario = cpf_funcionario;
    }

    public @NotBlank(message = "Campo Obrigatorio") Genero getGeneroFuncionario() {
        return generoFuncionario;
    }

    public void setGeneroFuncionario(@NotBlank(message = "Campo Obrigatorio") Genero generoFuncionario) {
        this.generoFuncionario = generoFuncionario;
    }

    public long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    @Column(name = "nome") @NotBlank(message = "Campo Obrigatorio")
    private String nome_funcionario;

    @Column(name = "CPF") @NotBlank(message = "Campo Obrigatorio")
    private String cpf_funcionario;

    @Column(name = "Genero") @NotBlank(message = "Campo Obrigatorio")
    @Enumerated(EnumType.STRING)
    private Genero generoFuncionario;

    @Column(name = "Email") @NotBlank(message = "Campo Obrigatorio")
    private String email_funcionario;

    @Column(name = "Senha") @NotBlank(message = "Campo Obrigatorio")
    private String password_funcionario;

    @Column(name = "DataEmissao") @NotNull
    private LocalDate data_emissao;


}
