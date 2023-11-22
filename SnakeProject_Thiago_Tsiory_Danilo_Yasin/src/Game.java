import javax.swing.*;
import java.awt.*; //Used to ba able to work with graphical interfaces.
import java.awt.event.KeyEvent; //Used to detect when a button from the keyboard is used.
import java.awt.event.KeyListener; //Used to capture events from the keyboard.
import java.awt.image.BufferStrategy;//Used to be able to render the board during the execution of the application.
import java.util.Scanner;

public class Game extends Canvas implements Runnable, KeyListener {
// Extend to Canvas is necessary to be able to draw and render the graphical elements of the application.
// By implementing Runnable is possible to use the method run(), which executes the application in a loop allowing to update and render the app.
// Implementing KeyListener is necessary to capture que events of the keyboard.

    public Snake[] snake = new Snake[10];
    public Apple apple;
    private String playerName;
    private JFrame frame;
    public int score = 0;
    public int speed = 1;
    public int maxWidth = 450;
    public int maxHeight = 300;
    public boolean left, right, up, down;
    private Font scoreFont = new Font("Arial", Font.BOLD, 24); // Display the score on the screen.


    public Game(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(maxWidth, maxHeight));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert your name:  ");
        playerName = scanner.nextLine();
        scanner.close();

        for (int i = 0; i < snake.length; i++) { //Create the snake, starting from the index 0.
            snake[i] = new Snake(225, 150);
        }
        apple = new Apple(maxWidth, maxHeight); //Create the apple.

        this.addKeyListener(this); //To be able to move the snake.
    }
    public JFrame getFrame() {
        return frame;
    }

    public void tick() {

//        //This code makes the snake come back to the screen if it leaves it.
//        if (snake[0].x + 10 < 0) {
//            snake[0].x = maxWidth;
//        } else if (snake[0].x >= maxWidth) {
//            snake[0].x = -10;
//        }
//
//        if (snake[0].y + 10 < 0) {
//            snake[0].y = maxHeight;
//        } else if (snake[0].y >= maxHeight) {
//            snake[0].y = -10;
//        }

        //Code to move the snake's head. The .x and .y are the coordinates, they are increased by the speed to move to any direction.
        if (right) {
            snake[0].x += speed;
        } else if (up) {
            snake[0].y -= speed;
        } else if (left) {
            snake[0].x -= speed;
        } else if (down) {
            snake[0].y += speed;
        }

        //This loop makes the snakes body follow the head. The -1 after .length means the head.
        for (int i = snake.length - 1; i > 0; i--) {
            snake[i].x = snake[i - 1].x;
            snake[i].y = snake[i - 1].y;
        }

        //The rectangle is used to be able to know if the snake's head encounter the apple.
        if (new Rectangle(snake[0].x, snake[0].y, 10, 10).intersects(new Rectangle(apple.getX(), apple.getY(), 10, 10))) {
            apple.generateRandomPosition(); //Generate new apple.

            //Updating the size of the snake.
            Snake newTail = new Snake(snake[snake.length - 1].x, snake[snake.length - 1].y); //Create new tail.
            Snake[] newSnake = new Snake[snake.length + 1]; //Add the tail to the snake.
            for (int i = 0; i < snake.length; i++) {
                newSnake[i] = snake[i];
            }
            newSnake[snake.length] = newTail;
            snake = newSnake; // Update the snake

            score++; //If the snake eats the apple, the score and
            speed++; //the speed are increased.
            System.out.println("Score: " + score); //The new score is going to be printed on the console.
        }




        if (snake[0].x + 10 < 0 || snake[0].x >= maxWidth || snake[0].y + 10 < 0 || snake[0].y >= maxHeight) {
            // Se a cabeça da cobra atingir as bordas da tela, o jogador perde o jogo
            System.out.println("Game Over");
            System.exit(0); // Encerrar o jogo ou realizar outra ação de fim de jogo
        }

    }

    public void render() {
        //This part verify if the bf was initialized. If no, it initializes it with three frames to provide a better experience and finish.
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        //Here the board, snake and apple are created using the methods from BufferStrategy.
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.green);
        g.fillRect(0, 0, maxWidth, maxHeight);

        for (int i = 0; i < snake.length; i++) {
            g.setColor(Color.darkGray);
            g.fillRect(snake[i].x, snake[i].y, 10, 10);
        }

        g.setColor(Color.red);
        g.fillRect(apple.getX(), apple.getY(), 10, 10);

        //SCORE-SNAKE SIZE ON THE FRAME:
        frame.setTitle("Snake | Player: " + playerName + " - Score: " + score + " Snake size: " + snake.length);


        g.dispose();

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        while (true) { //Create an infinity loop to keep running the application.
            tick();
            render();
            try { //To captuer any exception.
                Thread.sleep(1000 / 60); //Make that tha application pause for a short period in order not to use all the CPu power.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
            down = false;
            left = false;
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
            right = false;
            down = false;
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
            right = false;
            left = false;
            down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
            right = false;
            left = false;
            up = false;
        }
    }//To move the snake. Turning the other direction false, you do not need to keep pressing the button.

    @Override
    public void keyReleased(KeyEvent e) {
    }
}


