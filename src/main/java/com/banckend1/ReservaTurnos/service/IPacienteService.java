package com.banckend1.ReservaTurnos.service;

import com.banckend1.ReservaTurnos.entity.Paciente;

import java.util.List;

public interface IPacienteService {

  Paciente insertPaciente(Paciente paciente);
  Paciente selectPaciente(Long id);
  List<Paciente> selectAll();
  void updatePaciente(Paciente paciente);
  void deletePaciente(Long id);

}