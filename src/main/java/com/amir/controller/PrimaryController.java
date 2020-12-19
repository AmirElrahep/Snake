package com.amir.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.amir.App;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


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
        App.disableMenu();
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


        // setting up comboboxes choices and default selections
        cbo_gameMode.getItems().addAll("Normal", "Walls", "Free Play");
        cbo_gameMode.setValue("Normal");

        cbo_numberOfFruit.getItems().addAll(1, 3, 5);
        cbo_numberOfFruit.setValue(1);

        cp_fruitColor.setValue(Color.ORANGERED);

        cbo_snakeSpeed.getItems().addAll("Slow", "Normal", "Fast");
        cbo_snakeSpeed.setValue("Normal");

        cp_snakeColor.setValue(Color.DARKGREEN);
    }

}
