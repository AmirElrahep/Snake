package com.amir.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for the HowToPlay_Pane
 *
 * @author Amir Elrahep
 * @since 12-17-2020
 */
public class HowToPlay_Controller implements Initializable {

    @FXML
    private Label lblHowToPlayMsg;


    /**
     * This method initializes the HowToPlay_Pane when the program runs.
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblHowToPlayMsg.setWrapText(true);
        lblHowToPlayMsg.setText("The classic Snake game involves a moving snake on a board eating fruit in order to grow.\n" +
                "\n" +
                "The rules of the game are simple: eat as much fruit as you can without dying. The game ends when the snake either moves off the screen, bites itself, or hits one of the walls in the Walls game mode.\n" +
                "\n" +
                "The difficulty lies in that as the snake gets larger it is harder for the snake to remain alive as the chances of biting itself increase. \n" +
                "\n" +
                "Different game options include Normal, Walls, and Free Play game modes. The ability to select the amount of fruit displayed at a time. The ability to change the color of the snake and the fruit. The ability to change the speed of the snake.");
    }

}
