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
    context.fillStyle = "pink";
    context.strokeStyle = "purple";
    context.lineWidth = 10;
    context.beginPath();
    context.arc(70,50,20,0,2*Math.PI);
    context.stroke(); 
    context.fill();
    context.closePath();

    context.fillStyle = "pink";
    context.strokeStyle = "darkred";
    context.beginPath();
    context.arc(200,50,20,Math.PI/2,3*Math.PI/2);
    context.lineTo(250,30);
    context.arc(250,50,20,3*Math.PI/2,Math.PI/2);
    context.lineTo(200,70);
    context.stroke(); 
    context.fill();
    

    context.fillStyle = "yellow";
    context.strokeStyle = "orange";
    context.beginPath();
    context.lineTo(75,110);
    context.lineTo(100,150);
    context.lineTo(50,150);
    context.lineTo(75,110);
    context.lineTo(100,150);
    context.stroke(); 
    context.fill();
    
    context.fillStyle = "grey";
    context.strokeStyle = "black";
    context.beginPath();
    context.lineTo(200,120);
    context.lineTo(225,150);
    context.lineTo(250,120);
    context.lineTo(275,150);
    context.lineTo(275,170);
    context.lineTo(175,170);
    context.lineTo(175,150);
    context.lineTo(200,120);
    context.lineTo(225,150);

    context.stroke(); 
    context.fill();

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
    let x= 0;
    let y = 0;
    let context = canvas.getContext("2d");
    context.lineWidth = 1;

    context.beginPath();
    context.lineTo(0,500)
    context.lineTo(0,500)
    let colors = ["red","orange","green","blue","yellow","purple"]
    for(var i=0; i < 256; i++){
       context.strokeStyle = colors[i%6];
       console.log(colors[i%6]);
        context.lineTo(x,y);    
        context.moveTo(0,500)
        x +=3;
        y +=3;
        context.stroke();
    }

    x=0;
    y=0;
    context.moveTo(500,0)
    for(var i=0; i < 256; i++){
        context.strokeStyle = colors[i%6];
        console.log(colors[i%6]);
         context.lineTo(x,y);    
         context.moveTo(500,0)
         x +=3;
         y +=3;
         context.stroke();
     }


     for(var i=0; i < 500; i++){
        context.fillStyle = "red";
        context.lineWidth = 10;
        context.beginPath();
        context.arc(i%20 * 25 ,Math.floor(i/20) * 20,10,0,2*Math.PI);
        console.log(i/25);
        context.stroke(); 
        context.fill();
        context.closePath();
        context.globalAlpha -= (1/400);
     }
     context.globalAlpha = 1;

     for(var i=0; i < 500; i++){
        context.fillStyle = "green";
        context.lineWidth = 10;
        context.beginPath();
        context.arc(i%20 * 25 ,500 - (Math.floor(i/20) * 20),10,0,2*Math.PI);
        console.log(i/25);
        context.stroke(); 
        context.fill();
        context.closePath();
        context.globalAlpha -= (1/400);
     }

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