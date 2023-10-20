<script>
    import SvelteMarkdown from "svelte-markdown";
    /** @type string **/
    import source from "$lib/markdown/terms.md?raw";
</script>

<div class="h-auto w-full flex justify-center items-center py-12 px-4">
    <div class="prose">
        <SvelteMarkdown {source} />
    </div>
</div>