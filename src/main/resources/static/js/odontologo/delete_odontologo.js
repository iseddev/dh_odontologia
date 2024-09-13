const deleteOdontologo = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el odontólogo con id ${id}, presiona (Aceptar) para confirmar está acción.`
  );
  confirmDelete
    ? deleteById("/odontologo/delete/", id)
    : alert("Operación cancelada.");
};
