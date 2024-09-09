package com.banckend1.ReservaTurnos.repository;

import com.banckend1.ReservaTurnos.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {}