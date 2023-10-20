/**
 *
 * @returns {string} the os shortname: mac | win | lin | unknown
 */
export function getOS() {
    if (navigator.userAgent.includes("Mac"))
        return "mac";
    if (navigator.userAgent.includes("Win"))
        return "win";
    if (navigator.userAgent.includes("X11"))
        return "lin";
    if (navigator.userAgent.includes("Linux"))
        return "lin";

    return "unknown";
}