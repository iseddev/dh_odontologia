
const resetUploadForm = () => {
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.querySelector('#matricula').value = "";
}

const addOdontologo = () => {

    const formAddNewRecord = document.querySelector('#add_new_odontologo');

    formAddNewRecord.addEventListener('submit', e => {

        e.preventDefault();

        const odontologoData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value
        };

        const url = '/odontologo/create';
        const settings = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(odontologoData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = `
                    <div class="alert alert-success alert-dismissible success-added">
                        <i class="lni lni-thumbs-up icon"></i>
                        <strong>Odontólogo agregado correctamente</strong>
                    </div>`
                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();
            })
            .catch(error => {
                let errorAlert = `
                    <div class="alert alert-danger alert-dismissible">
                        <i class="lni lni-thumbs-up icon"></i>
                        <strong>Error: Intente nuevamente</strong>
                    </div>`
                  document.querySelector('#response').innerHTML = errorAlert;
                  document.querySelector('#response').style.display = "block";
                 resetUploadForm();
            })
    });
}

window.addEventListener('load', e => addOdontologo());