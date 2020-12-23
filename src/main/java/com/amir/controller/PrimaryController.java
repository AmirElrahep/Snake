package com.amir.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

import com.amir.model.Fruit;
import com.amir.model.Snake;
import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 * Controller class for the application
 *
 * @author Amir Elrahep
 * @since 12-18-2020
 */
public class PrimaryController implements Initializable {

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


    // public methods

    /**
     * These are the methods when the Play Game or Start Game buttons are pressed. Sets the current pane visibility
     * to false and sets the corresponding pane's visibility to true. The btnStartGamePressed method calls the
     * disableMenu method to set the menu visibility to false.
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



    public static KeyCode currDirection = KeyCode.RIGHT;

    public void startGameLoop() {
        // creating a snake and drawing it to the screen
        Snake snake = new Snake(5, cp_snakeColor.getValue());
        snake.drawSnake(pane_game);

        // creating an ArrayList of Fruits and drawing them to the screen
        ArrayList<Fruit> fruits = new ArrayList<>();
        // for loop that fills the ArrayList with Fruit objects and displays them by calling the drawFruit method
        for (int i = 0; i < cbo_numberOfFruit.getValue(); i++) {
            fruits.add(new Fruit(cp_fruitColor.getValue()));
            fruits.get(i).drawFruit(pane_game);
        }



        // animation using Timeline
//        Timeline timeline = new Timeline();
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> snake.moveSnake(PrimaryController.currDirection)));
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> snake.collisionHandler(fruits, pane_game)));
//
//        timeline.play();



        // animation using AnimationTimer
        AnimationTimer animationTimer = new AnimationTimer() {

            private int frameCount = 0; // used to slow the animation down
            private final int speedValue = 5; // calculates the frame value for the handle function


            @Override
            public void handle(long now) {

                if (frameCount % speedValue == 0) { // will only handle the next animation when frameCount is divisible by 4
                    snake.moveSnake(PrimaryController.currDirection); // move snake forward
                }
                frameCount++;

                //snake.moveSnake(PrimaryController.currDirection);
                snake.collisionHandler(fruits, pane_game);

                snake.setIsDead(snake.collisionHandler(fruits, pane_game));
                if (snake.getIsDead()) {
                    stop();

                    // erasing the snake
                    snake.eraseSnake(pane_game);

                    // erasing the fruit
                    for (int i = 0; i < cbo_numberOfFruit.getValue(); i++) {
                        fruits.get(i).eraseFruit(pane_game);
                    }



                    pane_game.setVisible(false);
                    pane_gameOver.setVisible(true);
                    //pane_game.requestFocus();
                    //pane_game.setFocusTraversable(true);
                }

                //System.out.println(snake.getIsDead());
            }
        };
        animationTimer.start();






        //pane_game.setFocusTraversable(true);
        pane_game.requestFocus();
        pane_game.setOnKeyPressed(event -> {
            PrimaryController.currDirection = event.getCode();
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

/*
 * Notes
 * - bug in code: sometimes fruit generates where the snake is
 */
