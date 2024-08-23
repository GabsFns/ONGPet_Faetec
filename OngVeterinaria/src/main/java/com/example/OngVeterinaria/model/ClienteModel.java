package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity //Transformando em uma entidade
@Table(name = "Tb_Cliente") //Criando Tabela no Banco de Dados
public class ClienteModel implements Serializable {

    @Id  //Identificar que IdCliente é uma Primary Key no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private long idCliente;

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

    public @NotBlank(message = "Campo Obrigatorio") LocalDate getData_Cadastro() {
        return data_Cadastro;
    }

    public void setData_Cadastro(@NotBlank(message = "Campo Obrigatorio") LocalDate data_Cadastro) {
        this.data_Cadastro = data_Cadastro;
    }

    public @NotBlank(message = "Campo Obrigatorio") LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(@NotBlank(message = "Campo Obrigatorio") LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public @NotBlank(message = "Campo Obrigatorio") Genero getGeneroCliente() {
        return generoCliente;
    }

    public void setGeneroCliente(@NotBlank(message = "Campo Obrigatorio") Genero generoCliente) {
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

    public @NotBlank(message = "Campo Obrigatorio") String getEmail_Cliente() {
        return email_Cliente;
    }

    public void setEmail_Cliente(@NotBlank(message = "Campo Obrigatorio") String email_Cliente) {
        this.email_Cliente = email_Cliente;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getNome_Cliente() {
        return nome_Cliente;
    }

    public void setNome_Cliente(@NotBlank(message = "Campo Obrigatorio") String nome_Cliente) {
        this.nome_Cliente = nome_Cliente;
    }

    public @NotBlank(message = "Campo Obrigatorio") String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(@NotBlank(message = "Campo Obrigatorio") String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    @Column(name = "CPF") @NotBlank(message = "Campo Obrigatorio") //@Collum nomeia o nome da coluna e @NotBlank faz a validacao antes de enviar ao banco
    private String cpf_Cliente;

    @Column(name = "Nome") @NotBlank(message = "Campo Obrigatorio")
    private String nome_Cliente;

    @Column(name = "Email") @NotBlank(message = "Campo Obrigatorio")
    private String email_Cliente;

    @Column(name = "Senha") @NotBlank(message = "Campo Obrigatorio")
    private String password_Cliente;

    @Column(name = "Telefone") @NotBlank(message = "Campo Obrigatorio")
    private String telefone_Cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "Genero") @NotBlank(message = "Campo Obrigatorio")
    private Genero generoCliente;

    @Column(name = "DataNascimento") @NotBlank(message = "Campo Obrigatorio")
    private LocalDate data_nascimento;

    @Column(name = "DataCadastro") @NotBlank(message = "Campo Obrigatorio")
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






}
