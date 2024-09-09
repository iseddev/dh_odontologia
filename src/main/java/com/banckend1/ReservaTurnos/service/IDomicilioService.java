package com.banckend1.ReservaTurnos.service;

import com.banckend1.ReservaTurnos.entity.Domicilio;

import java.util.List;

public interface IDomicilioService {

  Domicilio insertDomicilio(Domicilio domicilio);
  Domicilio selectDomicilio(Long id);
  List<Domicilio> selectAll();
  void updateDomicilio(Domicilio domicilio);
  void deleteDomicilio(Long id);

}