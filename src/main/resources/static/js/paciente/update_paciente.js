const renderUpdateForm = (id) => {
  const url = `/paciente/find/${id}`;
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      const updateContainer = document.querySelector("#update-form-container");
      document.querySelector("#paciente_id").value = data.id;
      document.querySelector("#paciente_nombre").value = data.nombre;
      document.querySelector("#paciente_apellido").value = data.apellido;
      document.querySelector("#paciente_dni").value = data.dni;
      document.querySelector("#paciente_fecha_alta").value = data.fechaAlta;
      document.querySelector("#domicilio_calle").value = data.domicilio.calle;
      document.querySelector("#domicilio_numero").value = data.domicilio.numero;
      document.querySelector("#domicilio_localidad").value = data.domicilio.localidad;
      document.querySelector("#domicilio_provincia").value = data.domicilio.provincia;
      updateContainer.style.display = "block";
    })
    .catch((error) => {
      const responseContainer = document.querySelector("#update-response");
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

const updatePaciente = () => {
  const updateForm = document.querySelector("#update_paciente_form");

  updateForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const responseContainer = document.querySelector("#update-response");

    const id = document.querySelector("#paciente_id").value;
    const nombre = document.querySelector("#paciente_nombre").value;
    const apellido = document.querySelector("#paciente_apellido").value;
    const dni = document.querySelector("#paciente_dni").value;
    const fechaAlta = document.querySelector("#paciente_fecha_alta").value;
    const domicilio = {
      calle: document.querySelector("#domicilio_calle").value,
      numero: document.querySelector("#domicilio_numero").value,
      localidad: document.querySelector("#domicilio_localidad").value,
      provincia: document.querySelector("#domicilio_provincia").value
    };

    const pacienteData = { id, nombre, apellido, dni, fechaAlta, domicilio };

    const url = "/paciente/edit";
    const settings = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(pacienteData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
              <i class="lni lni-thumbs-up icon"></i>
              <strong>Paciente actualizado correctamente</strong>
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
            <strong>Error: ${error}</strong>
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

window.addEventListener("load", () => updatePaciente());
