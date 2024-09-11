const renderUpdateForm = (id) => {
  const url = `/turno/find/${id}`;
  const settings = { method: "GET" };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      const updateContainer = document.querySelector("#update-form-container");

      document.querySelector("#turno_id").value = data.id;

      // Obtener y llenar el select de pacientes
      fetch('/paciente/list', { method: "GET" })
        .then(response => response.json())
        .then(pacientes => {
          const pacienteSelect = document.querySelector("#paciente_id");
          pacienteSelect.innerHTML = pacientes.map(p => 
            `<option value="${p.id}" ${p.id === data.paciente.id ? "selected" : ""}>
              ${p.nombre.toUpperCase()} ${p.apellido.toUpperCase()}
            </option>`).join('');
        });

      // Obtener y llenar el select de odontólogos
      fetch('/odontologo/list', { method: "GET" })
        .then(response => response.json())
        .then(odontologos => {
          const odontologoSelect = document.querySelector("#odontologo_id");
          odontologoSelect.innerHTML = odontologos.map(o => 
            `<option value="${o.id}" ${o.id === data.odontologo.id ? "selected" : ""}>
              ${o.nombre.toUpperCase()} ${o.apellido.toUpperCase()}
            </option>`).join('');
        });

      // Establecer la fecha del turno
      document.querySelector("#turno_fecha").value = data.fecha;

      // Mostrar el contenedor del formulario
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

const updateTurno = () => {
  const updateForm = document.querySelector("#update_turno_form");

  updateForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const responseContainer = document.querySelector("#update-response");
    
    const turnoData = {
      id: document.querySelector("#turno_id").value, 
      odontologo: {
        id: parseInt(document.querySelector("#odontologo_id").value)
      },
      paciente: {
        id: parseInt(document.querySelector("#paciente_id").value)
      },
      fecha: document.querySelector("#turno_fecha").value
    };

    const url = "/turno/edit";
    const settings = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(turnoData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(() => {
        const successContent = `
          <div class="alert alert-success alert-dismissible success-added">
              <i class="lni lni-thumbs-up icon"></i>
              <strong>Turno actualizado correctamente</strong>
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

window.addEventListener("load", () => updateTurno());
