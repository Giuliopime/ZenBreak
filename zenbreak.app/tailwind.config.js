/** @type {import('tailwindcss').Config} */
export default {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	theme: {
		extend: {
			colors: {
				primary: '#4f8436',
				secondary: '#C1D9B0',
				contrast: '#ff7c2b',
				tertiary: '#ffd3b4',
			}
		}
	},
	plugins: [
		require('@tailwindcss/typography'),
	]
};
