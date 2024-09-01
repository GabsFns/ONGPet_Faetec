package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
@Table(name = "Tb_Animal")
public class AnimalModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long idAnimal;

    @Column(name = "Nome")
    @NotBlank(message = "Campo Obrigatório")
    private String nome;

    @Column(name = "Especie")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo Obrigatório")
    private TipoEspecie especie;



    @Column(name = "Raca") // Adicionado campo de raça
    private String raca;


    @Column(name = "DataNascimento")
    @NotNull(message = "Campo Obrigatório")
    private String idade;


    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Cor")
    @NotBlank(message = "Campo Obrigatório")
    private String cor;

    @Column(name = "Peso")
    @NotNull(message = "Campo Obrigatório")
    private double peso;

    @Column(name = "foto")
    @NotNull(message = "Campo Obrigatório")
    private byte[] fotoAnimal;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;

    public AnimalModel(Long idAnimal) {
    }

    // Getters e Setters
    public long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEspecie getEspecie() {
        return especie;
    }

    public void setEspecie(TipoEspecie especie) {
        this.especie = especie;
    }

    public String getIdade() {
        return idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public byte[] getFotoAnimal() {
        return fotoAnimal;
    }

    public void setFotoAnimal(byte[] fotoAnimal) {
        this.fotoAnimal = fotoAnimal;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
