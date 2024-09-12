package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Turno;
import com.banckend1.ReservaTurnos.exception.*;
import com.banckend1.ReservaTurnos.repository.ITurnoRepository;
import com.banckend1.ReservaTurnos.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplTurnoService implements ITurnoService {

  private final ITurnoRepository turnoRepository;

  public ImplTurnoService(ITurnoRepository turnoRepository) {
    this.turnoRepository = turnoRepository;
  }

  @Override
  public Turno insertTurno(Turno turno) {
    // Validación de campos obligatorios
    if (turno.getOdontologo() == null ||
        turno.getPaciente() == null ||
        turno.getFecha() == null) {

      throw new BadRequestException("Los campos odontólogo, paciente y fecha son obligatorios.");
    }

    return turnoRepository.save(turno);
  }

  @Override
  public Turno selectTurno(Long id) {
    return turnoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Turno con id " + id + " no existe."));
  }

  @Override
  public void updateTurno(Turno turno) {
    // Comprobar si el turno existe
    if (!turnoRepository.existsById(turno.getId())) {
      throw new ResourceNotFoundException("Turno con id " + turno.getId() + " no existe.");
    }

    // Validación de campos obligatorios
    if (turno.getOdontologo() == null ||
        turno.getPaciente() == null ||
        turno.getFecha() == null) {

      throw new BadRequestException("Los campos odontólogo, paciente y fecha son obligatorios para la actualización.");
    }

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
