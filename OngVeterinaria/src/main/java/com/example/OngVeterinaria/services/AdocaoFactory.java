package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AdocaoModel;
import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.Enum.StatusGeral;

public class AdocaoFactory {
    public static AdocaoModel criarAdocao(Long idCliente, Long idAnimal, byte[] comprovante) {
        AdocaoModel adocao = new AdocaoModel();
        ClienteModel cliente = new ClienteModel(idCliente);
        AnimalModel animal = new AnimalModel(idAnimal);

        adocao.setCliente(cliente);
        adocao.setAnimal(animal);
        adocao.setComprovante(comprovante);
        adocao.setStatus_Adocao(StatusGeral.PENDENTE);

        return adocao;
    }
}
