package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Turno;
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
    return turnoRepository.save(turno);
  }

  @Override
  public Turno selectTurno(Long id) {
    return turnoRepository.findById(id).orElse(null);
  }

  @Override
  public void updateTurno(Turno turno) {
    turnoRepository.save(turno);
  }

  @Override
  public List<Turno> selectAll() {
    return turnoRepository.findAll();
  }

  @Override
  public void deleteTurno(Long id) {
    turnoRepository.deleteById(id);
  }
}