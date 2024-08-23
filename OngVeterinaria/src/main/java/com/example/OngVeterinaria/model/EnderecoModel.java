package com.example.OngVeterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEndereco;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Rua")
    private String rua;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Numero")
    private String numero;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Cidade")
    private String cidade;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Bairro")
    private String bairro;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Estado")
    private String estado;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "Cep")
    private String cep;

    @OneToOne(mappedBy = "endereco")
    private DenunciaModel denuncia;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteModel cliente;
}
