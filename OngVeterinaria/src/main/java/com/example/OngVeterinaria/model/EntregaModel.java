package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.StatusGeral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name = "Tb_Entrega")
public class EntregaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntrega;

    @Column(name = "Endereco") @NotBlank(message = "Campo obrigatorio")
    private String endereco_Entrega;

    @Column(name = "DataComeco") @NotNull
    private LocalDate data_Comeco;

    @Column(name = "DataFinal") @NotNull
    private LocalDate data_Final;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status") @NotBlank(message = "Campo Obrigatorio")
    private StatusGeral statusEntrega;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "NotaFiscal", nullable = false)
    private ClienteEstoque Notafiscal;

}
