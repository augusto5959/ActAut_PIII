/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/main/resources/templates/**/*.{html,js}', // Rutas a tus archivos HTML y JS en templates
    './src/main/resources/static/frontend/**/*.{html,js}' // Rutas a archivos HTML y JS en frontend (si es necesario)
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

