/**
 *
 * @returns {string} the os: macos | windows | linux | android | iphone | ipad | unknown
 */
export function getOS() {
    if (navigator.userAgent.toLowerCase().includes("mac"))
        return "macos";
    if (navigator.userAgent.toLowerCase().includes("win"))
        return "windows";
    if (navigator.userAgent.toLowerCase().includes("x11"))
        return "linux";
    if (navigator.userAgent.toLowerCase().includes("android"))
        return "android";
    if (navigator.userAgent.toLowerCase().includes("iphone"))
        return "iphone";
    if (navigator.userAgent.toLowerCase().includes("ipad"))
        return "ipad";
    if (navigator.userAgent.toLowerCase().includes("linux"))
        return "linux";

    return "unknown";
}