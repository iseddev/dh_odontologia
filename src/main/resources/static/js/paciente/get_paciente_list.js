const renderPacienteList = (data, pacienteTableId) => {
  for (let paciente of data) {
    const tableBody = document.querySelector(`#${pacienteTableId}`);
    let pacienteRow = tableBody.insertRow();
    pacienteRow.className = "tr-row";
    pacienteRow.id = `tr_${paciente.id}`;

    const updateButton = `<button id="btn_id_${paciente.id}" type="button" class="btn btn-info btn_id" onclick="renderUpdateForm(${paciente.id})" title="Actualizar éste registro">${paciente.id}</button>`;

    const deleteButton = `
      <button id="btn_delete_${paciente.id}" type="button" class="btn btn-danger btn_delete" title="Eliminar este registro" onclick="deletePaciente(${paciente.id})">
        <i class="lni lni-trash-can"></i>
      </button>`;

    pacienteRow.innerHTML = `
      <td class="td_btn_id">${updateButton}</td>
      <td class="td_nombre">${paciente.nombre.toUpperCase()}</td>
      <td class="td_apellido">${paciente.apellido.toUpperCase()}</td>
      <td class="td_dni">${paciente.dni.toUpperCase()}</td>
      <td class="td_fecha_alta">${paciente.fechaAlta}</td>
      <td class="td_calle">${paciente.domicilio.calle.toUpperCase()}</td>
      <td class="td_numero">${paciente.domicilio.numero}</td>
      <td class="td_localidad">${paciente.domicilio.localidad.toUpperCase()}</td>
      <td class="td_provincia">${paciente.domicilio.provincia.toUpperCase()}</td>
      <td class="td_btn_delete">${deleteButton}</td>`;
  }
};

const listPacientes = () => {
  const url = "/paciente/list";
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => renderPacienteList(data, "paciente-table-body"));
};

window.addEventListener("load", () => listPacientes());
