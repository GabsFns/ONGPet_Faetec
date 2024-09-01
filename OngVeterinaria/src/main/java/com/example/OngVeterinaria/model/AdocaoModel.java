package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.StatusGeral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Tb_Adocao")
public class AdocaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdocao;

    @Column(name = "Status") @NotBlank(message = "Campo obrigatorio")
    @Enumerated(EnumType.STRING)
    private StatusGeral StatusAdocao;

    @Column(name = "Comprovante")
    private byte[] comprovante;

    @ManyToOne
    @JoinColumn(name = "IdCliente", nullable = false)
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "IdAnimal", nullable = false)
    private AnimalModel animal;


    public byte[] getComprovante() {
        return comprovante;
    }

    public void setComprovante(byte[] comprovante) {
        this.comprovante = comprovante;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public @NotBlank(message = "Campo obrigatorio") StatusGeral getStatusAdocao() {
        return StatusAdocao;
    }

    public void setStatus_Adocao(@NotBlank(message = "Campo obrigatorio") StatusGeral status_Adocao) {
        StatusAdocao = status_Adocao;
    }

    public long getIdAdocao() {
        return idAdocao;
    }

    public void setIdAdocao(long idAdocao) {
        this.idAdocao = idAdocao;
    }

}
