package com.amir.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.amir.model.Fruit;
import com.amir.model.Snake;
import com.amir.model.Wall;
import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


/**
 * Controller class for the application
 *
 * @author Amir Elrahep
 * @since 12-18-2020
 */
public class MainAppController implements Initializable {

    /**
     * These are the different anchor panes.
     */
    @FXML
    private AnchorPane pane_home;
    @FXML
    private AnchorPane pane_options;
    @FXML
    private AnchorPane pane_game;
    @FXML
    private AnchorPane pane_gameOver;


    /**
     * These are the buttons for the application.
     */
    @FXML
    private JFXButton btn_playGame;
    @FXML
    private JFXButton btn_startGame;
    @FXML
    private JFXButton btn_playAgain;
    @FXML
    private JFXButton btn_changeOptions;


    /**
     * These are the labels for the application.
     */
    @FXML
    private Label lbl_snake;
    @FXML
    private Label lbl_gameOptions;
    @FXML
    private Label lbl_gameMode;
    @FXML
    private Label lbl_numberOfFruit;
    @FXML
    private Label lbl_fruitColor;
    @FXML
    private Label lbl_snakeSpeed;
    @FXML
    private Label lbl_snakeColor;
    @FXML
    private Label lbl_gameOver;
    @FXML
    private Label lbl_score;


    /**
     * These are the comboboxes for the application.
     */
    @FXML
    private ComboBox<String> cbo_gameMode;
    @FXML
    private ComboBox<Integer> cbo_numberOfFruit;
    @FXML
    private ColorPicker cp_fruitColor;
    @FXML
    private ComboBox<String> cbo_snakeSpeed;
    @FXML
    private ColorPicker cp_snakeColor;


    /**
     * This declares and initializes the direction and score variable.
     */
    public static KeyCode currDirection = KeyCode.RIGHT;
    public static int score = 0;


    // private methods

    /**
     * This method erases the Snake, Fruits, and Walls from the pane by calling the respective erase method. Sets
     * pane visibility for the pane_game and pane_gameOver. Sets the lbl_score Label. Sets the score variable to 0.
     *
     * @param snake  Snake
     * @param fruits ArrayList of Fruit objects
     * @param walls  ArrayList of Wall objects
     */
    private void quitGame(Snake snake, ArrayList<Fruit> fruits, ArrayList<Wall> walls) {
        snake.eraseSnake(pane_game);

        for (Fruit f : fruits) {
            f.eraseFruit(pane_game);
        }

        for (Wall w : walls) {
            w.eraseWall(pane_game);
        }

        pane_game.setVisible(false);
        pane_gameOver.setVisible(true);
        lbl_score.setText("Score: " + score);

        score = 0;
    }


    // public methods

    /**
     * These are the methods when the Play Game, Start Game, Change Options, or Play Again buttons are pressed.
     * Sets the current pane visibility to false and sets the corresponding pane's visibility to true. Calls the
     * startGameLoop method if necessary.
     */
    public void btnPlayGamePressed() {
        pane_home.setVisible(false);
        pane_options.setVisible(true);
    }

    public void btnStartGamePressed() {
        pane_options.setVisible(false);
        pane_game.setVisible(true);
        //App.disableMenu();
        startGameLoop();
    }

    public void btnChangeOptionsPressed() {
        pane_gameOver.setVisible(false);
        pane_options.setVisible(true);
    }

    public void btnPlayAgainPressed() {
        pane_gameOver.setVisible(false);
        pane_game.setVisible(true);

        startGameLoop();
    }


    /**
     * This method represents the game loop. Creates the Snake, Fruit, and Wall objects and draws them the pane by
     * calling the corresponding draw method. Uses an animation timer to move the Snake and and check for collisions.
     * Depending on the game mode if a collision occurs, the animation timer is topped and the calls the quitGame method.
     */
    public void startGameLoop() {
        currDirection = KeyCode.RIGHT;

        // creating a Snake and drawing it to the pane
        Snake snake = new Snake(5, cp_snakeColor.getValue());
        snake.drawSnake(pane_game);

        // creating an ArrayList of Fruits and drawing them to the pane
        ArrayList<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < cbo_numberOfFruit.getValue(); i++) {
            fruits.add(new Fruit(cp_fruitColor.getValue()));
            fruits.get(i).drawFruit(pane_game);
        }

