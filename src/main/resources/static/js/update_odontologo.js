function findById(id) {
    const url = `/odontologo/find/${id}`;
    const settings = { method: 'GET' }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#odontologo_nombre').value = odontologo.nombre;
            document.querySelector('#odontologo_apellido').value = odontologo.apellido;
            document.querySelector('#odontologo_matricula').value = odontologo.matricula;

            document.querySelector('#div_odontologo_updating').style.display = "block";
        })
        .catch(error => { alert("Error: " + error); })
}


window.addEventListener('load', function (e) {
    // Buscamos y obtenemos el formulario donde estan los datos que el usuario pudo haber modificado del odontólogo
    const formUpdate = document.querySelector('#update_odontologo_form');

    formUpdate.addEventListener('submit', e => {
        e.preventDefault();

        let id = document.querySelector('#odontologo_id').value;
        let nombre = document.querySelector('#odontologo_nombre').value;
        let apellido = document.querySelector('#odontologo_apellido').value;
        let matricula = document.querySelector('#odontologo_matricula').value;

        const formData = { id: id, nombre: nombre, apellido: apellido, matricula: matricula };

        const url = '/odontologo/edit';
        const settings = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = `
                    <div class="alert alert-success alert-dismissible success-added">
                        <i class="lni lni-thumbs-up icon"></i>
                        <strong>Odontólogo actualizado correctamente</strong>
                    </div>`
                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
            })
    })
})