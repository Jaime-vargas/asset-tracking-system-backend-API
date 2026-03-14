package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
}