        // if game mode Walls is selected, creates an ArrayList of Walls and draws them to the pane
        ArrayList<Wall> walls = new ArrayList<>();
        if (cbo_gameMode.getValue().equals("Walls")) {
            for (int i = 0; i < 25; i++) {
                walls.add(new Wall(Color.BLACK));
                walls.get(i).drawWall(pane_game);
            }
        }


        final int[] frameCount = {0};
        int speedValue = 5; // Normal game mode speed

        // if different game mode speed it selected, set speedValue value to corresponding integer
        if (cbo_snakeSpeed.getValue().equals("Slow")) {
            speedValue = 10;
        } else if (cbo_snakeSpeed.getValue().equals("Fast")) {
            speedValue = 3;
        }

        // animation using AnimationTimer
        int finalSpeedValue = speedValue;
        AnimationTimer animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (frameCount[0] % finalSpeedValue == 0) {
                    // moves the Snake
                    snake.moveSnake(MainAppController.currDirection);
                }
                frameCount[0]++;

                snake.collisionHandler(fruits, walls, pane_game);

                // is user pressed ESC to quit
                if (MainAppController.currDirection.equals(KeyCode.ESCAPE)) {
                    stop(); // stop the animationTimer
                    quitGame(snake, fruits, walls);
                }

                // checking which game mode selection is made - this is for Normal and Walls game modes
                if (cbo_gameMode.getValue().equals("Normal") || cbo_gameMode.getValue().equals("Walls")) {
                    snake.setIsDead(snake.collisionHandler(fruits, walls, pane_game));

                    if (snake.getIsDead()) {
                        stop(); // stopping the animationTimer
                        quitGame(snake, fruits, walls);
                    }
                }
            } // end handle
        }; // end animationTimer

        animationTimer.start();

        pane_game.requestFocus();
        pane_game.setOnKeyPressed(event -> {
            // limit Snake direction to only three directions at a time or if ESC is pressed
            if (event.getCode() == KeyCode.UP && !currDirection.equals(KeyCode.DOWN)) {
                currDirection = KeyCode.UP;
            } else if (event.getCode() == KeyCode.DOWN && !currDirection.equals(KeyCode.UP)) {
                currDirection = KeyCode.DOWN;
            } else if (event.getCode() == KeyCode.RIGHT && !currDirection.equals(KeyCode.LEFT)) {
                currDirection = KeyCode.RIGHT;
            } else if (event.getCode() == KeyCode.LEFT && !currDirection.equals(KeyCode.RIGHT)) {
                currDirection = KeyCode.LEFT;
            } else if (event.getCode() == KeyCode.ESCAPE) {
                currDirection = KeyCode.ESCAPE;
            }
        });
    }


    /**
     * This method initializes the Main App window when the program runs.
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting up the visibility of the panes
        pane_home.setVisible(true);
        pane_options.setVisible(false);
        pane_game.setVisible(false);
        pane_gameOver.setVisible(false);

        // setting colors for labels in the application
        lbl_snake.setTextFill(Color.rgb(104, 147, 198));
        lbl_gameOptions.setTextFill(Color.rgb(104, 147, 198));
        lbl_gameMode.setTextFill(Color.rgb(104, 147, 198));
        lbl_numberOfFruit.setTextFill(Color.rgb(104, 147, 198));
        lbl_fruitColor.setTextFill(Color.rgb(104, 147, 198));
        lbl_snakeSpeed.setTextFill(Color.rgb(104, 147, 198));
        lbl_snakeColor.setTextFill(Color.rgb(104, 147, 198));
        lbl_gameOver.setTextFill(Color.rgb(104, 147, 198));
        lbl_score.setTextFill(Color.rgb(104, 147, 198));
        lbl_score.setText("Score: ");

        // setting up combobox choices and default selections
        cbo_gameMode.getItems().addAll("Normal", "Walls", "Free Play");
        cbo_gameMode.setValue("Normal");

        cbo_numberOfFruit.getItems().addAll(1, 3, 5);
        cbo_numberOfFruit.setValue(1);

        cp_fruitColor.setValue(Color.GREEN);

        cbo_snakeSpeed.getItems().addAll("Slow", "Normal", "Fast");
        cbo_snakeSpeed.setValue("Normal");

        cp_snakeColor.setValue(Color.rgb(104, 147, 198));
    }

}
