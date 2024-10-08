import { resetUploadForm } from "../reset_form.js";

const addPaciente = () => {
  const formAddNewRecord = document.querySelector("#add_new_paciente");

  formAddNewRecord.addEventListener("submit", (e) => {
    e.preventDefault();

    const responseContainer = document.querySelector("#response");

    const domicilioData = {
      calle: document.querySelector("#domicilio_calle").value,
      numero: document.querySelector("#domicilio_numero").value,
      localidad: document.querySelector("#domicilio_localidad").value,
      provincia: document.querySelector("#domicilio_provincia").value,
    };

    const pacienteData = {
      nombre: document.querySelector("#paciente_nombre").value,
      apellido: document.querySelector("#paciente_apellido").value,
      dni: document.querySelector("#paciente_dni").value,
      fechaAlta: document.querySelector("#paciente_fecha_alta").value,
      domicilio: domicilioData,
    };

    const url = "/paciente/create";
    const settings = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(pacienteData),
    };

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          return response.text().then((errorText) => {
            throw new Error(errorText || "Error en la creación del paciente");
          });
        }
        return response.json();
      })
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
            <i class="lni lni-thumbs-up icon"></i>
            <strong>Paciente agregado correctamente</strong>
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

window.addEventListener("load", () => addPaciente());
