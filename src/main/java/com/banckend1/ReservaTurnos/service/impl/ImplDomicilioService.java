package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Domicilio;
import com.banckend1.ReservaTurnos.repository.IDomicilioRepository;
import com.banckend1.ReservaTurnos.service.IDomicilioService;
import com.banckend1.ReservaTurnos.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplDomicilioService implements IDomicilioService {

  public final IDomicilioRepository domicilioRepository;

  public ImplDomicilioService(IDomicilioRepository domicilioRepository) {
    this.domicilioRepository = domicilioRepository;
  }

  @Override
  public Domicilio insertDomicilio(Domicilio domicilio) {
    // Validate mandatory fields
    if (domicilio.getCalle() == null || domicilio.getCalle().isEmpty() ||
        domicilio.getNumero() == null || domicilio.getNumero() <= 0 ||
        domicilio.getLocalidad() == null || domicilio.getLocalidad().isEmpty() ||
        domicilio.getProvincia() == null || domicilio.getProvincia().isEmpty()) {

      throw new BadRequestException("Los campos calle, número, localidad y provincia son obligatorios.");
    }

    return domicilioRepository.save(domicilio);
  }

  @Override
  public Domicilio selectDomicilio(Long id) { return domicilioRepository.findById(id).orElse(null); }

  @Override
  public List<Domicilio> selectAll() { return domicilioRepository.findAll(); }

  @Override
  public void updateDomicilio(Domicilio domicilio) { domicilioRepository.save(domicilio); }

  @Override
  public void deleteDomicilio(Long id) { domicilioRepository.deleteById(id); }
}