package com.amir.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.amir.App;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneOptions;
    @FXML
    private AnchorPane paneGame;


    /**
     * These are the buttons for the application.
     */
    @FXML
    private JFXButton btnPlayGame;
    @FXML
    private JFXButton btnStartGame;


    /**
     * These are the labels for the application.
     */
    @FXML
    private Label lblSnake;
    @FXML
    private Label lblGameOptions;
    @FXML
    private Label lblGameMode;
    @FXML
    private Label lblNumberOfFruit;
    @FXML
    private Label lblFruitColor;
    @FXML
    private Label lblSnakeSpeed;
    @FXML
    private Label lblSnakeColor;


    // public methods

    /**
     * These are the methods when the Play Game or Start Game buttons are pressed. Sets the current pane visibility
     * to false and sets the corresponding pane's visibility to true.
     */
    public void btnPlayGamePressed() {
        paneHome.setVisible(false);
        paneOptions.setVisible(true);
    }

    public void btnStartGamePressed() {
        paneOptions.setVisible(false);
        paneGame.setVisible(true);
        App app = new App();
        app.menuBar.setVisible(false);
        app.disableMenu();
    }


    /**
     * This method initializes the Main App window when the program runs.
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneHome.setVisible(true);
        paneOptions.setVisible(false);
        paneGame.setVisible(false);


        // setting colors for labels in the application
        lblSnake.setTextFill(Color.rgb(104, 147, 198));
        lblGameOptions.setTextFill(Color.rgb(104, 147, 198));
        lblGameMode.setTextFill(Color.rgb(104, 147, 198));
        lblNumberOfFruit.setTextFill(Color.rgb(104, 147, 198));
        lblFruitColor.setTextFill(Color.rgb(104, 147, 198));
        lblSnakeSpeed.setTextFill(Color.rgb(104, 147, 198));
        lblSnakeColor.setTextFill(Color.rgb(104, 147, 198));
    }

}
