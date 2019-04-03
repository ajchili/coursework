let canvas = document.getElementById("snakeCanvas");
let context = canvas.getContext("2d");

const CANVAS_SIZE = 500;
const PIECE_SIZE = 10;
const BOARD_SIZE = CANVAS_SIZE / PIECE_SIZE;
const WIDTH = BOARD_SIZE;
const HEIGHT = BOARD_SIZE;
const DIRECTION = {
  UP: 0,
  DOWN: 1,
  LEFT: 2,
  RIGHT: 3
};

class Piece {
  x;
  y;
  color;
  constructor(x, y, color = "#FFFFFF") {
    this.x = x;
    this.y = y;
    this.color = color;
  }
  draw() {
    context.fillStyle = this.color;
    context.fillRect(
      this.x * PIECE_SIZE,
      this.y * PIECE_SIZE,
      PIECE_SIZE,
      PIECE_SIZE
    );
  }
}

class Food extends Piece {
  constructor() {
    super(
      Math.floor(Math.random() * WIDTH),
      Math.floor(Math.random() * HEIGHT)
    );
  }
}

class BodyPart extends Piece {
  constructor(x, y) {
    super(x, y, "#00FF00");
  }
}

class Snake {
  body = [];
  direction = DIRECTION.UP;
  constructor() {
    this.body.push(new BodyPart(WIDTH / 2, HEIGHT / 2));
    this.body.push(new BodyPart(WIDTH / 2, HEIGHT / 2 + 1));
    this.body.push(new BodyPart(WIDTH / 2, HEIGHT / 2 + 2));
  }
  get head() {
    return this.body[0];
  }
  get tail() {
    return this.body[this.body.length - 1];
  }
  set direction(direction) {
    this.direction = direction;
  }
  draw() {
    this.body.forEach(bodyPart => bodyPart.draw());
  }
  move() {
    let reverse = this.body.slice().reverse();
    reverse.forEach((piece, i) => {
      if (i === this.body.length - 1) {
        switch (this.direction) {
          case DIRECTION.UP:
            piece.y--;
            break;
          case DIRECTION.DOWN:
            piece.y++;
            break;
          case DIRECTION.LEFT:
            piece.x--;
            break;
          case DIRECTION.RIGHT:
            piece.x++;
            break;
        }
      } else {
        let position = this.body.length - i - 1;
        piece.x = this.body[position - 1].x;
        piece.y = this.body[position - 1].y;
      }
    });
  }
  grow() {
    let tail = this.body[this.body.length - 1];
    switch (this.direction) {
      case DIRECTION.UP:
        this.body.push(new BodyPart(tail.x, tail.y + 1));
        break;
      case DIRECTION.DOWN:
        this.body.push(new BodyPart(tail.x, tail.y - 1));
        break;
      case DIRECTION.LEFT:
        this.body.push(new BodyPart(tail.x + 1, tail.y));
        break;
      case DIRECTION.RIGHT:
        this.body.push(new BodyPart(tail.x - 1, tail.y));
        break;
    }
  }
  hasDied() {
    let head = this.body[0];
    if (head.x < 0 || head.x > WIDTH - 1 || head.y < 0 || head.y > HEIGHT - 1)
      return true;
    let body = this.body.slice(1);
    for (let i = 0; i < body.length; i++) {
      let piece = body[i];
      if (piece.x === head.x && piece.y === head.y) return true;
    }
    return false;
  }
}

class Board {
  snake;
  food;
  score = 0;
  running = true;
  gameTick;
  constructor() {
    this.snake = new Snake();
    this.food = new Food();
    this.gameTick = setInterval(() => this.update(), 1000 / 3); // 3 ticks per second
    this.setup();
  }
  setup() {
    window.onkeypress = e => {
      let key = e.keyCode ? e.keyCode : e.which;
      if (this.running) {
        switch (key) {
          // W
          case 119:
            if (this.snake.direction !== DIRECTION.DOWN)
              this.snake.direction = DIRECTION.UP;
            break;
          // D
          case 100:
            if (this.snake.direction !== DIRECTION.LEFT)
              this.snake.direction = DIRECTION.RIGHT;
            break;
          // S
          case 115:
            if (this.snake.direction !== DIRECTION.UP)
              this.snake.direction = DIRECTION.DOWN;
            break;
          // A
          case 97:
            if (this.snake.direction !== DIRECTION.RIGHT)
              this.snake.direction = DIRECTION.LEFT;
            break;
          default:
            break;
        }
      }
    };
  }
  draw() {
    context.fillStyle = "#000000";
    context.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
    this.snake.draw();
    this.food.draw();
  }
  canSnakeEat() {
    return (
      this.snake.head.x === this.food.x && this.snake.head.y === this.food.y
    );
  }
  update() {
    if (this.snake.hasDied()) {
      this.running = false;
      clearInterval(this.gameTick);
      return this.handleEndGame();
    }
    this.snake.move();
    if (this.canSnakeEat()) {
      this.score++;
      this.food = new Food();
      this.snake.grow();
    }
    this.draw();
  }
  handleEndGame() {
    alert(`You lost!\nYour score: ${this.score}\n\nRefresh to play again!`);
  }
}

new Board();
