package vimification.taskui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

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
    // private TaskListPanel taskListPanel;
    private CommandInput commandInput;

    @FXML
    private VBox leftComponent;

    @FXML
    private VBox rightComponent;

    @FXML
    private VBox textBoxComponent;

    // @FXML
    // private TextField textBox;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            setFocusTraversable(true); // Important

            commandInput = new CommandInput(this);
            textBoxComponent.getChildren().add(commandInput);

            // Set up the ":" key listener to show/hide the text box component
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Listener for handling all keyboard events to Vimification.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getText().equals(":")) {
            commandInput.setVisible(true);
            commandInput.requestFocus();
        }
    }


}
