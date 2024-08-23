package com.example.OngVeterinaria.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "Tb_Admin")
public class AdministradorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Adm;

    @Column(name = "Email")
    @NotBlank(message = "Campo Obrigatório")
    private String emailAdm;

    @Column(name = "Senha")
    @NotBlank(message = "Campo Obrigatório")
    private String passwordAdm;

    public long getId_Adm() {
        return id_Adm;
    }

    public void setId_Adm(long id_Adm) {
        this.id_Adm = id_Adm;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public String getPasswordAdm() {
        return passwordAdm;
    }

    public void setPasswordAdm(String passwordAdm) {
        this.passwordAdm = passwordAdm;
    }
}
