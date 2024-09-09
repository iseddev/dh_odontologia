package com.banckend1.ReservaTurnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "odontologos")
public class Odontologo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
  private Long id;
  private String nombre;
  private String apellido;
  private String matricula;

  @OneToMany(mappedBy = "odontologo") // Must be a ManyToOne relationship with Turno
  @JsonIgnore // Stop propagation(loop)
  private Set<Turno> turnoSet = new HashSet<>();

}