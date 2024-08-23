package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.StatusCliente_Estoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

@Entity @Table(name = "Tb_Compra")
public class ClienteEstoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNotaFiscal;




}
