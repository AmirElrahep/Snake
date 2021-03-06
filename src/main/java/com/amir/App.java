package com.amir;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * App class
 *
 * @author Amir Elrahep
 * @since 2-22-2020
 */
public class App extends Application {

    /**
     * The main menu for the application.
     */
    public static MenuBar menuBar = new MenuBar();


    /**
     * This method sets the menu visibility property to true.
     */
    public static void enableMenu() {
        menuBar.setVisible(true);
    }


    /**
     * This method sets the menu visibility property to false.
     */
    public static void disableMenu() {
        menuBar.setVisible(false);
    }


    @Override
    public void start(Stage stage) throws IOException {
        BorderPane rootPane = new BorderPane();
        Parent content = loadFXML("view/Snake");
        rootPane.setCenter(content);

        Scene scene = new Scene(rootPane);
        scene.getStylesheets().add(getClass().getResource("css/dark.css").toExternalForm());

        // menu bar
        //MenuBar menuBar = new MenuBar();
        // file menu
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        exitItem.setOnAction(ae -> {
            Platform.exit();
            System.exit(0);
        });
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);

        // theme menu
        Menu themeMenu = new Menu("Theme");
        themeMenu.setMnemonicParsing(true);
        menuBar.getMenus().add(themeMenu);
        rootPane.setTop(menuBar);

        // creating sub-menu buttons
        MenuItem dark = new MenuItem("Dark Mode");
        MenuItem light = new MenuItem("Light Mode");

        dark.setOnAction(ae -> {
            scene.getStylesheets().clear();
            setUserAgentStylesheet(null);
            scene.getStylesheets().add(getClass().getResource("css/dark.css").toExternalForm());
            dark.setStyle("-fx-text-fill: #6893C6");
            light.setStyle(null);

        });

        light.setOnAction(ae -> {
            scene.getStylesheets().clear();
            setUserAgentStylesheet(null);
            scene.getStylesheets().add(getClass().getResource("css/light.css").toExternalForm());
            light.setStyle("-fx-text-fill: #6893C6");
            dark.setStyle(null);
        });

        dark.setStyle("-fx-text-fill: #6893C6");
        // adding sub-menu buttons to main menu button
        themeMenu.getItems().addAll(dark, light);

        // help menu
        Menu helpMenu = new Menu("Help");
        helpMenu.setMnemonicParsing(true);
        menuBar.getMenus().add(helpMenu);
        rootPane.setTop(menuBar);

        // creating sub-menu buttons
        MenuItem howToPlay = new MenuItem("How to play");
        MenuItem about = new MenuItem("About");

        howToPlay.setOnAction(ae -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/HowToPlay_Pane.fxml"));
                DialogPane newGameDialogPane = fxmlLoader.load();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(newGameDialogPane);
                dialog.setTitle("How to play");
                dialog.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        about.setOnAction(ae -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/About_Pane.fxml"));
                DialogPane newGameDialogPane = fxmlLoader.load();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(newGameDialogPane);
                dialog.setTitle("About");
                dialog.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // adding sub-menu buttons to main menu button
        helpMenu.getItems().addAll(howToPlay, about);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}
