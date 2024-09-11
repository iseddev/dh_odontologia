const deletePaciente = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el registro con id ${id}, presiona OK (Aceptar) para confirmar esta acción.`
  );
  confirmDelete
    ? deleteById("/paciente/delete/", id)
    : alert("Operación cancelada.");
};
