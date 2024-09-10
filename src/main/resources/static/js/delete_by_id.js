const deleteById = (endpoint, id) => {
  const url = `${endpoint}${id}`;
  const settings = { method: "DELETE" };

  fetch(url, settings).then((response) =>
    response.ok
      ? alert(`Se eliminó el registro con id ${id}.`)
      : alert(`NO se eliminó el registro con id ${id}.`).catch((error) =>
          console.error("Error:", error)
        )
  );

  let row_id = `#tr_${id}`;
  document.querySelector(row_id).remove();
};
