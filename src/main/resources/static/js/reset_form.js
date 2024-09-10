export const resetUploadForm = (className) => {
  const inputs = document.querySelectorAll(className);
  inputs.forEach((input) => (input.value = ""));
};
