package com.banckend1.ReservaTurnos.repository;

import com.banckend1.ReservaTurnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {}