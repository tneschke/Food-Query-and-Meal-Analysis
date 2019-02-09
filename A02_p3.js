/**
 * A02_p3 - a simple JavaScript file that gets loaded with
 * page "A02_p3.html"
 * 
 * started by Michael Gleicher, January 2019
 * 
 * finished by STUDENT
 */

// we do enable typescript type checking - see
// http://graphics.cs.wisc.edu/WP/cs559-sp2019/typed-js/
// and
// https://github.com/Microsoft/TypeScript/wiki/Type-Checking-JavaScript-Files
// @ts-check

/* Set options for jshint (my preferred linter)
 * disable the warning about using bracket rather than dot
 * even though dot is better 
 * https://stackoverflow.com/questions/13192466/how-to-suppress-variable-is-better-written-in-dot-notation
 */
/* jshint -W069, esversion:6 */

/**
 * Put your code for picture 1 here
 * 
 * Remember to make:
 * - a circle
 * - a triangle
 * - a capsule (two semi-circles with straight lines connecting them)
 * - a sawtooth / mountain
 */
function wb2_pg3_ex1() {
    // use type information to make TypeScript happy
    /** @type {HTMLCanvasElement} */
    let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
let context = canvas.getContext("2d");
    // the student should fill in the rest...
    context.arc(10,5,20,0,0);

    context.arc(50,5,20,0,0);


    context.arc(100,5,20,0,0);

    //I have not yet been able to find out why this doesn't work

    //reading more docs on canvas
}

/**
 * Put your code for picture 2 here
 */
function wb2_pg3_ex2() {
    // use type information to make TypeScript happy
    /** @type {HTMLCanvasElement} */
    let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("canvas2"));

    // the student should fill in the rest...

}

/**
 * you don't need to change this
 */
window.onload = function()
{
    wb2_pg3_ex1();
    wb2_pg3_ex2();
}