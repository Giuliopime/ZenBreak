<script>
	import { onMount } from 'svelte';

	// App images
	import macosBreakImg from "$lib/images/demo/macos/macos_break.png"
	import macosBehaviourImg from "$lib/images/demo/macos/macos_behaviour.png"
	import macosAppereanceImg from "$lib/images/demo/macos/macos_appereance.png"
	import macosSystemImg from "$lib/images/demo/macos/macos_system.png"

	import windowsBreakImg from "$lib/images/demo/windows/windows_break.png"
	import windowsBehaviourImg from "$lib/images/demo/windows/windows_behaviour.png"
	import windowsAppearanceImg from "$lib/images/demo/windows/windows_appereance.png"
	import windowsSystemImg from "$lib/images/demo/windows/windows_system.png"

	import linuxBreakImg from "$lib/images/demo/linux/linux_break.png"
	import linuxBehaviourImg from "$lib/images/demo/linux/linux_behaviour.png"
	import linuxAppearanceImg from "$lib/images/demo/linux/linux_appereance.png"
	import linuxSystemImg from "$lib/images/demo/linux/linux_system.png"

	// Logos and Icons
	import DropdownArrowIcon from "../components/icons/DropdownArrowIcon.svelte";
	import ZBLogo from '$lib/images/logo.png';
	import WindowsLogo from "virtual:icons/bxl/windows";
	import AppleLogo from "virtual:icons/bxl/apple";
	import LinuxLogo from "virtual:icons/uil/linux";
	import MultiplatformLogo from "virtual:icons/logos/compose-multiplatform";
	import OpenSourceLogo from "virtual:icons/mdi/github";
	import NativeLogo from "virtual:icons/cib/swift";

	// Functions
	import { getOS } from "../utils/os.js";

	// Page components
	import HorizontalSelector from "../components/HorizontalSelector.svelte";
	import ImageCarousel from "../components/ImageCarousel.svelte";
	import FeatureBox from "../components/FeatureBox.svelte";

	const platforms = ["macOS", "Windows", "Linux"];
	let selectedPlatformIndex = 0;

	// Dropdown
	let platformDropdownOpen = false;
	let dropdownLogo = AppleLogo;

	// Carousel
	/** @type string[] **/
	$: images = [macosBreakImg, macosBehaviourImg, macosAppereanceImg, macosSystemImg];
	let imageIndex = 0;

	$: {
		switch (selectedPlatformIndex) {
			case 0:
				dropdownLogo = AppleLogo;
				images = [macosBreakImg, macosBehaviourImg, macosAppereanceImg, macosSystemImg];
				break;
			case 1:
				dropdownLogo = WindowsLogo;
				images = [windowsBreakImg, windowsBehaviourImg, windowsAppearanceImg, windowsSystemImg];
				break;
			case 2:
				dropdownLogo = LinuxLogo;
				images = [linuxBreakImg, linuxBehaviourImg, linuxAppearanceImg, linuxSystemImg];
				break;
		}
	}

	onMount(() => {
		const os = getOS()
		switch (os) {
			case "macos":
				selectedPlatformIndex = 0;
				break;
			case "windows":
				selectedPlatformIndex = 1;
				break;
			case "linux":
				selectedPlatformIndex = 2;
				break;
			case "unknown":
				console.log("unknown operating system")
		}
	})
</script>

<svelte:head>
	<title>ZenBreak</title>
	<meta name="description" content="Your desktop oasis from screen fatigue. Reclaim your focus and well-being." />
</svelte:head>

<div class="flex flex-col items-center p-4">
	<section class="flex flex-col md:flex-row h-screen items-center justify-evenly w-full">
		<div class="flex items-center">
			<div class="flex flex-col items-center md:items-start">
				<div class="flex justify-center items-center gap-4 mb-4">
					<img src={ZBLogo} alt="ZenBreak logo" class="w-24 h-24  md:w-32 md:h-32 select-none">

					<span class="text-5xl md:text-6xl text-center md:text-left font-bold">ZenBreak</span>
				</div>

				<span class="text-xl mb-6 text-center md:text-left px-4">
					ZenBreak gently reminds you to take <span class="text-primary font-semibold">meaningful breaks</span> from your devices.<br>
					Customize your breaks and embrace a healthier relationship with technology.
				</span>

				<div class="flex md:justify-start justify-center w-full gap-px text-white px-4">
					<button class="bg-primary py-2 px-6 rounded-l-full hover:opacity-90 transition-opacity" on:click={() => {}}>
						Download
					</button>
					<div class="relative">
						<button class="w-full h-full flex gap-3 items-center justify-center bg-primary py-2 px-4 rounded-r-full cursor-pointer hover:opacity-90 transition-opacity"
								on:click={() => platformDropdownOpen = !platformDropdownOpen}
						>
							<svelte:component this={dropdownLogo} />

							<DropdownArrowIcon rotated={platformDropdownOpen} />
						</button>

						<div class={(platformDropdownOpen ? "inline-block" : "hidden" ) + " absolute z-10 flex flex-col w-max mt-2"}>
							{#each platforms as item, i (i)}
								<button on:click={() => {selectedPlatformIndex = i; platformDropdownOpen = false}}
										class={(i === selectedPlatformIndex ? "bg-black text-white hover:bg-neutral-800" : "bg-white text-black hover:bg-neutral-100  transition-all")
								  + " pl-4 pr-12 py-2 hover:underline cursor-pointer text-start"}>{item}</button>
							{/each}
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="flex flex-col gap-4 items-center">
			<HorizontalSelector
					bind:selectedIndex={selectedPlatformIndex}
					platforms="{platforms}" />

			<ImageCarousel images="{images}" index="{imageIndex}"/>
		</div>
	</section>

	<div class="flex flex-col items-center gap-16">
		<span class="text-4xl font-semibold">Why ZenBreak?</span>

		<div class="flex flex-wrap gap-6 items-center justify-center">
			<FeatureBox
					title="Multiplatform"
					description="ZenBreak can be used from any desktop operating system, whether it's MacOS, Windows or Linux!"
					logo={MultiplatformLogo}
			/>

			<FeatureBox
					title="Native and lightweight"
					description="ZenBreak is built as a native application for each operating system, making it much more lightweight compared to other javascript based apps!"
					logo={NativeLogo}
			/>

			<FeatureBox
					title="Open Source"
					description="ZenBreak is completely open source. Check the about section below for more infos!"
					logo={OpenSourceLogo}
			/>
		</div>
	</div>
</div>