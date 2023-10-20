<script>
    import CarouselIcon from "./CarouselIcon.svelte";

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
         class="aspect-auto rounded-2xl select-none max-h-80"
         src="{image}">

    <div class="absolute w-full h-full flex flex-row place-content-between p-2 z-10">
        <button on:click={decrement} class="hover:opacity-80">
            <CarouselIcon rotated={true} />
        </button>
        <button on:click={increment} class="hover:opacity-80">
            <CarouselIcon rotated={false} />
        </button>
    </div>
</div>