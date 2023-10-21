<script>
    /** @type string **/
    export let title;

    /** @type string **/
    export let description;

    /** @type ConstructorOfATypedSvelteComponent **/
    export let logo;


    function onMouseMove(e) {
        const { currentTarget: target } = e;

        if (target !== null) {
            const rect = target.getBoundingClientRect(),
                x = e.clientX - rect.left,
                y = e.clientY - rect.top;

            target.style.setProperty("--mouse-x", `${x}px`);
            target.style.setProperty("--mouse-y", `${y}px`);
        }
    }
</script>

<style>
    .feature-box {
        position: relative;
    }

    .feature-box::before {
        background: radial-gradient(
            800px circle at var(--mouse-x) var(--mouse-y),
            rgba(252, 104, 12, 0.6),
            transparent 30%
        );
        border-radius: inherit;
        content: "";
        height: 100%;
        width: 100%;
        position: absolute;
        left: 0;
        top: 0;
        z-index: 2;
        opacity: 0;
        transition: opacity 500ms;
    }

    .feature-box:hover::before {
        opacity: 1;
    }
</style>

<div
     on:mousemove={(e) => onMouseMove(e)}
     role="none"
     class="feature-box cursor-pointer flex flex-col gap-5 max-w-xs h-72 border-neutral-950 border-[1px] rounded-xl p-4">
    <svelte:component this={logo} class="grayscale w-16 h-16 z-10"/>

    <span class="text-xl font-semibold z-10">{title}</span>

    <span class="z-10">{description}</span>
</div>