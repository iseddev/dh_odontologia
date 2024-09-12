package com.banckend1.ReservaTurnos.service;

import com.banckend1.ReservaTurnos.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

  Odontologo insertOdontologo(Odontologo odontologo);
  Odontologo selectOdontologo(Long id);
  List<Odontologo> selectAll();
  void updateOdontologo(Odontologo odontologo);
  void deleteOdontologo(Long id);

}