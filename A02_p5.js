/**
 * A02_p5 - a simple JavaScript file that gets loaded with
 * page "A02_p5.html"
 * 
 * started by Michael Gleicher, January 2019
 * 
 * but filled in by STUDENT
 * 
 * Note: the student code should go into the functions
 * wb2_pg5_ex1 and wb2_pg5_ex2
 * 
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
 * Function for the STUDENT to do exercise 1
 */
function wb2_pg5_ex1() 
{
       
        let circles = [];

        function drawCircles(context,circles) {
                circles.forEach(function(circle){
                        context.save();
                        if(circle.over){
                                context.fillStyle = "pink";
                                context.strokeStyle = "purple";
                        }
                        else{
                                context.fillStyle = "blue";
                                context.strokeStyle = "green"; 
                        }
                        
                        context.beginPath();
                        context.arc(circle.x,circle.y,20,0,2*Math.PI);
                        context.stroke(); 
                        context.fill();
                        context.closePath();
                    
                        context.restore();
                });
            }
        
        function mouseOver(x,y,circles) {
                circles.forEach(function(circle) {
                    // see if xy is inside of rect
                    if (((x - circle.x)*(x - circle.x) + (y - circle.y)*(y - circle.y)) < ((circle.r)*(circle.r))){
                        circle.over = true;
                    }
                    else{
                            circle.over = false;
                    }
                });
            }
        
        document.getElementById("ex1canvas").onclick = function(event) {
                let x = event.clientX;
                let y = event.clientY;

                let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
                x -= box.left;
                y -= box.top;
                circles.push({"x":x,"y":y,"r":20,"h":10,color:"#888"});
        };
        

        document.getElementById("ex1canvas").onmousemove = function(event1) {
                let x = event1.clientX;
                let y = event1.clientY;

                let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
                x -= box.left;
                y -= box.top;
                mouseOver(x, y, circles);
        };

        function animate() {
                /** @type {HTMLCanvasElement} */
                let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("ex1canvas"));
                let context = canvas.getContext('2d');

                // clear the canvas
                context.clearRect(0,0,canvas.width,canvas.height);
                
                drawCircles(context,circles);
                // loop
                window.requestAnimationFrame(animate);
        }
        animate();

}

/**
 * Function for the STUDENT to do exercise 1
 */
function wb2_pg5_ex2()
{
        
        let fireworks = [];
        let circles = [];

        function drawCircles(context,circles) {
                circles.forEach(function(circle){

                        context.save();

                        context.beginPath();
                        context.arc(circle.x,circle.y,20,0,2*Math.PI);
                        context.stroke(); 
                        context.fill();
                        context.closePath();
                        
                        if(circle.x - circle.x2 <= 3 ){
                                circles.splice(circles.indexOf(circle),1);
                                for(var i =0; i <9; i++) fireworks.push({"x":circle.x,"y":circle.y,"w":20,"h":20,"color":"black"});
                        }
                        circle.y -= (circle.y - circle.y2)/6
                        circle.x -= (circle.x - circle.x2)/6
                        context.restore();
                });
                }

        function drawFireworks(context,fireworks) {
                fireworks.forEach(function(firework){                   
                        context.save();
                                
                        
                        context.beginPath();
                        context.fillRect(firework.x,firework.y,firework.w,firework.h) 
                        context.fill();
                        context.closePath();
                        if(firework.x > 500 || firework.y > 500 || firework.x < 0 || firework.y < 0){
                                fireworks.splice(fireworks.indexOf(firework),1);
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.x += 5;
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.x += 4;
                                firework.y += 4;
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.y +=5;
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.x -= 4;
                                firework.y += 4;

                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.x -= 5;
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.x -= 4;
                                firework.y -= 4;
                        }
                        if (fireworks.indexOf()%8 == 0){
                                firework.y +=5;

                        }
                        if (fireworks.indexOf()%8 == 0){
                                
                                firework.x += 4;
                                firework.y -= 4;
                        }

                        context.restore();
                });
                }



                document.getElementById("ex2canvas").onclick = function(event) {
                        let x = event.clientX;
                        let y = event.clientY;
                        console.log("FFF");
                        let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
                        x -= box.left;
                        y -= box.top;
                        
                        circles.push({"x":Math.random()*400 ,"y":400,"r":20,"h":10,color:"#888","x2":x,"y2":y});
                        
                };

        function animate() {
                /** @type {HTMLCanvasElement} */
                let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("ex2canvas"));
                let context = canvas.getContext('2d');

                // clear the canvas
                context.clearRect(0,0,canvas.width,canvas.height);
                
                drawCircles(context,circles);
                drawFireworks(context,fireworks);

                // loop
                window.requestAnimationFrame(animate);
        }
        animate();
}

/**
 * Function to run the student's code
 */
window.onload = function() {
    wb2_pg5_ex1();
    wb2_pg5_ex2();
};