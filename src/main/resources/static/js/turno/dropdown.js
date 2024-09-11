const populateDropdowns = () => {
    const pacienteSelect = document.querySelector("#paciente_id");
    const odontologoSelect = document.querySelector("#odontologo_id");
  
    // Cargar pacientes
    fetch("/paciente/list") // Cambia esta URL a la que corresponda para obtener los pacientes
      .then(response => response.json())
      .then(pacientes => {
        pacientes.forEach(paciente => {
          const option = document.createElement("option");
          option.value = paciente.id;
          option.textContent = `${paciente.nombre} ${paciente.apellido}`;
          pacienteSelect.appendChild(option);
        });
      })
      .catch(error => console.error("Error al cargar pacientes:", error));
  
    // Cargar odontólogos
    fetch("/odontologo/list") // Cambia esta URL a la que corresponda para obtener los odontólogos
      .then(response => response.json())
      .then(odontologos => {
        odontologos.forEach(odontologo => {
          const option = document.createElement("option");
          option.value = odontologo.id;
          option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
          odontologoSelect.appendChild(option);
        });
      })
      .catch(error => console.error("Error al cargar odontólogos:", error));
  };
  
  window.addEventListener("load", () => populateDropdowns());
  