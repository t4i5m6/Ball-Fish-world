'use strict';

//app to draw polymorphic shapes on canvas
var app;
var intervalID = -1;
const FishImage = new Image(100, 100);
FishImage.src = './fish.png'

/**
 * Create the ball world app for a canvas
 * @param canvas The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");

    /**
     * Draw a circle
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The circl color
     */
    let drawCircle = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    /**
     * Draw a fish
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The fish side length
     * @param angle The circl angle
     */
    let drawFish = function(x, y, length, theta, isFlip) {

        c.save();
        let rad = theta * Math.PI / 180;

        c.translate(x + length / 2, y + length / 2);
        if (isFlip) {
            c.scale(-1, 1);
        }
        c.rotate(rad);
        c.translate(-1 * length / 2, -1 * length / 2)


        c.drawImage(FishImage, 0, 0, length, length);
        c.restore();
    };

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };


    return {
        drawCircle,
        drawFish,
        clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    clear();
    canvasDims();


    $("#btn-action").text($("#actions option:selected").text());
    $("#actions").change(function (){
        $("#btn-action").text($("#actions option:selected").text())
        let action = $("#actions").val()
        $("label[for=behaviors], #behaviors").show();
        $("label[for=switch], #switch").hide();
        $("label[for=interactions], #interactions").hide();

        if (action == "makeBall") {
            $("label[for=interactions], #interactions").show();
            $("label[for=switch], #switch").show();
        }
        if(action == "makeFish") {
            $("label[for=switch], #switch").show();
        }
        else if(action == "clear") {
            $("label[for=behaviors], #behaviors").hide();
        }
    });

    $("#btn-action").click(function(){
        switch( $("#actions").val()){
            case "makeBall":
                loadBall();
                break;
            case "makeFish":
                loadFish();
                break;
            case "switch":
                switchStrategy();
                break;
            case "remove":
                removeStrategy();
                break;
            case "clear":
                clear();
                break;
            default:
                break;
        }
    });

    intervalID = setInterval(updateBallWorld, 100);

};

/**
 * load fish at a location on the canvas
 */
function loadFish() {
    let values = $("#behaviors").val();
    let switchOrNot = $("#switch").val();

    $.post("/load/fish", { strategies: values , switchOrNot: switchOrNot}, function (data) {
        app.drawFish(data.loc.x, data.loc.y, data.sideLength, data.theta, data.isFlip)
    }, "json");
}

/**
 * load ball at a location on the canvas
 */
function loadBall() {
    let values = $("#behaviors").val() + ' ' + $("#interactions").val();
    let switchOrNot = $("#switch").val();

    $.post("/load/ball", { strategies: values , switchOrNot: switchOrNot}, function (data) {
        app.drawCircle(data.loc.x, data.loc.y, data.radius, data.color)
    }, "json");
}

/**
 * Switch ball strategies
 */
function switchStrategy() {
    let values = $("#behaviors").val();

    $.post("/switch", { strategies: values}, function (data) {
        drawByData(data)
    }, "json");
}

function removeStrategy() {
    let values = $("#behaviors").val();

    $.get("/remove/" + values, function (data) {
        drawByData(data)
    }, "json");
}

function drawByData(data) {
    for (const d of data) {
        if (d.type === "ball") {
            app.drawCircle(d.loc.x, d.loc.y, d.radius, d.color)
        }
        else if (d.type === "fish") {
            app.drawFish(d.loc.x, d.loc.y, d.sideLength, d.theta, d.isFlip)
        }
    }
}

function updateBallWorld() {
    $.get("/update", function(data) {
        app.clear()
        drawByData(data)
    }, "json");
}

/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
}