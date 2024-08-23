package com.example.OngVeterinaria.model;


import com.example.OngVeterinaria.model.Enum.TipoDenucias;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Tb_Denuncia")
public class DenunciaModel implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idDenuncia;


    @Enumerated(EnumType.STRING)
    @Column(name = "Denuncias") @NotBlank(message = "Campo Obrigatorio")
    private TipoDenucias tipo_Denuncia;


    @Column(name = "Descricao") @NotBlank(message = "Campo Obrigatorio")
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", referencedColumnName = "idEndereco")
    private EnderecoModel endereco;


    @ManyToOne
    @JoinColumn(name = "idAnimal")
    private AnimalModel animal;


    @Column(name = "Valor")
    private Long valor;
}
