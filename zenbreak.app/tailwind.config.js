/** @type {import('tailwindcss').Config} */
export default {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	theme: {
		extend: {
			colors: {
				primary: '#4f8436',
				secondary: '#C1D9B0'
			}
		}
	},
	plugins: [
		require('@tailwindcss/typography'),
	]
};
