<script>
	import '../app.css';
	import Footer from "../components/Footer.svelte";
</script>

<div class="app">
	<div class="absolute h-full w-full bg-hero -z-20"></div>
	<main class="flex flex-col w-full">
		<slot />

		<div class="mt-32 w-full">
			<Footer />
		</div>
	</main>
</div>