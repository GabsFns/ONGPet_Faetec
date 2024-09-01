package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.DenunciaModel;
import com.example.OngVeterinaria.model.Enum.TipoDenucias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DenunciaRepository extends JpaRepository <DenunciaModel, Long> {

    // Buscar denúncias por idCliente
    List<DenunciaModel> findByCliente_Id(Long idCliente);

    // Buscar denúncias por data
    List<DenunciaModel> findByDataDenuncia(LocalDate dataDenuncia);

    // Buscar denúncias por tipo
    List<DenunciaModel> findByTipoDenucias(TipoDenucias tipoDenucias);

    @Query("SELECT d FROM DenunciaModel d WHERE d.cliente.id = :idCliente AND d.dataDenuncia = :dataDenuncia AND d.tipoDenucias = :tipoDenucias")
    List<DenunciaModel> buscarPorClienteDataETipo(
            @Param("idCliente") Long idCliente,
            @Param("dataDenuncia") LocalDate dataDenuncia,
            @Param("tipoDenucias") TipoDenucias tipoDenucias
    );
}
