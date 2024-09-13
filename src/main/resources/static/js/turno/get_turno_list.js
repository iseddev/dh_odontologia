const renderTurnoList = (data, turnoTableId) => {
  for (let turno of data) {
    const tableBody = document.querySelector(`#${turnoTableId}`);
    let turnoRow = tableBody.insertRow();
    turnoRow.className = "tr-row";
    turnoRow.id = `tr_${turno.id}`;

    const updateButton = `<button id="btn_id_${turno.id}" type="button" class="btn btn-info btn_id" onclick="renderUpdateForm(${turno.id})" title="Actualizar este turno">${turno.id}</button>`;

    const deleteButton = `
      <button id="btn_delete_${turno.id}" type="button" class="btn btn-danger btn_delete" title="Eliminar este turno" onclick="deleteTurno(${turno.id})">
        <i class="lni lni-trash-can"></i>
      </button>`;

    turnoRow.innerHTML = `
      <td class="td_btn_id">${updateButton}</td>
      <td class="td_paciente">${turno.paciente.nombre.toUpperCase()} ${turno.paciente.apellido.toUpperCase()}</td>
      <td class="td_odontologo">${turno.odontologo.nombre.toUpperCase()} ${turno.odontologo.apellido.toUpperCase()}</td>
      <td class="td_fecha">${turno.fecha}</td>
      <td class="td_hora">${turno.hora}</td>
      <td class="td_btn_delete">${deleteButton}</td>`;
  }
};
const listTurnos = () => {
  const url = "/turno/list";
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => renderTurnoList(data, "turno-table-body"));
};
window.addEventListener("load", () => listTurnos());

