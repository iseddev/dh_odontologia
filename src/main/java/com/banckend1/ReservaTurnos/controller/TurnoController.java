package com.banckend1.ReservaTurnos.controller;

import com.banckend1.ReservaTurnos.entity.Turno;
import com.banckend1.ReservaTurnos.service.ITurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

  private final ITurnoService turnoService;
  public TurnoController(ITurnoService turnoService) { this.turnoService = turnoService; }

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
  public ResponseEntity<List<Turno>> turnoList() {
    List<Turno> turnoList = turnoService.selectAll();
    return ResponseEntity.ok(turnoList);
  }
  // Delete a Turno
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
    turnoService.deleteTurno(id);
    return ResponseEntity.noContent().build(); // 204 No Content
  }
}