package com.amir.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    }


    //    // testing
    public static KeyCode currDirection = KeyCode.RIGHT;


    /**
     * This method creates the snake. Creates an ArrayList of Rectangle objects to represent the snake. Creates a
     * Rectangle object representing the snake head and adds it to the array list. Creates the body of the snake by
     * using a for loop to create Rectangle objects to represent the body segments and adds them to the ArrayList.
     *
     * @return ArrayList of Rectangle objects representing the snake
     */
    private ArrayList<Rectangle> createSnake() {
        ArrayList<Rectangle> snake = new ArrayList<>();
        Rectangle head = new Rectangle(20, 20, cp_snakeColor.getValue());
        snake.add(head);

        int numCircles = 10;
        for (int i = 1; i < numCircles; i++) {
            Rectangle bodySegment = new Rectangle(20, 20);
            snake.add(bodySegment);
        }
        return snake;
    }


    /**
     * This method draws the snake. It does this by adding the snake, "arraylist of circle objects", to the pane.
     *
     * @param snake ArrayList of Rectangle objects representing the snake
     * @param pane  pane to draw the snake on
     */
    private void drawSnake(ArrayList<Rectangle> snake, Pane pane) {
        pane.getChildren().addAll(snake);
    }


    /**
     * This method moves the snake in the up direction.
     *
     * @param snake ArrayList of Rectangle objects representing the snake
     */
    public void moveSnakeUp(ArrayList<Rectangle> snake) {
        Rectangle oldHead = snake.get(0);
        Rectangle newHead = snake.remove(snake.size() - 1);
        oldHead.setFill(cp_snakeColor.getValue());
        newHead.setFill(cp_snakeColor.getValue());
        newHead.setLayoutY((oldHead.getLayoutY() - 20 + 800) % 800);
        newHead.setLayoutX(oldHead.getLayoutX());
        snake.add(0, newHead);
    }


    /**
     * This method moves the snake in the down direction.
     *
     * @param snake ArrayList of Rectangle objects representing the snake
     */
    public void moveSnakeDown(ArrayList<Rectangle> snake) {
        Rectangle oldHead = snake.get(0);
        Rectangle newHead = snake.remove(snake.size() - 1);
        oldHead.setFill(cp_snakeColor.getValue());
        newHead.setFill(cp_snakeColor.getValue());
        newHead.setLayoutY((oldHead.getLayoutY() + 20 + 800) % 800);
        newHead.setLayoutX(oldHead.getLayoutX());
        snake.add(0, newHead);
    }


    /**
     * This method moves the snake in the right direction.
     *
     * @param snake ArrayList of Rectangle objects representing the snake
     */
    public void moveSnakeRight(ArrayList<Rectangle> snake) {
        Rectangle oldHead = snake.get(0);
        Rectangle newHead = snake.remove(snake.size() - 1);
        oldHead.setFill(cp_snakeColor.getValue());
        newHead.setFill(cp_snakeColor.getValue());
        newHead.setLayoutX((oldHead.getLayoutX() + 20) % 1000);
        newHead.setLayoutY(oldHead.getLayoutY());
        snake.add(0, newHead);
    }


    /**
     * This method moves the snake in the left direction.
     *
     * @param snake ArrayList of Rectangle objects representing the snake
     */
    public void moveSnakeLeft(ArrayList<Rectangle> snake) {
        Rectangle oldHead = snake.get(0);
        Rectangle newHead = snake.remove(snake.size() - 1);
        oldHead.setFill(cp_snakeColor.getValue());
        newHead.setFill(cp_snakeColor.getValue());
        newHead.setLayoutX((oldHead.getLayoutX() - 20 + 1000) % 1000);
        newHead.setLayoutY(oldHead.getLayoutY());
        snake.add(0, newHead);
    }


    /**
     * This method moves the snake based on the key pressed. Takes an ArrayList of Rectangle objects representing
     * the snake and the KeyCode direction. Based on the KeyCode direction, it uses a switch statement to call the
     * corresponding method to move the snake in the varying direction.
     *
     * @param snake     ArrayList of Rectangle objects representing the snake
     * @param direction direction of key pressed
     */
    private void moveSnake(ArrayList<Rectangle> snake, KeyCode direction) {
        switch (direction) {
            case UP:
                moveSnakeUp(snake);
                break;
            case DOWN:
                moveSnakeDown(snake);
                break;
            case RIGHT:
                moveSnakeRight(snake);
                break;
            case LEFT:
                moveSnakeLeft(snake);
                break;
            default:
                break;
        }
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


        // testing
        ArrayList<Rectangle> snake = createSnake();

        // animation using Timeline
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> moveSnake(snake, PrimaryController.currDirection)));
        timeline.play();


        drawSnake(snake, pane_game);

        pane_game.setFocusTraversable(true);
        pane_game.setOnKeyPressed(event -> {
            //PrimaryController.moveSnake(snake, event.getCode());
            PrimaryController.currDirection = event.getCode();
        });
    }

}
