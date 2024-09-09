function deleteById(id) {
    const url = `/odontologo/delete/${id}`;
    const settings = { method: "DELETE" }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            console.log(odontologo)
        })

    let row_id = `#tr_${id}`;
    document.querySelector(row_id).remove();
}

function confirmDelete(id) {
    let confirmDelete = confirm(`Se eliminará el registro con id ${id}, presiona OK(Aceptar) para confirmar está acción.`)
    confirmDelete ? deleteById(id) : alert(`NO se eliminó el registro con id ${id}.`)
}