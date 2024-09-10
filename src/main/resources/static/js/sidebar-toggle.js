const sidebarToggle = document.querySelector("#sidebar-toggle");

sidebarToggle.addEventListener("click", (e) => {
  document.querySelector("#sidebar").classList.toggle("expand");
});
