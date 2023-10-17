<script>
	import '../app.css';
	import Footer from "./Footer.svelte";
</script>

<div class="app">
	<main>
		<slot />
	</main>

	<Footer />
</div>