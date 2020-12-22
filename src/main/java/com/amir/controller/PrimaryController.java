package com.amir.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.amir.model.Snake;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.shape.Circle;
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


    /**
     * These are the buttons for the application.
     */
    @FXML
    private JFXButton btn_playGame;
    @FXML
    private JFXButton btn_startGame;


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



    public static KeyCode currDirection = KeyCode.RIGHT;

    public void startGameLoop() {
        Snake snake = new Snake(10, cp_snakeColor.getValue());
        //snake.createSnake();

        Rectangle fruit = generateFruit();


        // animation using Timeline
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> snake.moveSnake(PrimaryController.currDirection)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> snake.collisionHandler(fruit, pane_game)));
        //timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> moveFruit()));

        timeline.play();

        snake.drawSnake(pane_game);
        drawFruit(fruit, pane_game);


        pane_game.setFocusTraversable(true);
        //pane_game.requestFocus();
        pane_game.setOnKeyPressed(event -> {
            //PrimaryController.moveSnake(snake, event.getCode());
            PrimaryController.currDirection = event.getCode();
        });
    }








    public void moveFruit(Rectangle fruit) {
        //Rectangle fruit = new Rectangle(20,20,cp_fruitColor.getValue());

        int xPos = -1;
        int yPos = -1;
        while ((xPos % 20 != 0) || (yPos % 20 != 0)) {
            xPos = rand.nextInt(1000 - 20) + 20;
            yPos = rand.nextInt(800 - 20) + 20;

            System.out.println("x-pos: " + xPos + "\ny-pos: " + yPos + "\n\n");
        }
        fruit.setLayoutX(xPos);
        fruit.setLayoutY(yPos);

        pane_game.getChildren().remove(fruit);
        pane_game.getChildren().add(fruit);
    }




    private static final Random rand = new Random();

    public Rectangle generateFruit() {
        Rectangle fruit;

        int xPos = -1;
        int yPos = -1;
        while ((xPos % 20 != 0) || (yPos % 20 != 0)) {
            xPos = rand.nextInt(1000 - 20) + 20;
            yPos = rand.nextInt(800 - 20) + 20;
        }
        fruit = new Rectangle(20, 20, cp_fruitColor.getValue());
        fruit.setLayoutX(xPos);
        fruit.setLayoutY(yPos);

        return fruit;
    }

    private void drawFruit(Rectangle fruit, Pane pane) {
        pane.getChildren().addAll(fruit);
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

        // setting colors for labels in the application
        lbl_snake.setTextFill(Color.rgb(104, 147, 198));
        lbl_gameOptions.setTextFill(Color.rgb(104, 147, 198));
        lbl_gameMode.setTextFill(Color.rgb(104, 147, 198));
        lbl_numberOfFruit.setTextFill(Color.rgb(104, 147, 198));
        lbl_fruitColor.setTextFill(Color.rgb(104, 147, 198));
        lbl_snakeSpeed.setTextFill(Color.rgb(104, 147, 198));
        lbl_snakeColor.setTextFill(Color.rgb(104, 147, 198));


        // setting up combobox choices and default selections
        cbo_gameMode.getItems().addAll("Normal", "Walls", "Free Play");
        cbo_gameMode.setValue("Normal");

        cbo_numberOfFruit.getItems().addAll(1, 3, 5);
        cbo_numberOfFruit.setValue(1);

        cp_fruitColor.setValue(Color.ORANGERED);

        cbo_snakeSpeed.getItems().addAll("Slow", "Normal", "Fast");
        cbo_snakeSpeed.setValue("Normal");

        cp_snakeColor.setValue(Color.rgb(104, 147, 198));





//        // testing
//        ArrayList<Rectangle> snake = createSnake();
//        Rectangle fruit = generateFruit();
//
//        // animation using Timeline
//        Timeline timeline = new Timeline();
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> moveSnake(snake, PrimaryController.currDirection)));
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> collisionHandler(snake,fruit)));
//        //timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> moveFruit()));
//
//
//        timeline.play();
//
//
//        drawSnake(snake, pane_game);
//        drawFruit(fruit, pane_game);
//
//
//        pane_game.setFocusTraversable(true);
//        //pane_game.requestFocus();
//        pane_game.setOnKeyPressed(event -> {
//            //PrimaryController.moveSnake(snake, event.getCode());
//            PrimaryController.currDirection = event.getCode();
//        });
    }

}

/*
 * Notes
 * - bug in code: sometimes fruit generates where the snake is
 */
