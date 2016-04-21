/**
 * Game objects
 */

var display,
        input,
        frames,
        spFrame,
        lvFrame,
        alSprite,
        taSprite,
        ciSprite,
        aliens,
        dir,
        tank,
        lives,
        bullets,
        alienBullets,
        cities,
        score = 0,
        firstName,
        lastName,
        email,
        userID,
        interval,
        gameID,
        myHighScore,
        hsFirstName,
        hsLastName,
        hsScore;
//lengths of scores for formatting
var lngMyHighScore,
        lngHsFirstName,
        lngHsLastName,
        lngHsScore;
//canvas dimensions
var canvasHeight,
        canvasWidth;
//initialize user values
firstName = $('#firstName').val();
lastName = $('#lastName').val();
email = $('#email').val();
userID = $('#userID').val();
/**
 * Initiate and start the game
 */
// start and run the game

$(document).ready(function () {
    main();
    function main() {
        // create game canvas and inputhandeler
        display = new Screen(504, 600);
        //set height and width dimensions to variables to find middle of screen for printing
        canvasHeight = display.canvas.height / 2;
        canvasWidth = display.canvas.width / 2;
        input = new InputHandeler();
        // create all sprites frame
        var img = new Image();
        img.addEventListener("load", function () {
            alSprite = [
                [new Sprite(this, 0, 0, 22, 16), new Sprite(this, 0, 16, 22, 16)],
                [new Sprite(this, 22, 0, 16, 16), new Sprite(this, 22, 16, 16, 16)],
                [new Sprite(this, 38, 0, 24, 16), new Sprite(this, 38, 16, 24, 16)]
            ];
            taSprite = new Sprite(this, 62, 0, 22, 16);
            ciSprite = new Sprite(this, 84, 8, 36, 24);
            //assign ID to game 
            gameID = generateUUID();
            // initate and run the game
            init();
            run();
        });
        img.src = "assets/images/invaders.png";
    }
    /**
     * Initate game objects
     */
    function init() {
        //Initialize score
        initScore();
        //Initialize Lives
        lives = 50;
        // set start settings
        frames = 0;
        spFrame = 0;
        lvFrame = 60;
        dir = 1;
        // create the tank object
        tank = {
            sprite: taSprite,
            x: (display.width - taSprite.w) / 2,
            y: display.height - (30 + taSprite.h)
        };
        // initatie bullet array
        bullets = [];
        alienBullets = [];
        // create the cities object (and canvas)
        cities = {
            canvas: null,
            ctx: null,
            y: tank.y - (30 + ciSprite.h),
            h: ciSprite.h,
            /**
             * Create canvas and game graphic context
             */
            init: function () {
                // create canvas and grab 2d context
                this.canvas = document.createElement("canvas");
                this.canvas.width = display.width;
                this.canvas.height = this.h;
                this.ctx = this.canvas.getContext("2d");
                for (var i = 0; i < 4; i++) {
                    this.ctx.drawImage(ciSprite.img, ciSprite.x, ciSprite.y,
                            ciSprite.w, ciSprite.h,
                            68 + 111 * i, 0, ciSprite.w, ciSprite.h);
                }
            },
            /**
             * Create damage effect on city-canvas
             * 
             * @param  {number} x x-coordinate
             * @param  {number} y y-coordinate
             */
            generateDamage: function (x, y) {
                // round x, y position
                x = Math.floor(x / 2) * 2;
                y = Math.floor(y / 2) * 2;
                // draw dagame effect to canva
                this.ctx.clearRect(x - 2, y - 2, 4, 4);
                this.ctx.clearRect(x + 2, y - 4, 2, 4);
                this.ctx.clearRect(x + 4, y, 2, 2);
                this.ctx.clearRect(x + 2, y + 2, 2, 2);
                this.ctx.clearRect(x - 4, y + 2, 2, 2);
                this.ctx.clearRect(x - 6, y, 2, 2);
                this.ctx.clearRect(x - 4, y - 4, 2, 2);
                this.ctx.clearRect(x - 2, y - 6, 2, 2);
            },
            /**
             * Check if pixel at (x, y) is opaque
             * 
             * @param  {number} x x-coordinate
             * @param  {number} y y-coordinate
             * @return {bool}     boolean value if pixel opaque
             */
            hits: function (x, y) {
                // transform y value to local coordinate system
                y -= this.y;
                // get imagedata and check if opaque
                var data = this.ctx.getImageData(x, y, 1, 1);
                if (data.data[3] !== 0) {
                    this.generateDamage(x, y);
                    return true;
                }
                return false;
            }
        };
        cities.init(); // initiate the cities
        // create and populate alien array
        aliens = [];
        var rows = [1, 0, 0, 2, 2];
        for (var i = 0, len = rows.length; i < len; i++) {
            for (var j = 0; j < 10; j++) {
                var a = rows[i];
                // create right offseted alien and push to alien
                // array
                aliens.push({
                    sprite: alSprite[a],
                    x: 30 + j * 30 + [0, 4, 0][a],
                    y: 30 + i * 30,
                    w: alSprite[a][0].w,
                    h: alSprite[a][0].h
                });
            }
        }
    }
    ;
    /**
     * Wrapper around the game loop function, updates and renders
     * the game
     */
    function run() {
        var loop = function () {
            if (checkState()) {
                update();
                render();
                window.requestAnimationFrame(loop, display.canvas);
            }
        };
        window.requestAnimationFrame(loop, display.canvas);
    }
    ;
    /**
     * Update the game logic
     */
    function update() {
        // update the frame count
        frames++;
        // update tank position depending on pressed keys
        if (input.isDown(37)) { // Left
            tank.x -= 4;
        }
        if (input.isDown(39)) { // Right
            tank.x += 4;
        }
        // keep the tank sprite inside of the canvas
        tank.x = Math.max(Math.min(tank.x, display.width - (30 + taSprite.w)), 30);
        // append new bullet to the bullet array if spacebar is
        // pressed
        if (input.isPressed(32)) { // Space
            bullets.push(new Bullet(tank.x + 10, tank.y, -8, 2, 6, "#fff"));
        }
        // update all bullets position and checks
        for (var i = 0, len = bullets.length; i < len; i++) {
            var b = bullets[i];
            b.update();
            // remove bullets outside of the canvas
            if (b.y + b.height < 0 || b.y > display.height) {
                bullets.splice(i, 1);
                i--;
                len--;
                scoreTracker(0);
                continue;
            }

            // check if bullet hits any city
            var h2 = b.height * 0.5; // half hight is used for
            // simplicity
            if (cities.y < b.y + h2 && b.y + h2 < cities.y + cities.h) {
                if (cities.hits(b.x, b.y + h2)) {
                    bullets.splice(i, 1);
                    i--;
                    len--;
                    continue;
                }
            }

            // check if bullet hit any aliens
            for (var j = 0, len2 = aliens.length; j < len2; j++) {
                var a = aliens[j];
                if (AABBIntersect(b.x, b.y, b.width, b.height, a.x, a.y, a.w, a.h)) {
                    aliens.splice(j, 1);
                    j--;
                    len2--;
                    bullets.splice(i, 1);
                    i--;
                    len--;
                    scoreTracker(1);
                    //console.log(score);
                    // increase the movement frequence of the aliens
                    // when there are less of them
                    switch (len2) {
                        case 30:
                        {
                            this.lvFrame = 40;
                            break;
                        }
                        case 10:
                        {
                            this.lvFrame = 20;
                            break;
                        }
                        case 5:
                        {
                            this.lvFrame = 15;
                            break;
                        }
                        case 1:
                        {
                            this.lvFrame = 6;
                            break;
                        }
                    }
                }
            }
        }


        // makes the alien shoot in an random fashion 
        if (Math.random() < 0.03 && aliens.length > 0) {
            var a = aliens[Math.round(Math.random() * (aliens.length - 1))];
            // iterate through aliens and check collision to make
            // sure only shoot from front line
            for (var i = 0, len = aliens.length; i < len; i++) {
                var b = aliens[i];
                if (AABBIntersect(a.x, a.y, a.w, 100, b.x, b.y, b.w, b.h)) {
                    a = b;
                }
            }
            // create and append new bullet
            alienBullets.push(new Bullet(a.x + a.w * 0.5, a.y + a.h, 4, 2, 4, "#fff"));
        }

        // update the aliens at the current movement frequence
        if (frames % lvFrame === 0) {
            spFrame = (spFrame + 1) % 2;
            var _max = 0, _min = display.width;
            // iterate through aliens and update postition
            for (var i = 0, len = aliens.length; i < len; i++) {
                var a = aliens[i];
                a.x += 30 * dir;
                // find min/max values of all aliens for direction
                // change test
                _max = Math.max(_max, a.x + a.w);
                _min = Math.min(_min, a.x);
            }
            // check if aliens should move down and change direction
            if (_max > display.width - 30 || _min < 30) {
                // mirror direction and update position
                dir *= -1;
                for (var i = 0, len = aliens.length; i < len; i++) {
                    aliens[i].x += 30 * dir;
                    aliens[i].y += 30;
                }
            }
        }
        //Check for collisions with alien bullets and tank
        for (var i = 0, len = alienBullets.length; i < len; i++) {
            var b = alienBullets[i];
            b.update();
            // remove bullets outside of the canvas
            if (b.y + b.height < 0 || b.y > display.height) {
                alienBullets.splice(i, 1);
                i--;
                len--;
                continue;
            }
            //check if any alien bullets hit tank
            if (AABBIntersect(b.x, b.y, b.width, b.height, tank.x, tank.y, 22, 16)) {
                console.log("TANK HIT");
                lives--;
                tank = {
                    sprite: taSprite,
                    x: (display.width - taSprite.w) / 2,
                    y: display.height - (30 + taSprite.h)
                };
                continue;
            }
            // check if bullet hits any city
            var h2 = b.height * 0.5; // half hight is used for
            // simplicity
            if (cities.y < b.y + h2 && b.y + h2 < cities.y + cities.h) {
                if (cities.hits(b.x, b.y + h2)) {
                    alienBullets.splice(i, 1);
                    i--;
                    len--;
                    continue;
                }
            }
        }
    }
    ;
//check the game state
    function checkState() {
        //check if ship is hit by alien

        for (var j = 0, len2 = aliens.length; j < len2; j++) {
            var a = aliens[j];
            if (AABBIntersect(tank.x, tank.y, 22, 16, a.x, a.y, a.w, a.h) || a.y > (display.canvas.height - 66)) {
                endGame();
                return false;
            }
        }
        //if there are no aliens left, win game
        if (aliens.length <= 0) {
            winGame();
            return false;
        }
        //if you are out of lives, end game
        if (lives === 0) {
            endGame();
            return false;
        }
        return true;
    }

    function endGame() {
        display.ctx.clearRect(0, 0, display.canvas.width, display.canvas.height);
        //update score
        display.ctx.font = "15px Arial";
        display.ctx.fillStyle = "white";
        display.ctx.fillText("Score: " + score, 5, 15);
        display.ctx.fillText(lastName + ", " + firstName, canvasWidth * 2 - (lastName.length + firstName.length + 125), 15);
        display.ctx.font = "20px Arial";
        display.ctx.fillText("Game Over", canvasWidth - 67, canvasHeight - 50);
        display.ctx.font = "15px Arial";
        display.ctx.fillText("Score: " + score, canvasWidth - 40, canvasHeight + 35);
        display.ctx.fillText("To restart, press Enter", canvasWidth - 85, canvasHeight + 85);
        display.ctx.fillText("Your High Score: " + myHighScore, canvasWidth - (lngMyHighScore + 85), canvasHeight + 125);
        display.ctx.fillText("TOP High Score: " + hsScore + " (" + hsFirstName + " " + hsLastName + ")",
                canvasWidth - (lngMyHighScore + lngHsFirstName + lngHsLastName + 130), canvasHeight + 155);

        $(document).keypress(function (e) {
            var key = e.which;
            if (key === 13) {
                display.ctx.clearRect(0, 0, display.canvas.width, display.canvas.height);
                window.location.reload();
            }
        });
    }

    function winGame() {
        display.ctx.clearRect(0, 0, display.canvas.width, display.canvas.height);
        //update score
        saveFinalScore();

        display.ctx.font = "15px Arial";
        display.ctx.fillStyle = "white";
        display.ctx.fillText("Score: " + score, 5, 15);
        display.ctx.fillText(lastName + ", " + firstName, canvasWidth * 2 - (lastName.length + firstName.length + 125), 15);
        display.ctx.font = "20px Arial";
        display.ctx.fillText("YOU WON!", canvasWidth - 67, canvasHeight - 50);
        display.ctx.font = "15px Arial";        
        display.ctx.fillText("Score: " + score, canvasWidth - 40, canvasHeight + 35);
        display.ctx.fillText("To restart, press Enter", canvasWidth - 85, canvasHeight + 85);
        display.ctx.fillText("Your High Score: " + myHighScore, canvasWidth - (lngMyHighScore + 85), canvasHeight + 125);
        display.ctx.fillText("TOP High Score: " + hsScore + " (" + hsFirstName + " " + hsLastName + ")",
                canvasWidth - (lngMyHighScore + lngHsFirstName + lngHsLastName + 130), canvasHeight + 155);

        $(document).keypress(function (e) {
            var key = e.which;
            if (key === 13) {
                display.ctx.clearRect(0, 0, display.canvas.width, display.canvas.height);
                window.location.reload();
            }
        });
    }
    //initialize score on table at 0
    function initScore() {
        $.post('ws_startscore', {gameID: gameID, userID: userID, score: score},
                function (returnedData) {
                    console.log(returnedData);
                });
        highScores();
    }
    //update current game's score on table
    function scoreTracker(sc) {
        if (sc === 1) {
            score++;
            $.post('ws_savescore', {score: score, won: "false"},
                    function (returnedData) {
                        //console.log(returnedData);
                    });
        } else if (sc === 0 && score !== 0) {
            score--;
            $.post('ws_savescore', {score: score, won: "false"},
                    function (returnedData) {
                        //console.log(returnedData);
                    });
        }

    }
    function saveFinalScore() {
        $.post('ws_savescore', {score: score, won: "true"},
                function (returnedData) {
                    console.log(returnedData);
                });
    }
    //get highscores and save them to variables
    function highScores() {
        $.getJSON('ws_readscores', function (data) {
            hsFirstName = data.FirstName;
            hsLastName = data.LastName;
            hsScore = data.Score;
            myHighScore = data.MyScore;

            if (typeof myHighScore.length === 'undefined') {
                lngMyHighScore = 0;
            } else {
                lngMyHighScore = myHighScore.length;
            }
            lngHsFirstName = hsFirstName.length;
            lngHsLastName = hsLastName.length;
            lngHsScore = hsScore.length;
            console.log(lngMyHighScore);
        });

    }

    /*
     * Javascript UUID generator
     * http://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript
     */
    function generateUUID() {
        var d = new Date().getTime();
        if (window.performance && typeof window.performance.now === "function") {
            d += performance.now(); //use high-precision timer if available
        }
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = (d + Math.random() * 16) % 16 | 0;
            d = Math.floor(d / 16);
            return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
        return uuid;
    }



    /**
     * Render the game state to the canvas
     */
    function render() {

        display.clear(); // clear the game canvas
        // draw all aliens
        for (var i = 0, len = aliens.length; i < len; i++) {
            var a = aliens[i];
            display.drawSprite(a.sprite[spFrame], a.x, a.y);
        }
        // save contetx and draw bullet then restore
        display.ctx.save();
        for (var i = 0, len = bullets.length; i < len; i++) {
            display.drawBullet(bullets[i]);
        }
        for (var i = 0, len = alienBullets.length; i < len; i++) {
            display.drawBullet(alienBullets[i]);
        }
        display.ctx.restore();
        // draw the city graphics to the canvas
        display.ctx.drawImage(cities.canvas, 0, cities.y);
        // draw the tank sprite
        display.drawSprite(tank.sprite, tank.x, tank.y);
        //update score
        display.ctx.font = "15px Arial";
        display.ctx.fillStyle = "white";
        display.ctx.fillText("Score: " + score, 5, 15);
        display.ctx.fillText(lastName + ", " + firstName, display.canvas.width - (lastName.length + firstName.length + 125), 15);
        display.ctx.font = "15px Arial";
        display.ctx.fillText("Lives: " + lives, 5, display.canvas.height - 150);
    }
});

