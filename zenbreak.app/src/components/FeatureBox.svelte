<script>
    /** @type string **/
    export let title;

    /** @type string **/
    export let description;

    /** @type ConstructorOfATypedSvelteComponent **/
    export let logo;

    /** @type HTMLElement **/
    let element;

    /** @type boolean **/
    let hovered = false;

    $: {
        if (element != null) {
            if (hovered) {
                console.log("Hovered")
            } else {
                console.log("not hovered")
            }
        }
    }
</script>

<div bind:this={element}
     on:mouseenter={() => hovered=true}
     on:mouseleave={() => hovered=false}
     on:mousemove={(e) => console.log(e)}
     role="none"
     class="flex flex-col gap-5 max-w-xs border-neutral-950 border-[1px] rounded-xl p-4 torch-div">
    <svelte:component this={logo} class="grayscale w-16 h-16"/>

    <span class="text-xl font-semibold">{title}</span>

    <span>{description}</span>
</div>