//import java.awt.event.KeyListener;
//import java.util.LinkedList;
//import java.awt.Rectangle;
//import java.awt.Color;
//import java.util.Scanner;
//import java.awt.event.KeyEvent; //Used to detect when a button from the keyboard is used.
//import java.awt.event.KeyListener; //Used to capture events from the keyboard.
//
//public class Game implements KeyListener {
//    public Snake[] snake = new Snake[10];
//    public Apple apple;
//    private String playerName;
//    public int score = 0;
//    public int speed = 1;
//    public int maxWidth = 60;
//    public int maxHeight = 20;
//    public boolean left, right, up, down;
//    private boolean isGameOver = false;
//
//    public Game() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Insert your name: ");
//        playerName = scanner.nextLine();
//
//        for (int i = 0; i < snake.length; i++) {
//            snake[i] = new Snake(30, 10);
//        }
//        apple = new Apple(maxWidth, maxHeight);
//    }
//
//    public void tick() {
//        if (right) {
//            snake[0].x += speed;
//        } else if (up) {
//            snake[0].y -= speed;
//        } else if (left) {
//            snake[0].x -= speed;
//        } else if (down) {
//            snake[0].y += speed;
//        }
//
//        for (int i = snake.length - 1; i > 0; i--) {
//            snake[i].x = snake[i - 1].x;
//            snake[i].y = snake[i - 1].y;
//        }
//
//        if (new Rectangle(snake[0].x, snake[0].y, 10, 10).intersects(new Rectangle(apple.getX(), apple.getY(), 10, 10))) {
//            apple.generateRandomPosition();
//            Snake newTail = new Snake(snake[snake.length - 1].x, snake[snake.length - 1].y);
//            Snake[] newSnake = new Snake[snake.length + 1];
//            for (int i = 0; i < snake.length; i++) {
//                newSnake[i] = snake[i];
//            }
//            newSnake[snake.length] = newTail;
//            snake = newSnake;
//
//            score++;
//            speed++;
//            System.out.println("Score: " + score);
//        }
//
//        if (snake[0].x + 10 < 0 || snake[0].x >= maxWidth || snake[0].y + 10 < 0 || snake[0].y >= maxHeight) {
//            System.out.println("Game Over");
//            isGameOver = true;
//        }
//    }
//
//    public void render() {
//        for (int y = 0; y < maxHeight; y++) {
//            for (int x = 0; x < maxWidth; x++) {
//                char cell = ' ';
//                for (Snake s : snake) {
//                    if (s.x == x && s.y == y) {
//                        cell = 'O';
//                    }
//                }
//                if (apple.getX() == x && apple.getY() == y) {
//                    cell = 'A';
//                }
//                System.out.print(cell);
//            }
//            System.out.println();
//        }
//
//        System.out.println("Snake | Player: " + playerName + " - Score: " + score + " Snake size: " + snake.length);
//    }
//
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (!isGameOver) {
//            tick();
//            render();
//            System.out.println("Enter a direction (w=up, d=right, s=down, a=left):");
//            char direction = scanner.next().charAt(0);
//            updateDirection(direction);
//        }
//    }
//
//    private void updateDirection(char newDirection) {
//        up = (newDirection == 'w');
//        down = (newDirection == 's');
//        left = (newDirection == 'a');
//        right = (newDirection == 'd');
//    }
//
//    public static void main(String[] args) {
//        Game game = new Game();
//        game.run();
//    }
//
//        @Override
//    public void keyTyped(KeyEvent e) {
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            right = true;
//            down = false;
//            left = false;
//            up = false;
//        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            left = true;
//            right = false;
//            down = false;
//            up = false;
//        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
//            up = true;
//            right = false;
//            left = false;
//            down = false;
//        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            down = true;
//            right = false;
//            left = false;
//            up = false;
//        }
//    }//To move the snake. Turning the other direction false, you do not need to keep pressing the button.
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//    }
//}

