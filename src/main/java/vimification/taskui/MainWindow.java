package vimification.taskui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
// import javafx.scene.control.MenuItem;
// import javafx.scene.control.TextInputControl;
// import javafx.scene.input.KeyCombination;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vimification.logic.Logic;

/**
 * The Main Window. Provides the basic application layout containing a menu bar and space where
 * other JavaFX elements can be placed.
 */
public class MainWindow extends VBox {

    private static final String FXML = "Main.fxml";

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;

    @FXML
    private VBox leftComponent;

    @FXML
    private VBox rightComponent;

    @FXML
    private VBox textBoxComponent;

    @FXML
    private TextField textBox;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            this.setOnKeyPressed(event -> {
                if (event.getText().equals(":")) {
                    textBoxComponent.setVisible(true);
                    textBox.requestFocus();
                }
            });

            textBoxComponent.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    textBoxComponent.setVisible(false);
                    this.requestFocus();
                    System.out.println("You escaped");
                }
            });

            textBox.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    textBoxComponent.setVisible(false);
                    this.requestFocus();
                    String commandString = textBox.getText();
                    System.out.println("Your command is " + commandString);

                    // TODO : Create a Parser to parse the command and create a Driver to run it.
                    if (commandString.equals(":wq!")) {
                        Platform.exit();
                    }
                }
            });

            // Set up the ":" key listener to show/hide the text box component
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @FXML
    public void initialize() {
        textBoxComponent.setVisible(false);
    }

}
