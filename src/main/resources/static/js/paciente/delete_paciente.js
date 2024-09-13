const deletePaciente = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el paciente con id ${id}, presiona (Aceptar) para confirmar esta acción.`
  );
  confirmDelete
    ? deleteById("/paciente/delete/", id)
    : alert("Operación cancelada.");
};
