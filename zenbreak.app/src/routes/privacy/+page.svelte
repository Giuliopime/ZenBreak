<script>
    import SvelteMarkdown from "svelte-markdown";
    /** @type string **/
    import source from "$lib/markdown/privacy.md?raw";
</script>



<div class="h-auto w-full flex justify-center items-center my-12">
    <div class="prose">
        <SvelteMarkdown {source} />
    </div>
</div>