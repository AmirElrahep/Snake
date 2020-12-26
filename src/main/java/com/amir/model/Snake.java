package com.amir.model;

import com.amir.controller.PrimaryController;
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
    private final Color snakeColor;
    private int snakeLength;
    private Rectangle snakeHead;
    private boolean isDead;


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

        createSnake();
    }


    /**
     * This method draws the Snake. It does this by adding the Snake, ArrayList of Rectangle objects, to the pane.
     *
     * @param pane pane to draw the Snake on
     */
    public void drawSnake(Pane pane) {
        pane.getChildren().addAll(this.snake);
    }


    /**
     * This method erases the Snake. It does this by removing the Snake, ArrayList of Rectangle objects, from the pane.
     *
     * @param pane pane to erase the Snake from
     */
    public void eraseSnake(Pane pane) {
        pane.getChildren().removeAll(this.snake);
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

        // for testing - prints the snake location
        //System.out.println("--- Snake Location ---\nX-Pos: " + snakeHead.getLayoutX() + "\nY-Pos: " + snakeHead.getLayoutY());
    }


    /**
     * This method checks for a collision with the snake.
     * <p>
     * -- Part one --
     * Checks if the Snake collided with a Fruit object. Loops through the ArrayList of Fruit objects and if the
     * Fruit's LayoutY and LayoutX equal the Snake's LayoutY and LayoutX. If a collision occurs, the player score and
     * Snake size are incremented. The Snake is removed and redrawn to the pane. The Fruit is moves to a new location.
     * Return false.
     * <p>
     * -- Part two --
     * Checks if the Snake collided with a Wall object. Loops through the ArrayList of Wall objects and if the Wall's
     * LayoutY and LayoutX equal the Snake's LayoutY and LayoutX, return true.
     * <p>
     * -- Part three --
     * Checks if the Snake collided with the border walls. Return true.
     * <p>
     * -- Part four --
     * Checks if the Snake hit itself. Loops through the length of the Snake and checks if the Snake it itself, if so
     * return true.
     * <p>
     * -- Part five --
     * Return false.
     *
     * @param fruits ArrayList of Fruit objects
     * @param pane   pane to draw Snake on
     * @return boolean if snake is dead or not
     */
    public boolean collisionHandler(ArrayList<Fruit> fruits, ArrayList<Wall> walls, Pane pane) {
        // -- part one -- checking if Snake hit fruit
        for (Fruit f : fruits) {
            if (f.getFruit().getLayoutY() == snake.get(0).getLayoutY() &&
                    f.getFruit().getLayoutX() == snake.get(0).getLayoutX()) {
                PrimaryController.score++;
                incrementSnakeSize();
                this.snakeLength++;

                pane.getChildren().removeAll(snake);
                drawSnake(pane);

                f.moveFruit();

                // for testing
                //System.out.println("Snake hit fruit");
                return false;
            }
        }

        // -- part two -- checking if Snake hit inner walls
        for (Wall w : walls) {
            if (w.getWall().getLayoutY() == snake.get(0).getLayoutY() &&
                    w.getWall().getLayoutX() == snake.get(0).getLayoutX()) {
                // for testing
                //System.out.println("Snake hit inner wall");
                return true;
            }
        }

        // -- part three -- checking if snake hit screen walls
        if (this.snakeHead.getLayoutX() <= -20 || this.snakeHead.getLayoutX() >= pane.getWidth() ||
                this.snakeHead.getLayoutY() <= -20 || this.snakeHead.getLayoutY() >= pane.getHeight()) {
            // for testing
            //System.out.println("Snake hit wall");
            return true;
        }

        // -- part four -- checking if Snake hit itself
        for (int i = 1; i < this.snakeLength; i++) {
            if (this.snake.get(i).getLayoutX() == this.snakeHead.getLayoutX() &&
                    this.snake.get(i).getLayoutY() == this.snakeHead.getLayoutY()) {
                // for testing
                //System.out.println("Snake hit itself");
                return true;
            }
        }

        // -- part five --
        return false;
    }

    /**
     * This method returns the Snake's isDead property.
     *
     * @return isDead
     */
    public boolean getIsDead() {
        return isDead;
    }

    /**
     * This method sets the Snake's isDead property.
     *
     * @param isDead boolean isDead
     */
    public void setIsDead(Boolean isDead) {
        this.isDead = isDead;
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
        snakeHead.setLayoutX(0);
        snakeHead.setLayoutY(0);

        // creating the snake body
        for (int i = 1; i < snakeLength; i++) {
            Rectangle bodySegment = new Rectangle(20, 20);
            snake.add(bodySegment);
            bodySegment.setLayoutX(0);
            bodySegment.setLayoutY(0);
        }
    }


    /**
     * This method moves the Snake in the up direction.
     */
    private void moveSnakeUp() {
        Rectangle oldHead = this.snakeHead;
        Rectangle newHead = this.snake.remove(this.snakeLength - 1);
        oldHead.setFill(snakeColor);
        newHead.setFill(snakeColor);
        newHead.setLayoutY((oldHead.getLayoutY() - 20) % 820);
        newHead.setLayoutX(oldHead.getLayoutX());

        if (newHead.getLayoutY() <= -20) {
            newHead.setLayoutY(800);
        }

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
        newHead.setLayoutY((oldHead.getLayoutY() + 20) % 820);
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
        newHead.setLayoutX((oldHead.getLayoutX() + 20) % 1020);
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
        newHead.setLayoutX((oldHead.getLayoutX() - 20) % 1020);
        newHead.setLayoutY(oldHead.getLayoutY());

        if (newHead.getLayoutX() <= -20) {
            newHead.setLayoutX(1000);
        }

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
    }

}
