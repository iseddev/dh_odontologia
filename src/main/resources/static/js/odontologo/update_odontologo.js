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
      .then((response) => {
        if (!response.ok) {
          return response.text().then((errorText) => {
            throw new Error(errorText || "Error en la actualización del odontólogo");
          });
        }
        return response.json();
      })
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
              <i class="lni lni-thumbs-up icon"></i>
              <strong>Odontólogo actualizado correctamente</strong>
          </div>`;
        responseContainer.innerHTML = successContent;
        responseContainer.style.display = "block";

        setTimeout(() => {
          document.querySelector("#update-form-container").style.display = "none";
          responseContainer.style.display = "none";

          // Actualizar la fila del odontólogo en la tabla
          const row = document.querySelector(`#tr_${id}`);
          row.querySelector(".td_nombre").textContent = nombre.toUpperCase();
          row.querySelector(".td_apellido").textContent = apellido.toUpperCase();
          row.querySelector(".td_matricula").textContent = matricula;
        }, 1500);
      })
      .catch((error) => {
        const errorMessage = error.message;
        const errorContent = `
          <div class="alert alert-danger alert-dismissible">
            <i class="lni lni-thumbs-down icon"></i>
            <strong>Error: ${errorMessage}</strong>
          </div>`;
        responseContainer.innerHTML = errorContent;
        responseContainer.style.display = "block";
        setTimeout(() => {
          document.querySelector("#update-form-container").style.display = "none";
          responseContainer.style.display = "none";
        }, 3000);
      });
  });
};

window.addEventListener("load", () => updateOdontologo());
