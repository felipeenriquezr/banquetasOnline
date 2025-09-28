// app.js

const API_URL = 'http://localhost:8080/api/products'; // Asegúrate de que este puerto sea el de tu API
const productList = document.getElementById('product-list');

/**
 * Función para obtener y mostrar todos los productos del API.
 */
async function fetchProducts() {
    try {
        // 1. Realiza la petición GET a la API
        const response = await fetch(API_URL);

        // Verifica si la respuesta HTTP fue exitosa (código 200)
        if (!response.ok) {
            // Si la API devuelve un error (ej: 404), lanzamos una excepción
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // 2. Convierte la respuesta a formato JSON
        const products = await response.json();

        // 3. Limpia el mensaje de carga
        productList.innerHTML = '';

        if (products.length === 0) {
            productList.innerHTML = '<p>No hay productos disponibles.</p>';
            return;
        }

        // 4. Itera sobre los productos y crea elementos HTML
        products.forEach(product => {
            const productDiv = document.createElement('div');
            productDiv.className = 'product-card';

            // Usamos los campos de tu modelo (name, description, price)
            productDiv.innerHTML = `
                <h2>${product.name}</h2>
                <p>${product.description}</p>
                <p><strong>Precio: $${product.price.toFixed(2)}</strong></p>
                <img src="${product.imageUrl}" alt="${product.imageAlt}" style="width: 100px;">
                <hr>
            `;
            productList.appendChild(productDiv);
        });

    } catch (error) {
        // Muestra cualquier error de conexión o del API
        console.error('Error al cargar los productos:', error);
        productList.innerHTML = `<p style="color: #11ff00;">Error al conectar con el servicio: ${error.message}</p>`;
    }
}

// Llama a la función para cargar los productos cuando la página inicia
fetchProducts();