package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.StatusGeral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity @Table(name = "Tb_Consulta")
public class ConsultaModel implements Serializable {



    public ConsultaModel() {
    }

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




    // Construtor padrão

    public @NotBlank(message = "Campo Obrigatorio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Campo Obrigatorio") String descricao) {
        this.descricao = descricao;
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

    public @NotBlank(message = "Campo Obrigatorio") StatusGeral getStatus_consulta() {
        return status_consulta;
    }

    public void setStatus_consulta(@NotBlank(message = "Campo Obrigatorio") StatusGeral status_consulta) {
        this.status_consulta = status_consulta;
    }

    public @NotNull LocalDate getData_final() {
        return data_final;
    }

    public void setData_final(@NotNull LocalDate data_final) {
        this.data_final = data_final;
    }

    public @NotNull LocalDate getData_comeco() {
        return data_comeco;
    }

    public void setData_comeco(@NotNull LocalDate data_comeco) {
        this.data_comeco = data_comeco;
    }

    public long getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(long id_consulta) {
        this.id_consulta = id_consulta;
    }
}
