package com.banckend1.ReservaTurnos.repository;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    boolean existsByMatricula(String matricula);
}