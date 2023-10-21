<script>
    import pfp from '$lib/images/pfp.jpeg';
    import { GiuliopimeGitHub, ZenBreakGitHub } from "$lib/links.js";
</script>

<footer class="w-full flex flex-col items-center justify-center gap-8 bg-[#27282c] text-white py-12 px-12 md:px-24 text-sm">
    <div class="flex flex-col md:flex-row items-center justify-center gap-8">
        <img src={pfp}
             alt="Giuliopime"
            class="w-48 h-48 rounded-full" />

        <div class="flex flex-col gap-2 items-start max-w-xl">
            <span class="text-xl">Developed by <a href={GiuliopimeGitHub} target="_blank">Giuliopime</a> with ❤️</span>
            <span class="text-neutral-300">
                I've created this project for the
                <a href="https://blog.jetbrains.com/kotlin/2022/10/join-the-kotlin-multiplatform-contest/" target="_blank">
                    Jetbrains Kotlin Multiplatform contest!
                </a>
                <br>
                Building it has been an amazing journey, and I've made it completely free and open source so that it can help others!
                <br>
                <span class="text-neutral-400">
                    (This has been heavily inspired by <a href="https://breaktimer.app" target="_blank">BreakTimer</a> ^^)
                </span>
            </span>
            <a href={ZenBreakGitHub} target="_blank" class="mt-1">
                <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/Giuliopime/ZenBreak?logo=github&logoColor=%23000000&label=Star&labelColor=%23ebf0f4&color=%23ffffff">
            </a>
        </div>
    </div>
    <hr class="w-full border-neutral-500">
    <div class="flex gap-4 items-start w-full text-neutral-300 flex-wrap">
        <a href="privacy">Privacy & Security</a>
        <a href="terms">Terms of Use</a>
        <a href="https://discord.gg/MunKMgKAJp">Contact</a>
    </div>

    <div class="flex flex-col md:flex-row gap-4 md:gap-0 justify-between w-full text-neutral-500">
        <span>
            Both the ZenBreak application and website are licensed under the GNU General Public License v3.0.
        </span>

        <span>
            Developed with drive and <a href="https://www.jetbrains.com/idea/" target="_blank" class="text-neutral-400">IntelliJ IDEA</a>
        </span>
    </div>
</footer>