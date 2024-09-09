package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import com.banckend1.ReservaTurnos.repository.IOdontologoRepository;
import com.banckend1.ReservaTurnos.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplOdontologoService implements IOdontologoService {

  private final IOdontologoRepository odontologoRepository;

  public ImplOdontologoService(IOdontologoRepository odontologoRepository) {
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public Odontologo insertOdontologo(Odontologo odontologo) { return odontologoRepository.save(odontologo); }

  @Override
  public Odontologo selectOdontologo(Long id) { return odontologoRepository.findById(id).orElse(null); }

  @Override
  public List<Odontologo> selectAll() { return odontologoRepository.findAll(); }

  @Override
  public void updateOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo); }

  @Override
  public void deleteOdontologo(Long id) {
    odontologoRepository.deleteById(id);
  }
}