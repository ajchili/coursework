const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');

// Size of game piece
const pieceSize = 10;
// Size of game board (used as width and height)
const size = 500 / pieceSize;

/**
 * Food object that is the main objective for snake.
 * 
 * @param {any} x Integer value of x position of food.
 * @param {any} y Integer value of y position of food.
 */
function Food(x, y) {
	this.x = x,
	this.y = y,
	/**
	 * Returns object that contains x any y cords
	 * of Food object.
	 */
	this.cords = () => {
		return {
			x: this.x,
			y: this.y,
		};
		},
	/**
	 * Draws food object on game canvas.
	 */
	this.draw = () => {
		ctx.fillStyle = '#ffffff';
		ctx.fillRect(x * pieceSize, y * pieceSize, pieceSize, pieceSize);
	}
};

/**
 * Snake object that is the player.
 */
function Snake() {
	/* Directio snake moves in
	 * 0 - Up
	 * 1 - Right
	 * 2 - Down
	 * 3 - Left
	 */
	this.direction = 0,
	this.hasChangedDirection = false,
	this.body = [
		{ x: size / 2, y: size / 2 },
		{ x: size / 2, y: size / 2 + 1 },
		{ x: size / 2, y: size / 2 + 2 },
		],
	/**
	 * Draws snake object on game canvas.
	 */
	this.draw = () => {
		this.hasChangedDirection = false;
		this.body.forEach((piece, i) => {
			if (i === 0) ctx.fillStyle = '#adffad';
			else ctx.fillStyle = '#00ff00';
			ctx.fillRect(piece.x * pieceSize, piece.y * pieceSize, pieceSize, pieceSize);
		});
		},
	/**
	 * Changes direction of snake if direction is not direct
	 * opposite direction of current direction.
	 * 
	 * @param {any} direction Integer between 0 and 3 (inclusive).
	 */
	this.changeDirection = direction => {
		if (direction >= 0 && direction <= 3) {
			if (this.direction === 0 && direction === 2) return;
			else if (this.direction === 2 && direction === 0) return;
			else if (this.direction === 1 && direction === 3) return;
			else if (this.direction === 3 && direction === 1) return;
			else if (this.hasChangedDirection === true) return;
			this.hasChangedDirection = true;
			this.direction = direction;
		} else this.direction = 0;
		},
	/**
	 * Moves snake by one piece iteratively.
	 */
	this.move = () => {
		let reverse = this.body.slice().reverse();

		reverse.forEach((piece, i) => {
			if (i === this.body.length - 1) {
				switch (this.direction) {
					case 1:
						piece.x = piece.x + 1;
						break;
					case 2:
						piece.y = piece.y + 1;
						break;
					case 3:
						piece.x = piece.x - 1;
						break;
					default:
						piece.y = piece.y - 1;
						break;
				}
			} else {
				let position = this.body.length - i - 1;
				piece.x = this.body[position - 1].x;
				piece.y = this.body[position - 1].y;
			}
		});
		},
	/**
	 * Determines if snake can eat the provided food object.
	 * 
	 * @param {any} food Food object of game.
	 */
	this.eat = food => {
		let head = this.body[0];
		return head.x === food.x && head.y === food.y;
		},
	/**
	 * Grows snake by one piece, piece is added to end of snake.
	 */
	this.grow = () => {
		let tail = this.body[this.body.length - 1];
		switch (this.direction) {
			case 1:
				this.body.push({ x: tail.x - 1, y: tail.y });
				break;
			case 2:
				this.body.push({ x: tail.x, y: tail.y - 1 });
				break;
			case 3:
				this.body.push({ x: tail.x + 1, y: tail.y });
				break;
			default:
				this.body.push({ x: tail.x, y: tail.y + 1 });
				break;
		}
		},
	/**
	 * Determines if snake has hit the edge of the game canvas or run into itself.
	 * Returns true if either have occurred, indicating that the snake has died.
	 */
	this.didDie = () => {
		let head = this.body[0];
		let isDead = false;

		if (head.x < 0 || head.x > 49 || head.y < 0 || head.y > 49) return true;
		this.body.forEach((piece, i) => {
			if (i !== 0 && piece.x === head.x && piece.y === head.y) isDead = true;
		});

		return isDead;
	}
};

/* States
 * -1 - Game not started
 *  0 - Player died
 *  1 - Game is running
 */
let state = -1;
let score = 0;
let snake = null;
let food = null;

/**
 * Starts game and sets all game variables to new values.
 */
const startGame = () => {
	food = new Food(
		Math.floor(Math.random() * size),
		Math.floor(Math.random() * size));
	snake = new Snake();
	score = 0; 
	state = 1;
};

/**
 * Draws text in top left corner of game screen.
 * 
 * @param {any} text Array of strings to be drawn.
 */
const drawText = text => {
	ctx.fillStyle = '#ffffff';
	ctx.font = '30px Arial';
	text.forEach((text, i) => {
		ctx.fillText(text, 10, 30 * (i + 1));
	});
};

/**
 * Determines if user is pressing key and performs action based
 * on pressed key.
 */
const handleUserInput = () => {
	window.onkeypress = e => {
		let key = e.keyCode ? e.keyCode : e.which;

		if (key === 32) {
			// Starts game if it is not already running
			if (state < 1) {
				startGame();
			}
		} else {
			if (state === 1) {
				switch (key) {
					// W
					case 119:
						snake.changeDirection(0);
						break;
					// D
					case 100:
						snake.changeDirection(1);
						break;
					// S
					case 115:
						snake.changeDirection(2);
						break;
					// A
					case 97:
						snake.changeDirection(3);
						break;
					default:
						break;
				}
			}
		}
	};
};

/**
 * Renders game as current state.
 */
const render = () => {
	food.draw();
	snake.draw();
};

/**
 * Updates game by calculating movement, snake actions and
 * if player has died.
 */
const update = () => {
	snake.move();
	if (snake.eat(food)) {
		food = new Food(
			Math.floor(Math.random() * size),
			Math.floor(Math.random() * size));
		score += 1;
		snake.grow();
	} else if (snake.didDie()) {
		state = 0;
	}
};

/**
 * Runs single game step based on current game state.
 */
const gameStep = () => {
	switch (state) {
		case 0:
			render();
			drawText([
				`Score: ${score}`,
				'You have died.',
				'Press space to restart.'
			]);
			break;
		case 1:
			update();
			render();
			drawText([`Score: ${score}`]);
			break;
		default:
			drawText([
				'Use W/A/S/D to move.',
				'Press space to start playing.'
			]);
			break;
	};
};

// Game loop, executed 5 times per second
setInterval(() => {
	ctx.fillStyle = '#000000';
	ctx.fillRect(0, 0, 500, 500);
	
	handleUserInput();
	gameStep();
}, 1000 / 5);