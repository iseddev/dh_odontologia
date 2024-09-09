package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Paciente;
import com.banckend1.ReservaTurnos.repository.IPacienteRepository;
import com.banckend1.ReservaTurnos.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplPacienteService implements IPacienteService {

  private final IPacienteRepository pacienteRepository;
  public ImplPacienteService(IPacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }

  @Override
  public Paciente insertPaciente(Paciente paciente) {
    return pacienteRepository.save(paciente);
  }

  @Override
  public Paciente selectPaciente(Long id) {
    return pacienteRepository.findById(id).orElse(null);
  }

  @Override
  public List<Paciente> selectAll() { return pacienteRepository.findAll(); }

  @Override
  public void updatePaciente(Paciente paciente) {
    pacienteRepository.save(paciente);
  }

  @Override
  public void deletePaciente(Long id) {
    pacienteRepository.deleteById(id);
  }
}