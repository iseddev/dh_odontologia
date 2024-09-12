import { resetUploadForm } from "../reset_form.js";

const addOdontologo = () => {
  const formAddNewRecord = document.querySelector("#add_new_odontologo");

  formAddNewRecord.addEventListener("submit", (e) => {
    e.preventDefault();

    const responseContainer = document.querySelector("#response");

    const odontologoData = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      matricula: document.querySelector("#matricula").value,
    };

    const url = "/odontologo/create";
    const settings = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(odontologoData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
            <i class="lni lni-thumbs-up icon"></i>
            <strong>Odontólogo agregado correctamente</strong>
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
            <strong>Ya existe un odontólogo con la misma matrícula</strong>
          </div>`;
        responseContainer.innerHTML = errorContent;
        responseContainer.style.display = "block";
        resetUploadForm(".form-control");
        setTimeout(() => (responseContainer.style.display = "none"), 3000);
      });
  });
};

window.addEventListener("load", () => addOdontologo());
