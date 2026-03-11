package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findTop4ByOrderByNameAscIdAsc();
}
