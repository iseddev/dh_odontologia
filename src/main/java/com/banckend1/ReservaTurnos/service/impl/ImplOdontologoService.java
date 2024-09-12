package com.banckend1.ReservaTurnos.service.impl;

import com.banckend1.ReservaTurnos.entity.Odontologo;
import com.banckend1.ReservaTurnos.repository.IOdontologoRepository;
import com.banckend1.ReservaTurnos.service.IOdontologoService;
import com.banckend1.ReservaTurnos.exception.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplOdontologoService implements IOdontologoService {

  private final IOdontologoRepository odontologoRepository;

  public ImplOdontologoService(IOdontologoRepository odontologoRepository) {
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public Odontologo insertOdontologo(Odontologo odontologo) {
    if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty() ||
        odontologo.getApellido() == null || odontologo.getApellido().isEmpty() ||
        odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {

      throw new BadRequestException("Los campos nombre, apellido y matrícula son obligatorios.");
    }

    // Check if an odontólogo with the same matrícula already exists
    if (odontologoRepository.existsByMatricula(odontologo.getMatricula())) {
      throw new HandleConflictException("Ya existe un odontólogo con la matrícula " + odontologo.getMatricula());
    }

    // Save the odontólogo if all validations pass
    return odontologoRepository.save(odontologo);
  }

  @Override
  public Odontologo selectOdontologo(Long id) {
    return odontologoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Odontólogo con id " + id + " no existe."));
  }

  @Override
  public List<Odontologo> selectAll() {
    return odontologoRepository.findAll();
  }

  @Override
  public void updateOdontologo(Odontologo odontologo) {
    if (!odontologoRepository.existsById(odontologo.getId())) {
      throw new ResourceNotFoundException("Odontólogo con id " + odontologo.getId() + " no existe.");
    }

    // Validate that the required fields are not empty
    if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty() ||
        odontologo.getApellido() == null || odontologo.getApellido().isEmpty() ||
        odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {

      throw new BadRequestException("Los campos nombre, apellido y matrícula son obligatorios para la actualización.");
    }

    // Validate if the matrícula is unique (check if another odontólogo has the same matrícula)
    Odontologo existingOdontologo = odontologoRepository.findById(odontologo.getId()).orElse(null);
    if (existingOdontologo != null && !existingOdontologo.getMatricula().equals(odontologo.getMatricula()) &&
        odontologoRepository.existsByMatricula(odontologo.getMatricula())) {
      throw new HandleConflictException("Ya existe un odontólogo con la matrícula " + odontologo.getMatricula());
    }

    // Save the updated odontólogo if all validations pass
    odontologoRepository.save(odontologo);
  }

  @Override
  public void deleteOdontologo(Long id) {
    if (!odontologoRepository.existsById(id)) {
      throw new ResourceNotFoundException("Odontólogo con id " + id + " no existe.");
    }
    odontologoRepository.deleteById(id);
  }
}