package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import com.banckend1.ReservaTurnos.entity.Paciente;
import com.banckend1.ReservaTurnos.entity.Turno;
import com.banckend1.ReservaTurnos.exception.*;
import com.banckend1.ReservaTurnos.repository.IOdontologoRepository;
import com.banckend1.ReservaTurnos.repository.IPacienteRepository;
import com.banckend1.ReservaTurnos.repository.ITurnoRepository;
import com.banckend1.ReservaTurnos.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplTurnoService implements ITurnoService {

  private final ITurnoRepository turnoRepository;
  private final IOdontologoRepository odontologoRepository;
  private final IPacienteRepository pacienteRepository;

  public ImplTurnoService(ITurnoRepository turnoRepository, IOdontologoRepository odontologoRepository,
      IPacienteRepository pacienteRepository) {
    this.turnoRepository = turnoRepository;
    this.odontologoRepository = odontologoRepository;
    this.pacienteRepository = pacienteRepository;
  }

  @Override
  public Turno insertTurno(Turno turno) {
    if (turno.getOdontologo() == null ||
        turno.getPaciente() == null ||
        turno.getFecha() == null ||
        turno.getHora() == null) {

      throw new BadRequestException("Los campos odontólogo, paciente, fecha y hora son obligatorios.");
    }

    Long odontologoId = turno.getOdontologo().getId();
    Long pacienteId = turno.getPaciente().getId();

    Odontologo odontologo = odontologoRepository.findById(odontologoId)
        .orElseThrow(() -> new ResourceNotFoundException("Odontólogo con id " + odontologoId + " no existe."));

    Paciente paciente = pacienteRepository.findById(pacienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + pacienteId + " no existe."));

    // Verificar conflictos para odontólogo
    List<Turno> turnosOdontologo = turnoRepository.findByOdontologoAndFecha(odontologo, turno.getFecha());
    for (Turno t : turnosOdontologo) {
      if (t.getHora().equals(turno.getHora())) {
        throw new HandleConflictException("El odontólogo " + odontologo.getNombre() + " " +
            odontologo.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha() +
            " a la hora " + turno.getHora());
      }
    }

    // Verificar conflictos para paciente
    List<Turno> turnosPaciente = turnoRepository.findByPacienteAndFecha(paciente, turno.getFecha());
    for (Turno t : turnosPaciente) {
      if (t.getHora().equals(turno.getHora())) {
        throw new HandleConflictException("El paciente " + paciente.getNombre() + " " +
            paciente.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha() +
            " a la hora " + turno.getHora());
      }
    }

    turno.setOdontologo(odontologo);
    turno.setPaciente(paciente);

    return turnoRepository.save(turno);
  }

  @Override
  public Turno selectTurno(Long id) {
    return turnoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Turno con id " + id + " no existe."));
  }

  @Override
  public void updateTurno(Turno turno) {
    if (!turnoRepository.existsById(turno.getId())) {
      throw new ResourceNotFoundException("Turno con id " + turno.getId() + " no existe.");
    }

    if (turno.getOdontologo() == null ||
        turno.getPaciente() == null ||
        turno.getFecha() == null ||
        turno.getHora() == null) {
      throw new BadRequestException(
          "Los campos odontólogo, paciente, fecha y hora son obligatorios para la actualización.");
    }

    Long odontologoId = turno.getOdontologo().getId();
    Long pacienteId = turno.getPaciente().getId();

    Odontologo odontologo = odontologoRepository.findById(odontologoId)
        .orElseThrow(() -> new ResourceNotFoundException("Odontólogo con id " + odontologoId + " no existe."));

    Paciente paciente = pacienteRepository.findById(pacienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + pacienteId + " no existe."));

    // Verificar conflictos para odontólogo
    List<Turno> turnosOdontologo = turnoRepository.findByOdontologoAndFecha(odontologo, turno.getFecha());
    for (Turno t : turnosOdontologo) {
      if (t.getHora().equals(turno.getHora()) && !t.getId().equals(turno.getId())) {
        throw new HandleConflictException("El odontólogo " + odontologo.getNombre() + " " +
            odontologo.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha() +
            " a la hora " + turno.getHora());
      }
    }

    // Verificar conflictos para paciente
    List<Turno> turnosPaciente = turnoRepository.findByPacienteAndFecha(paciente, turno.getFecha());
    for (Turno t : turnosPaciente) {
      if (t.getHora().equals(turno.getHora()) && !t.getId().equals(turno.getId())) {
        throw new HandleConflictException("El paciente " + paciente.getNombre() + " " +
            paciente.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha() +
            " a la hora " + turno.getHora());
      }
    }

    turno.setOdontologo(odontologo);
    turno.setPaciente(paciente);

    turnoRepository.save(turno);
  }

  @Override
  public List<Turno> selectAll() {
    return turnoRepository.findAll();
  }

  @Override
  public void deleteTurno(Long id) {
    if (!turnoRepository.existsById(id)) {
      throw new ResourceNotFoundException("Turno con id " + id + " no existe.");
    }
    turnoRepository.deleteById(id);
  }
}