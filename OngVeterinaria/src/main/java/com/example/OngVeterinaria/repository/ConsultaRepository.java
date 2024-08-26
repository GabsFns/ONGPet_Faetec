package com.example.OngVeterinaria.repository;

import com.example.OngVeterinaria.model.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    @Query("SELECT c FROM ConsultaModel c " +
            "WHERE (:nome IS NULL OR c.cliente.nome = :nome) " +
            "AND (:idCliente IS NULL OR c.cliente.id = :idCliente) " +
            "AND (:dataConsulta IS NULL OR (c.data_comeco <= :dataConsulta AND c.data_final >= :dataConsulta))")
    List<ConsultaModel> findByFilters(@Param("nome") String nome,
                                      @Param("idCliente") Long idCliente,
                                      @Param("dataConsulta") LocalDate dataConsulta);
}
