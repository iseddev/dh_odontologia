import { resetUploadForm } from "../reset_form.js";

const addTurno = () => {
  const formAddNewTurno = document.querySelector("#add_new_turno");

  formAddNewTurno.addEventListener("submit", (e) => {
    e.preventDefault();

    const responseContainer = document.querySelector("#response");

    const turnoData = {
      odontologo: {
        id: parseInt(document.querySelector("#odontologo_id").value)
      },
      paciente: {
        id: parseInt(document.querySelector("#paciente_id").value)
      },
      fecha: document.querySelector("#turno_fecha").value
    };

    const url = "/turno/create";
    const settings = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(turnoData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
            <i class="lni lni-thumbs-up icon"></i>
            <strong>Turno agregado correctamente</strong>
          </div>`;
        responseContainer.innerHTML = successContent;
        responseContainer.style.display = "block";
        resetUploadForm(".form-control");
        setTimeout(() => (responseContainer.style.display = "none"), 3000);
      })
      .catch((error) => {
        const errorContent = `
          <div class="alert alert-danger alert-dismissible">
            <i class="lni lni-thumbs-down icon"></i>
            <strong>Error: ${error}</strong>
          </div>`;
        responseContainer.innerHTML = errorContent;
        responseContainer.style.display = "block";
        resetUploadForm(".form-control");
        setTimeout(() => (responseContainer.style.display = "none"), 3000);
      });
  });
};

window.addEventListener("load", () => addTurno());
