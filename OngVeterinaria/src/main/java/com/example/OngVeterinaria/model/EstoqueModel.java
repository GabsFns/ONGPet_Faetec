package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.TipoEstoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity @Table(name = "Tb_Estoque")
public class EstoqueModel implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEstoque;

    @Column(name = "nome") @NotBlank(message = "Campo Obrigatorio")
    private String Nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo") @NotBlank(message = "Campo Obrigatorio")
    private TipoEstoque tipo_estoque;

    @Column(name = "Preco") @NotBlank(message = "Campo Obrigatorio")
    private BigDecimal preco;

    @Column(name = "Quantidade") @NotBlank(message = "Campo Obrigatorio")
    private long quantidade;

    @Column(name = "DataEstoque") @NotNull
    private LocalDate data_Estoque;

    @ManyToMany(mappedBy = "estoques")
    private Set<ClienteModel> clientes;



}

