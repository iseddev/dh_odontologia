package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Paciente;
import com.banckend1.ReservaTurnos.entity.Domicilio;
import com.banckend1.ReservaTurnos.exception.BadRequestException;
import com.banckend1.ReservaTurnos.exception.ResourceNotFoundException;
import com.banckend1.ReservaTurnos.repository.IPacienteRepository;
import com.banckend1.ReservaTurnos.service.IPacienteService;
import com.banckend1.ReservaTurnos.service.IDomicilioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplPacienteService implements IPacienteService {

  private final IPacienteRepository pacienteRepository;
  private final IDomicilioService domicilioService;

  public ImplPacienteService(IPacienteRepository pacienteRepository, IDomicilioService domicilioService) {
    this.pacienteRepository = pacienteRepository;
    this.domicilioService = domicilioService;
  }

  @Override
  public Paciente insertPaciente(Paciente paciente) {
    // Validate mandatory fields
    if (paciente.getNombre() == null || paciente.getNombre().isEmpty() ||
        paciente.getApellido() == null || paciente.getApellido().isEmpty() ||
        paciente.getDni() == null || paciente.getDni().isEmpty()) {

      throw new BadRequestException("Los campos nombre, apellido y DNI son obligatorios.");
    }

    // Validate and save domicilio
    if (paciente.getDomicilio() == null) {
      throw new BadRequestException("El paciente debe tener un domicilio.");
    }

    // Save the domicilio and associate it with the patient
    Domicilio savedDomicilio = domicilioService.insertDomicilio(paciente.getDomicilio());
    paciente.setDomicilio(savedDomicilio);

    return pacienteRepository.save(paciente);
  }

  @Override
  public Paciente selectPaciente(Long id) {
    return pacienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + id + " no existe."));
  }

  @Override
  public List<Paciente> selectAll() {
    return pacienteRepository.findAll();
  }

  @Override
  public void updatePaciente(Paciente paciente) {
    if (!pacienteRepository.existsById(paciente.getId())) {
      throw new ResourceNotFoundException("Paciente con id " + paciente.getId() + " no existe.");
    }

    // Validate mandatory fields
    if (paciente.getNombre() == null || paciente.getNombre().isEmpty() ||
        paciente.getApellido() == null || paciente.getApellido().isEmpty() ||
        paciente.getDni() == null || paciente.getDni().isEmpty()) {

      throw new BadRequestException("Los campos nombre, apellido y DNI son obligatorios para la actualización.");
    }

    // Ensure the domicilio exists
    if (paciente.getDomicilio() == null) {
      throw new BadRequestException("El paciente debe tener un domicilio.");
    }

    pacienteRepository.save(paciente);
  }

  @Override
  public void deletePaciente(Long id) {
    if (!pacienteRepository.existsById(id)) {
      throw new ResourceNotFoundException("Paciente con id " + id + " no existe.");
    }
    pacienteRepository.deleteById(id);
  }
}
