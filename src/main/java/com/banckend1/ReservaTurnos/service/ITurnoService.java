package com.banckend1.ReservaTurnos.service;

import com.banckend1.ReservaTurnos.entity.Turno;

import java.util.List;

public interface ITurnoService {

  Turno insertTurno(Turno turno);
  Turno selectTurno(Long id);
  List<Turno> selectAll();
  void deleteTurno(Long id);
}