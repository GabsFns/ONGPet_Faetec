package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.TipoDoacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity @Table(name = "Tb_Doacao")
public class DoacaoModel implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idDocao;

    @Column(name = "Tipo") @NotBlank(message = "Campo Obrigatorio")
    private TipoDoacao tipo_Doacao;

    @Column(name = "Valor")
    private BigDecimal valor_Doacao;

    @Column(name = "Quantidade") @NotBlank(message = "Campo Obrigatorio")
    private long quantidade_Doacao;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;

}
