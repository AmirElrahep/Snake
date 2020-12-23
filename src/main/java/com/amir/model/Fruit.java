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


    public Fruit(Color color) {
        this.fruit = new Rectangle();
        this.fruitColor = color;

        createFruit();
    }


    public void drawFruit(Pane pane) {
        pane.getChildren().add(this.fruit);
    }


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


    // private methods

    private void createFruit() {
        this.fruit = new Rectangle(20, 20, fruitColor);
        moveFruit();
    }


    public Rectangle getFruit() {
        return fruit;
    }

}
