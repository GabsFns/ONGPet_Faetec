package com.example.OngVeterinaria.model;

import com.example.OngVeterinaria.model.Enum.TipoEspecie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;


@Entity @Table(name = "Tb_Animal")
public class AnimalModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Animal;

    @Column(name = "Nome") @NotBlank(message = "Campo Obrigatorio")
    private String Nome;

    @Column(name = "Especie") @NotBlank(message = "Campo Obrigatorio")
    @Enumerated(EnumType.STRING)
    private TipoEspecie Especie;

    @Column(name = "DataNascimento") @NotBlank(message = "Campo Obrigatorio")
    private LocalDate data_NascimentoAnimal;

    @Column(name = "Cor") @NotBlank(message = "Campo Obrigatorio")
    private String cor_Animal;

    @Column(name = "Peso") @NotBlank(message = "Campo Obrigatorio")
    private double peso_Animal;

    @Column(name = "foto") @NotBlank(message = "Campo Obrigatorio")
    private byte fotoAnimal;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;



}
