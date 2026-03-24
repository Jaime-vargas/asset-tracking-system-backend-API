package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableRowDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findTop4ByOrderByNameAscIdAsc();

    // LIST OF ALL CLIENTS WITH THEIR ID, NAME, NUMBER OF BRANCHES, AND NUMBER OF HARDWARE
    @Query("""
        SELECT new com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableRowDTO(
            c.id,
            c.name,
            COUNT(DISTINCT b.id),
            COUNT(DISTINCT h.id)
        )
        FROM Client c
        LEFT JOIN c.branches b
        LEFT JOIN b.hardware h
        GROUP BY c.id, c.name
        ORDER BY c.name ASC
    """)
    List<ClientTableRowDTO> getClientTableRows();
}
