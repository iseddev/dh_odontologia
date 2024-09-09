package com.banckend1.ReservaTurnos.repository;

import com.banckend1.ReservaTurnos.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {}