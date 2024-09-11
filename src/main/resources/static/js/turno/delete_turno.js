const deleteTurno = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el registro con id ${id}, presiona OK (Aceptar) para confirmar esta acción.`
  );
  confirmDelete
    ? deleteById("/turno/delete/", id)
    : alert("Operación cancelada.");
};
