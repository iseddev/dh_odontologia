import { resetUploadForm } from "../reset_form.js";

const addTurno = () => {
  const formAddNewTurno = document.querySelector("#add_new_turno");

  formAddNewTurno.addEventListener("submit", (e) => {
    e.preventDefault();

    const responseContainer = document.querySelector("#response");

    const turnoData = {
      odontologo: {
        id: parseInt(document.querySelector("#odontologo_id").value),
      },
      paciente: {
        id: parseInt(document.querySelector("#paciente_id").value),
      },
      fecha: document.querySelector("#turno_fecha").value,
      hora: document.querySelector("#turno_hora").value,
    };

    const url = "/turno/create";
    const settings = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(turnoData),
    };

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          return response.text().then((errorText) => {
            throw new Error(errorText || "Error en la creación del turno");
          });
        }
        return response.json();
      })
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
            <i class="lni lni-thumbs-up icon"></i>
            <strong>Turno agregado correctamente</strong>
          </div>`;
        responseContainer.innerHTML = successContent;
        responseContainer.style.display = "block";
        resetUploadForm(".form-control");
        setTimeout(() => (responseContainer.style.display = "none"), 1500);
      })
      .catch((error) => {
        let errorMessage = "Error desconocido";
        errorMessage = error.message;

        const errorContent = `
          <div class="alert alert-danger alert-dismissible">
            <i class="lni lni-thumbs-down icon"></i>
            <strong>Error: ${errorMessage}</strong>
          </div>`;
        responseContainer.innerHTML = errorContent;
        responseContainer.style.display = "block";
        resetUploadForm(".form-control");
        setTimeout(() => (responseContainer.style.display = "none"), 3000);
      });
  });
};

window.addEventListener("load", () => addTurno());
