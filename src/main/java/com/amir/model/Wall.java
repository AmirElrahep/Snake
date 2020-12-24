package com.amir.model;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Wall class, represents a wall object
 *
 * @author Amir Elrahep
 * @since 12-24-2020
 */
public class Wall {

    private Rectangle wall;
    private final Color wallColor;
    private static final Random rand = new Random();


    public Wall(Color color) {
        this.wall = new Rectangle();
        this.wallColor = color;

        createWall();
    }


    public void drawWall(Pane pane) {
        pane.getChildren().add(this.wall);
    }

    public void eraseWall(Pane pane) {
        pane.getChildren().remove(this.wall);
    }

    public void moveWall() {
        int xPos = -1;
        int yPos = -1;

        while ((xPos % 20 != 0) || (yPos % 20 != 0)) {
            xPos = rand.nextInt(1000 - 20) + 20;
            yPos = rand.nextInt(800 - 20) + 20;
        }

        this.wall.setLayoutX(xPos);
        this.wall.setLayoutY(yPos);
    }

    public Rectangle getWall() {
        return wall;
    }

    // private methods

    private void createWall() {
        this.wall = new Rectangle(20, 20, wallColor);
        moveWall();
    }




}
