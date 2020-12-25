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


    /**
     * This is the Wall constructor. Creates a Wall object that is represented by a Rectangle object.
     * Takes a Color representing the wall color.
     *
     * @param color color of the Wall
     */
    public Wall(Color color) {
        this.wall = new Rectangle();
        this.wallColor = color;

        createWall();
    }


    /**
     * This method draws the Wall. It does this by adding the Wall to the pane.
     *
     * @param pane pane to draw the Wall on
     */
    public void drawWall(Pane pane) {
        pane.getChildren().add(this.wall);
    }


    /**
     * This method erases the Wall. It does this by removing the Wall from the pane.
     *
     * @param pane pane to erase the Wall from
     */
    public void eraseWall(Pane pane) {
        pane.getChildren().remove(this.wall);
    }


    /**
     * This method moves the Wall. Generates two numbers within the boundaries of the pane. Sets the LayoutX and
     * LayoutY of the Wall to the new Location.
     */
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


    /**
     * This method returns the Wall object.
     *
     * @return wall
     */
    public Rectangle getWall() {
        return wall;
    }


    // private methods

    /**
     * This method creates the Wall object. Sets the Wall to a new Rectangle and calls the moveWall method.
     */
    private void createWall() {
        this.wall = new Rectangle(20, 20, wallColor);
        moveWall();
    }

}
