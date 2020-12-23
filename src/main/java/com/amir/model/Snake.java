package com.amir.model;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


/**
 * Snake class, represents a snake object
 *
 * @author Amir Elrahep
 * @since 12-18-2020
 */
public class Snake {

    private final ArrayList<Rectangle> snake;
    private int snakeLength;
    private Rectangle snakeHead;
    private final Color snakeColor;
    private boolean isDead;
    //private KeyCode currDirection;


    /**
     * This is the Snake constructor. Creates a Snake object that is represented by an ArrayList of Rectangle object.
     * Takes in an integer representing the snake length and a color representing the snake color.
     *
     * @param length length of the snake
     * @param color  color of the snake
     */
    public Snake(int length, Color color) {
        this.snake = new ArrayList<>();
        this.snakeLength = length;
        this.snakeColor = color;
        this.isDead = false;
        //this.currDirection = KeyCode.RIGHT;

        createSnake();
    }


    /**
     * This method draws the Snake. It does this by adding the Snake, ArrayList of circle objects, to the pane.
     *
     * @param pane pane to draw the Snake on
     */
    public void drawSnake(Pane pane) {
        pane.getChildren().addAll(this.snake);
    }


    /**
     * This method moves the Snake based on the key pressed. Takes a KeyCode direction. Based on the KeyCode direction,
     * it uses a switch statement to call the corresponding method to move the Snake in the varying direction.
     *
     * @param direction direction of key pressed
     */
    public void moveSnake(KeyCode direction) {
        switch (direction) {
            case UP:
                moveSnakeUp();
                break;
            case DOWN:
                moveSnakeDown();
                break;
            case RIGHT:
                moveSnakeRight();
                break;
            case LEFT:
                moveSnakeLeft();
                break;
            default:
                break;
        }
        this.snakeHead = this.snake.get(0);
    }


    /**
     *
     * @param fruits ArrayList of Fruit objects
     * @param pane pane to draw Snake on
     */
    public void collisionHandler(ArrayList<Fruit> fruits, Pane pane) {
        // checking if snake hit fruit
        for (Fruit f : fruits) {
            if (f.getFruit().getLayoutY() == snake.get(0).getLayoutY() && f.getFruit().getLayoutX() == snake.get(0).getLayoutX()) {
                incrementSnakeSize();
                this.snakeLength++;

                pane.getChildren().removeAll(snake);
                drawSnake(pane);

                f.moveFruit();

                // for testing
                System.out.println("snake hit fruit");
            }
        }

        // checking if snake hit wall
        if (this.snakeHead.getLayoutX() <= 0 || this.snakeHead.getLayoutX() >= pane.getWidth() ||
                this.snakeHead.getLayoutY() <= 0 || this.snakeHead.getLayoutY() >= pane.getHeight()) {

            System.out.println("Snake hit wall");
        }

        // checking if snake hit itself
        for (int i = 1; i < this.snakeLength; i++) {
            if (this.snake.get(i).getLayoutX() == this.snakeHead.getLayoutX() &&
                    this.snake.get(i).getLayoutY() == this.snakeHead.getLayoutY()) {

                System.out.println("Snake hit itself");
            }
        }
    }


    // private methods

    /**
     * This method creates the Snake. Creates an ArrayList of Rectangle objects to represent the Snake. Creates a
     * Rectangle object representing the Snake head and adds it to the ArrayList. Sets the snakeHead to the first
     * element in the ArrayList. Creates the body of the Snake by using a for loop to create Rectangle objects to
     * represent the body segments and adds them to the ArrayList.
     */
    private void createSnake() {
        Rectangle head = new Rectangle(20, 20, snakeColor);
        this.snake.add(head);
        this.snakeHead = snake.get(0);

        // creating the snake body
        for (int i = 1; i < snakeLength; i++) {
            Rectangle bodySegment = new Rectangle(20, 20);
            snake.add(bodySegment);
        }
        System.out.println("Snake length: " + snakeLength);
    }


    /**
     * This method moves the Snake in the up direction.
     */
    private void moveSnakeUp() {
        Rectangle oldHead = this.snakeHead;
        Rectangle newHead = this.snake.remove(this.snakeLength - 1);
        oldHead.setFill(snakeColor);
        newHead.setFill(snakeColor);
        newHead.setLayoutY((oldHead.getLayoutY() - 20 + 800) % 800);
        newHead.setLayoutX(oldHead.getLayoutX());
        this.snake.add(0, newHead);
    }


    /**
     * This method moves the Snake in the down direction.
     */
    private void moveSnakeDown() {
        Rectangle oldHead = this.snakeHead;
        Rectangle newHead = this.snake.remove(this.snakeLength - 1);
        oldHead.setFill(snakeColor);
        newHead.setFill(snakeColor);
        newHead.setLayoutY((oldHead.getLayoutY() + 20 + 800) % 800);
        newHead.setLayoutX(oldHead.getLayoutX());
        this.snake.add(0, newHead);
    }


    /**
     * This method moves the Snake in the right direction.
     */
    private void moveSnakeRight() {
        Rectangle oldHead = this.snakeHead;
        Rectangle newHead = this.snake.remove(this.snakeLength - 1);
        oldHead.setFill(snakeColor);
        newHead.setFill(snakeColor);
        newHead.setLayoutX((oldHead.getLayoutX() + 20) % 1000);
        newHead.setLayoutY(oldHead.getLayoutY());
        this.snake.add(0, newHead);
    }


    /**
     * This method moves the Snake in the left direction.
     */
    private void moveSnakeLeft() {
        Rectangle oldHead = this.snakeHead;
        Rectangle newHead = this.snake.remove(this.snakeLength - 1);
        oldHead.setFill(snakeColor);
        newHead.setFill(snakeColor);
        newHead.setLayoutX((oldHead.getLayoutX() - 20 + 1000) % 1000);
        newHead.setLayoutY(oldHead.getLayoutY());
        this.snake.add(0, newHead);
    }


    /**
     * This method increments the Snake length. Creates a new Rectangle object, oldTail, and sets that to the end
     * of the Snake object represented by the ArrayList of Rectangle objects. Creates a new Rectangle object, newTail.
     * Sets the LayoutX and LayoutY of newTail to that of oldTail. Then adds the newTail to the Snake.
     */
    private void incrementSnakeSize() {
        Rectangle oldTail = this.snake.get(snakeLength - 1);

        Rectangle newTail = new Rectangle(20, 20, snakeColor);
        newTail.setLayoutX(oldTail.getLayoutX());
        newTail.setLayoutY(oldTail.getLayoutY());

        this.snake.add(newTail);

        System.out.println("Snake length: " + snake.size());
    }

}
