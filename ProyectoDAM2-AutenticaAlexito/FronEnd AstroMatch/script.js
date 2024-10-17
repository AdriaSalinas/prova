// Función que se ejecuta cuando la página termina de cargar
document.addEventListener("DOMContentLoaded", () => {
    const usersContainer = document.getElementById('users-list');
    const getMatchBtn = document.getElementById('get-match-btn');

    // Función para obtener y mostrar los usuarios
    async function obtenerUsuarios() {
        try {
            const response = await fetch('http://localhost:8080/usuarios'); // Cambia el puerto si es necesario
            const usuarios = await response.json();

            if (usuarios.length > 0) {
                let html = '<ul>';
                usuarios.forEach(usuario => {
                    html += `<li>${usuario.nombre} - ${usuario.signoZodiacal}</li>`;
                });
                html += '</ul>';
                usersContainer.innerHTML = html;
            } else {
                usersContainer.innerHTML = 'No hay usuarios registrados.';
            }
        } catch (error) {
            usersContainer.innerHTML = 'Error al obtener los usuarios.';
            console.error('Error:', error);
        }
    }

    // Función para buscar un match cuando el botón es clicado
    async function buscarMatch() {
        try {
            const response = await fetch('http://localhost:8080/match', {
                method: 'GET',
            });

            if (response.ok) {
                const data = await response.json();
                document.getElementById('match-result').innerText = `Match encontrado: ${data.nombre}`;
            } else {
                document.getElementById('match-result').innerText = 'No se encontró un match';
            }
        } catch (error) {
            document.getElementById('match-result').innerText = 'Error al buscar el match';
            console.error('Error al buscar el match:', error);
        }
    }

    // Llama a la función para obtener usuarios al cargar la página
    obtenerUsuarios();

    // Verifica si el botón de match existe en el DOM y agrega el event listener
    if (getMatchBtn) {
        getMatchBtn.addEventListener('click', buscarMatch);
    } else {
        console.error('El botón de match no se encontró en el DOM');
    }
});
