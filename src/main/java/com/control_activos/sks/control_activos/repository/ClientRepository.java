package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableRowDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findTop4ByOrderByNameAscIdAsc();
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

    @Query("""
    SELECT new com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO(
        c.id,
        r.id,
        r.dueDate
    )
    FROM Client c
    JOIN c.branches b
    JOIN b.hardware h
    JOIN h.reports r
    WHERE r.active = true
""")
    List<ReportCountDTO> getActiveReports();

}
