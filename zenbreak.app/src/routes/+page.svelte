<script>
	import logo from '$lib/images/logo.png';
	import FeatureDescriptor from "./FeatureDescriptor.svelte";
</script>

<svelte:head>
	<title>ZenBreak</title>
	<meta name="description" content="Your desktop oasis from screen fatigue. Reclaim your focus and well-being." />
</svelte:head>

<div class="flex-col">
	<section class="flex-row">
		<div>
			<div>
				<img src={logo} alt="ZenBreak logo">

				<h2>ZenBreak</h2>
			</div>

			<p>
				ZenBreak, your digital wellness companion, is the antidote to screen addiction.
				This app gently reminds you to take meaningful breaks from your devices.
				Customize your breaks and rediscover the world outside your screen.
				Embrace a healthier relationship with technology
			</p>

			<button>
				Download
			</button>
		</div>

		<div>
			<div>
				<!-- platform selector -->
			</div>

			<!-- Features images -->
			<img src={logo} alt="info" class="fixed" />
		</div>
	</section>

	<FeatureDescriptor
			title="First feature"
			description="This is the first feature description"
	/>

	<FeatureDescriptor
			title="Second feature"
			description="This is the second feature description"
	/>

	<FeatureDescriptor
			title="Third feature"
			description="This is the third feature description"
	/>
</div>