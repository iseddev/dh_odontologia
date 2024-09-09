package com.banckend1.ReservaTurnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pacientes")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;
  private String dni;
  private LocalDate fechaAlta;

  @OneToOne(cascade = CascadeType.ALL)
  private Domicilio domicilio;

  @OneToMany(mappedBy = "paciente") // Must be a ManyToOne relationship with Turno
  @JsonIgnore // Stop propagation(loop)
  private Set<Turno> turnoSet = new HashSet<>();

}