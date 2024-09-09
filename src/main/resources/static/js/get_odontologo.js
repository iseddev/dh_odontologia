const loadOdontologos = () => {
    // Con fetch invocamos a la API de odontólogas con el método_ GET nos devolverá un JSON con una colección de odontólogos
    const url = '/odontologo/list';
    const settings = { method: 'GET' }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
         for(odontologo of data) {
            var tableBody = document.getElementById("odontologo-table-body");
            var odontologoRow = tableBody.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;
            odontologoRow.className = "tr-row";

            let updateButton = `<button id="btn_id_${odontologo.id}" type="button" class="btn btn-info btn_id" onclick="findById(${odontologo.id})">${odontologo.id}</button>`;

            let deleteButton = `<button id="btn_delete_${odontologo.id}" type="button" class="btn btn-danger btn_delete" title="Eliminar este registro">X</button>`;

            odontologoRow.innerHTML = `
                <td class="td_btn_id">
                    <button id="btn_id_${odontologo.id}" type="button" class="btn btn-info btn_id" onclick="findById(${odontologo.id})" title="Actualizar éste registro">${odontologo.id}</button>
                </td>
                <td class="td_nombre">${odontologo.nombre.toUpperCase()}</td>
                <td class="td_apellido">${odontologo.apellido.toUpperCase()}</td>
                <td class="td_matricula">${odontologo.matricula}</td>
                <td class="td_btn_delete">
                    <button id="btn_delete_${odontologo.id}" type="button" class="btn btn-danger btn_delete" title="Eliminar éste registro" onclick="confirmDelete(${odontologo.id})">X</button>
                </td>`;
        };
    })
}

const activateCurrentLink = currentPathLink => {
    const currentLink = document.querySelector(`a[href="${currentPathLink}"]`);
    if (currentLink) { currentLink.classList.add("active"); }
}

window.addEventListener('load', () => {
    loadOdontologos();
    activateCurrentLink("/odontologos-lista.html");
});