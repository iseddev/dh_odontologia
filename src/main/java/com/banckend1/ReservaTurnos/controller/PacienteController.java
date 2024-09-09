package com.banckend1.ReservaTurnos.controller;

import com.banckend1.ReservaTurnos.entity.Paciente;
import com.banckend1.ReservaTurnos.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

  private final IPacienteService pacienteService;

  public PacienteController(IPacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }

  // Create(insert) a new Paciente
  @PostMapping("/create")
  public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
    Paciente newPaciente = pacienteService.insertPaciente(paciente);
    return ResponseEntity.status(HttpStatus.CREATED).body(newPaciente);
  }


  // Read(select) a Paciente
  @GetMapping("/find/{id}")
  public ResponseEntity<Paciente> getPaciente(@PathVariable Long id){
    return ResponseEntity.ok(pacienteService.selectPaciente(id));
  }


  // Read(select) all Pacientes
  @GetMapping("/list")
  public ResponseEntity<List<Paciente>> pacienteList() {
    List<Paciente> pacienteList = pacienteService.selectAll();
    return ResponseEntity.ok(pacienteList);
  }


  // Update(update) a Paciente
  @PutMapping("/edit")
  public ResponseEntity<Paciente> updatePaciente(@RequestBody Paciente paciente) {
    pacienteService.updatePaciente(paciente);
    return ResponseEntity.status(HttpStatus.OK).body(paciente);
  }


  // Delete(delete) a Paciente
  @DeleteMapping ("/delete/{id}")
  public ResponseEntity<?> deletePaciente(@PathVariable Long id){
    pacienteService.deletePaciente(id);
    return ResponseEntity.noContent().build();
  }
}