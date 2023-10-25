<script>
    import CarouselArrow from "virtual:icons/ri/arrow-up-s-line";

    /** @type string[] **/
    export let images;

    /** @type number **/
    export let index;

    $: image = images[index]

    function decrement() {
        if (index === 0) {
            index = images.length - 1;
        } else {
            index--;
        }
    }

    function increment() {
        console.log("nice")
        if (index === images.length - 1) {
            index = 0;
        } else {
            index++;
        }
    }
</script>

<div class="relative flex items-center justify-center w-fit">
    <img alt="image {index}"
         class="aspect-auto rounded-2xl select-none max-h-80 border-2 border-neutral-900"
         src="{image}">

    <div class="absolute w-full h-full flex flex-row place-content-between z-10 text-transparent hover:text-gray-400">
        <button on:click={decrement} class="opacity-70 hover:opacity-100">
            <CarouselArrow class="-rotate-90 w-12 h-12" />
        </button>
        <button on:click={increment} class="opacity-70 hover:opacity-100">
            <CarouselArrow class="rotate-90 w-12 h-12" />
        </button>
    </div>
</div>