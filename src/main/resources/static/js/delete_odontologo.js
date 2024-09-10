const deleteOdontologo = (id) => {
  let confirmDelete = confirm(
    `Se eliminará el registro con id ${id}, presiona OK(Aceptar) para confirmar está acción.`
  );
  confirmDelete
    ? deleteById("/odontologo/delete/", id)
    : alert("Operación cancelada.");
};
