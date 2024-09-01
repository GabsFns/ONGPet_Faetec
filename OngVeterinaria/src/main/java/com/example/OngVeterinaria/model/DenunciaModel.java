package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.model.Enum.TipoDenucias;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tb_Denuncia")
public class DenunciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDenuncia; // Identificador único para a entidade

    @Enumerated(EnumType.STRING)
    @Column(name = "Denuncias")
    private TipoDenucias tipoDenucias;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Data")
    private LocalDate dataDenuncia;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusGeral statusGeral;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "idAnimal")
    private AnimalModel animal;

    @ManyToOne
    @JoinColumn(name = "idEndereco")
    private EnderecoModel endereco;

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    // Getters e Setters
    public Long getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Long id) {
        this.idDenuncia = idDenuncia;
    }

    public TipoDenucias getTipoDenucias() {
        return tipoDenucias;
    }

    public void setTipoDenucias(TipoDenucias tipoDenucias) {
        this.tipoDenucias = tipoDenucias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(LocalDate dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }
}


