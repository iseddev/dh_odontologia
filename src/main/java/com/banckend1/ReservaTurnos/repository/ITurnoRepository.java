package com.banckend1.ReservaTurnos.repository;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import com.banckend1.ReservaTurnos.entity.Paciente;
import com.banckend1.ReservaTurnos.entity.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByOdontologoAndFechaAndHora(Odontologo odontologo, LocalDate fecha, LocalTime hora);
    List<Turno> findByPacienteAndFechaAndHora(Paciente paciente, LocalDate fecha, LocalTime hora);
    List<Turno> findByOdontologoAndFecha(Odontologo odontologo, LocalDate fecha);
    List<Turno> findByPacienteAndFecha(Paciente paciente, LocalDate fecha);
}