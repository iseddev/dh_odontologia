package com.banckend1.ReservaTurnos.controller;

import com.banckend1.ReservaTurnos.entity.Turno;
import com.banckend1.ReservaTurnos.service.ITurnoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

  private final ITurnoService turnoService;

  public TurnoController(ITurnoService turnoService) {
    this.turnoService = turnoService;
  }

  // Create(insert) new Turno
  @PostMapping("/create")
  public ResponseEntity<Turno> createTurno(@RequestBody Turno turno) {
    return ResponseEntity.ok(turnoService.insertTurno(turno));
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Turno> getTurno(@PathVariable Long id) {
    return ResponseEntity.ok(turnoService.selectTurno(id));
  }

  // Read(select) all Turnos
  @GetMapping("/list")
  public ResponseEntity<?> turnoList() {
    List<Turno> turnoList = turnoService.selectAll();

    if (turnoList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay turnos registrados en la base de datos");
    }
    return ResponseEntity.ok(turnoList);
  }

  @PutMapping("/edit")
  public ResponseEntity<Turno> updateTurno(@RequestBody Turno turno) {
    turnoService.updateTurno(turno);
    return ResponseEntity.status(HttpStatus.OK).body(turno);
  }

  // Delete a Turno
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteTurno(@PathVariable Long id) {
    turnoService.deleteTurno(id);
    return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el turno con id: " + id + " exitosamente");
  }
}