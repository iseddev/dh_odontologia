const deleteTurno = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el turno con id ${id}, presiona (Aceptar) para confirmar esta acción.`
  );
  confirmDelete
    ? deleteById("/turno/delete/", id)
    : alert("Operación cancelada.");
};
