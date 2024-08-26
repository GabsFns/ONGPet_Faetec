package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity //Transformando em uma entidade
@Table(name = "Tb_Cliente") //Criando Tabela no Banco de Dados
public class ClienteModel implements Serializable {

    @Id  //Identificar que IdCliente é uma Primary Key no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private long idCliente;

    @Column(name = "CPF") @NotBlank(message = "Campo Obrigatorio") //@Collum nomeia o nome da coluna e @NotBlank faz a validacao antes de enviar ao banco
    private String cpf_Cliente;

    @Column(name = "Nome") @NotBlank(message = "Campo Obrigatorio")
    private String nome;

    @Column(name = "Email") @NotBlank(message = "Campo Obrigatorio")
    private String email;

    @Column(name = "Senha") @NotBlank(message = "Campo Obrigatorio")
    private String password_Cliente;

    @Column(name = "Telefone") @NotBlank(message = "Campo Obrigatorio")
    private String telefone_Cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "Genero")
    private Genero generoCliente;

    @Column(name = "DataNascimento")
    private LocalDate data_nascimento;

    @Column(name = "DataCadastro")
    private LocalDate data_Cadastro;

    @ManyToMany //Mapeando ClienteEstoque como uma tabela intermiadiaria N para N
    @JoinTable(
            name = "Tb_Cliente_Estoque",
            joinColumns = @JoinColumn(name = "idCliente"),
            inverseJoinColumns = @JoinColumn(name = "idEstoque")
    )
    private Set<EstoqueModel> estoques;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoModel> enderecos;

    @Column(name = "ResetToken")
    private String resetToken;

    @Column(name = "ResetTokenExpiration")
    private LocalDateTime resetTokenExpiration;


    @PrePersist
    protected void onCreate() {
        this.data_Cadastro = LocalDate.now();  // Define a data de cadastro como a data atual
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }


    public Set<EstoqueModel> getEstoques() {
        return estoques;
    }

    public void setEstoques(Set<EstoqueModel> estoques) {
        this.estoques = estoques;
    }

    public LocalDate getData_Cadastro() {
        return data_Cadastro;
    }

    public void setData_Cadastro( LocalDate data_Cadastro) {
        this.data_Cadastro = data_Cadastro;
    }

    public  LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento( LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Genero getGeneroCliente() {
        return generoCliente;
    }

    public void setGeneroCliente(Genero generoCliente) {
        this.generoCliente = generoCliente;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getTelefone_Cliente() {
        return telefone_Cliente;
    }

    public void setTelefone_Cliente(@NotBlank(message = "Campo Obrigatorio") String telefone_Cliente) {
        this.telefone_Cliente = telefone_Cliente;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getPassword_Cliente() {
        return password_Cliente;
    }

    public void setPassword_Cliente(@NotBlank(message = "Campo Obrigatorio") String password_Cliente) {
        this.password_Cliente = password_Cliente;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Campo Obrigatorio") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getNome() {
        return nome;
    }

    public void setNome_Cliente(@NotBlank(message = "Campo Obrigatorio") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getCpf_Cliente() {
        return cpf_Cliente;
    }
    public void setCpf_Cliente(@NotBlank(message = "Campo Obrigatorio") String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }


    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }





}
