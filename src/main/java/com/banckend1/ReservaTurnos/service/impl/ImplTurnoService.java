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
    // Validación de campos obligatorios
    if (turno.getOdontologo() == null ||
        turno.getPaciente() == null ||
        turno.getFecha() == null) {
        
        throw new BadRequestException("Los campos odontólogo, paciente y fecha son obligatorios.");
    }

    // Cargar los datos completos del odontólogo y el paciente desde la base de datos
    Long odontologoId = turno.getOdontologo().getId();
    Long pacienteId = turno.getPaciente().getId();

    // Buscar odontólogo por id
    Odontologo odontologo = odontologoRepository.findById(odontologoId)
        .orElseThrow(() -> new ResourceNotFoundException("Odontólogo con id " + odontologoId + " no existe."));

    // Buscar paciente por id
    Paciente paciente = pacienteRepository.findById(pacienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + pacienteId + " no existe."));

    // Verificar si el odontólogo ya tiene un turno asignado en la misma fecha
    List<Turno> turnosOdontologo = turnoRepository.findByOdontologoAndFecha(odontologo, turno.getFecha());
    if (!turnosOdontologo.isEmpty()) {
        throw new BadRequestException("El odontólogo " + odontologo.getNombre() + " " +
            odontologo.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha());
    }

    // Verificar si el paciente ya tiene un turno asignado en la misma fecha
    List<Turno> turnosPaciente = turnoRepository.findByPacienteAndFecha(paciente, turno.getFecha());
    if (!turnosPaciente.isEmpty()) {
        throw new BadRequestException("El paciente " + paciente.getNombre() + " " +
            paciente.getApellido() + " ya tiene un turno asignado en la fecha " + turno.getFecha());
    }

    // Asignar los objetos completos al turno
    turno.setOdontologo(odontologo);
    turno.setPaciente(paciente);

    // Guardar el turno en la base de datos
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
