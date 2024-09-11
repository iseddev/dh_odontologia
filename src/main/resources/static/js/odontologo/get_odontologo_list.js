const renderOdontologoList = (data, odontologoTableId) => {
  for (let odontologo of data) {
    const tableBody = document.querySelector(`#${odontologoTableId}`);
    let odontologoRow = tableBody.insertRow();
    odontologoRow.className = "tr-row";
    odontologoRow.id = `tr_${odontologo.id}`;

    const updateButton = `<button id="btn_id_${odontologo.id}" type="button" class="btn btn-info btn_id" onclick="renderUpdateForm(${odontologo.id})" title="Actualizar éste registro">${odontologo.id}</button>`;

    const deleteButton = `
      <button id="btn_delete_${odontologo.id}" type="button" class="btn btn-danger btn_delete" title="Eliminar este registro" onclick="deleteOdontologo(${odontologo.id})">
        <i class="lni lni-trash-can"></i>
      </button>`;

    odontologoRow.innerHTML = `
      <td class="td_btn_id">${updateButton}</td>
      <td class="td_nombre">${odontologo.nombre.toUpperCase()}</td>
      <td class="td_apellido">${odontologo.apellido.toUpperCase()}</td>
      <td class="td_matricula">${odontologo.matricula}</td>
      <td class="td_btn_delete">${deleteButton}</td>`;
  }
};

const listOdontologos = () => {
  const url = "/odontologo/list";
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => renderOdontologoList(data, "odontologo-table-body"));
};

window.addEventListener("load", () => listOdontologos());
