package com.banckend1.ReservaTurnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "turnos")
public class Turno {

    @Id
    private Long id;

    @ManyToOne
    private Odontologo odontologo;

    @ManyToOne
    private Paciente paciente;

    private LocalDate fecha;

}