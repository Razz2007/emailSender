const API_URL = "http://localhost:8080"; // Asegúrate que este sea el puerto correcto

const form = document.getElementById('emailForm');
const responseDiv = document.getElementById('response');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const email = document.getElementById('email').value.trim();
  const type = document.getElementById('type').value;

  if (!email || !type) {
    responseDiv.textContent = 'Por favor completa todos los campos.';
    responseDiv.style.color = 'red';
    return;
  }

  try {
    const res = await fetch(`${API_URL}/${type}/${email}`);
    const text = await res.text();

    if (res.ok) {
      responseDiv.textContent = text;
      responseDiv.style.color = 'green';
    } else {
      responseDiv.textContent = 'Error del servidor: ' + text;
      responseDiv.style.color = 'red';
    }
  } catch (error) {
    responseDiv.textContent = 'Error de red: ' + error.message;
    responseDiv.style.color = 'red';
  }
});
