package com.amir.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


/**
 * Fruit class, represents a fruit object
 *
 * @author Amir Elrahep
 * @since 12-22-2020
 */
public class Fruit {

    private Rectangle fruit;
    private final Color fruitColor;
    private static final Random rand = new Random();


    /**
     * This is the Fruit constructor. Creates a Fruit object that is represented by a Rectangle object.
     * Takes in a Color representing the fruit color.
     *
     * @param color color of the Fruit
     */
    public Fruit(Color color) {
        this.fruit = new Rectangle();
        this.fruitColor = color;

        createFruit();
    }


    /**
     * This method draws the Fruit. It does this by adding the Fruit to the pane.
     *
     * @param pane pane to draw the Fruit on
     */
    public void drawFruit(Pane pane) {
        pane.getChildren().add(this.fruit);
    }


    /**
     * This method erases the Fruit. It does this by removing the Fruit from the pane.
     *
     * @param pane pane to erase Fruit from
     */
    public void eraseFruit(Pane pane) {
        pane.getChildren().remove(this.fruit);
    }


    /**
     * This method moves the Fruit. Generates two numbers within the boundaries of the pane. Sets the LayoutX and
     * LayoutY of the Fruit to the new location.
     */
    public void moveFruit() {
        int xPos = -1;
        int yPos = -1;

        while ((xPos % 20 != 0) || (yPos % 20 != 0)) {
            xPos = rand.nextInt(1000 - 20) + 20;
            yPos = rand.nextInt(800 - 20) + 20;
        }

        fruit.setLayoutX(xPos);
        fruit.setLayoutY(yPos);
    }


    /**
     * This method return the Fruit object.
     *
     * @return fruit
     */
    public Rectangle getFruit() {
        return fruit;
    }


    // private methods

    /**
     * This method creates the Fruit object. Sets the Fruit to a new Rectangle and calls the moveFruit method.
     */
    private void createFruit() {
        this.fruit = new Rectangle(20, 20, fruitColor);
        moveFruit();
    }

}
