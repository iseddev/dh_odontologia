package com.banckend1.ReservaTurnos.controller;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import com.banckend1.ReservaTurnos.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

  private final IOdontologoService odontologoService;
  public OdontologoController(IOdontologoService odontologoService) {
    this.odontologoService = odontologoService;
  }


  // Create(insert) new Odontologo
  @PostMapping("/create")
  public ResponseEntity<Odontologo> createOdontologo(@RequestBody Odontologo odontologo) {
    Odontologo newOdontologo = odontologoService.insertOdontologo(odontologo);
    return ResponseEntity.status(HttpStatus.CREATED).body(newOdontologo);
  }


  // Read(select) an Odontologo
  @GetMapping("/find/{id}")
  public ResponseEntity<Odontologo> getOdontologo(@PathVariable Long id){
    return ResponseEntity.ok(odontologoService.selectOdontologo(id));
  }


  // Read(select) all Odontologos
  @GetMapping("/list")
  public ResponseEntity<List<Odontologo>> OdontoList() {
    List<Odontologo> listaOdonto = odontologoService.selectAll();
    return ResponseEntity.ok(listaOdonto);
  }


  // Update(update) an Odontologo
  @PutMapping("/edit")
  public ResponseEntity<Odontologo> updateOdontologo(@RequestBody Odontologo updateOdontologo) {
    odontologoService.updateOdontologo(updateOdontologo);
    return ResponseEntity.status(HttpStatus.OK).body(updateOdontologo);
  }


  // Delete(delete) an Odontologo
  @DeleteMapping ("/delete/{id}")
  public ResponseEntity<?> deleteOdontologo(@PathVariable Long id){
    odontologoService.deleteOdontologo(id);
    return ResponseEntity.ok("Se eliminó el odontologo con id: " + id + " exitosamente");
  }
}