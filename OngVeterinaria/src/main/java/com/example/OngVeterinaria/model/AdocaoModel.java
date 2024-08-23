package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.StatusGeral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "Tb_Adocao")
public class AdocaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdocao;

    @Column(name = "Status") @NotBlank(message = "Campo obrigatorio")
    @Enumerated(EnumType.STRING)
    private StatusGeral Status_Adocao;

    @ManyToOne
    @JoinColumn(name = "IdCliente", nullable = false)
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "IdAnimal", nullable = false)
    private AnimalModel animal;

}
