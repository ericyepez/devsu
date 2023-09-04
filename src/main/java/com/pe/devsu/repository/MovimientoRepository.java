package com.pe.devsu.repository;

import com.pe.devsu.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :idCuenta")
    List<Movimiento> findAllByCuentaId(Long idCuenta);
}
