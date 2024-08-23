package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.StatusGeral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity @Table(name = "Tb_Consulta")
public class ConsultaModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_consulta;

    //private Consultas tipo;
    @Column(name = "DataComeco") @NotNull
    private LocalDate data_comeco;


    @Column(name = "DataFinal") @NotNull
    private LocalDate data_final;


    @Column(name = "Descricao") @NotBlank(message = "Campo Obrigatorio")
    private String descricao;


    @Enumerated(EnumType.STRING)
    @Column(name = "Status") @NotBlank(message = "Campo Obrigatorio")
    private StatusGeral status_consulta;


    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;


    @ManyToOne
    @JoinColumn(name = "idAnimal", nullable = false)
    private AnimalModel animal;
}
