const renderUpdateForm = (id) => {
  const url = `/odontologo/find/${id}`;
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      const updateContainer = document.querySelector("#update-form-container");
      document.querySelector("#odontologo_id").value = data.id;
      document.querySelector("#odontologo_nombre").value = data.nombre;
      document.querySelector("#odontologo_apellido").value = data.apellido;
      document.querySelector("#odontologo_matricula").value = data.matricula;
      updateContainer.style.display = "block";
    })
    .catch((error) => {
      const errorContent = `
        <div class="alert alert-danger alert-dismissible">
          <i class="lni lni-thumbs-down icon"></i>
          <strong>Error: ${error}</strong>
        </div>`;
      responseContainer.innerHTML = errorContent;
      responseContainer.style.display = "block";
      setTimeout(() => (responseContainer.style.display = "none"), 3000);
    });
};

const updateOdontologo = () => {
  const updateForm = document.querySelector("#update_odontologo_form");

  updateForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const updateForm = document.querySelector("#update_odontologo_form");
    const responseContainer = document.querySelector("#update-response");

    const id = document.querySelector("#odontologo_id").value;
    const nombre = document.querySelector("#odontologo_nombre").value;
    const apellido = document.querySelector("#odontologo_apellido").value;
    const matricula = document.querySelector("#odontologo_matricula").value;

    const odontologoData = { id, nombre, apellido, matricula };

    const url = "/odontologo/edit";
    const settings = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(odontologoData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
              <i class="lni lni-thumbs-up icon"></i>
              <strong>Odontólogo actualizado correctamente</strong>
          </div>`;
        responseContainer.innerHTML = successContent;
        responseContainer.style.display = "block";
        setTimeout(() => {
          updateForm.style.display = "none";
          responseContainer.style.display = "none";
        }, 3000);
      })
      .catch((error) => {
        const errorContent = `
          <div class="alert alert-danger alert-dismissible">
            <i class="lni lni-thumbs-down icon"></i>
            <strong>Error de actualización: Ya existe un odontólogo con la misma matrícula</strong>
          </div>`;
        responseContainer.innerHTML = errorContent;
        responseContainer.style.display = "block";
        setTimeout(() => {
          updateForm.style.display = "none";
          responseContainer.style.display = "none";
        }, 3000);
      });
  });
};

window.addEventListener("load", () => updateOdontologo());
