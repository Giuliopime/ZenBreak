<script>
    /** @type string[] **/
    export let platforms;

    /** @type number **/
    export let selectedIndex;

    /** @type HTMLElement[] **/
    let elements = [];

    let paddingX = 0;

    $: {
        let platform = platforms[selectedIndex];

        if (platform !== undefined) {
            let tempPadding = 0;
            elements.forEach((e, i) => {
                if (i < selectedIndex) {
                    tempPadding += e.offsetWidth
                }
            })

            paddingX = tempPadding
        }
    }
</script>

<div class="relative">
    <div class="flex border-2 border-secondary rounded-full -z-100">
        {#each platforms as p, i (i)}
            <button class="rounded-full hover:bg-secondary px-4 py-2 cursor-pointer"
                    on:click={() => { selectedIndex = i }}
                    bind:this={elements[i]}>
                {p}
            </button>
        {/each}
    </div>

    <button
            style={"left: " + paddingX + "px; transition: left 0.3s;"}
            class="absolute border-primary border-2 top-0 z-10 rounded-full px-4 py-2 text-transparent cursor-default">
        {platforms[selectedIndex]}
    </button>
</div>