package mycelium.mycelium.ui.commandbox;

import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Consumer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.logic.commands.CommandResult;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.ui.MainWindow;
import mycelium.mycelium.ui.UiPart;

/**
 * The UI component that is responsible for receiving user command inputs. It can be in exactly one of two states:
 * listening or not listening. When not listening, it functions like a usual text field and handles user input via a
 * {@link CommandExecutor} upon pressing enter. When listening, it listens to every change in the text field and runs
 * a handler via {@link CommandInputListener} upon every change.
 * <p>
 * Both handlers can be set through the constructor.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String LISTENING_STYLE_CLASS = "listening";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandInputListener commandInputListener;
    private final MainWindow mainWindow;
    private boolean isListening;
    /**
     * A lambda to run when the command box starts listening.
     */
    private Consumer<MainWindow> uponListening;
    /**
     * A lambda to run when the command box stops listening.
     */
    private Consumer<MainWindow> uponNotListening;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox}.
     *
     * @param mainWindow           The main window of the application. Required as some actions modify the window.
     * @param commandExecutor      Handler for executing user-entered commands.
     * @param commandInputListener Handler for changes in the command input.
     * @param uponListening        Action to be performed when the command box starts listening.
     * @param uponNotListening     Action to be performed when the command box stops listening.
     */
    public CommandBox(MainWindow mainWindow,
                      CommandExecutor commandExecutor,
                      CommandInputListener commandInputListener,
                      Consumer<MainWindow> uponListening,
                      Consumer<MainWindow> uponNotListening) {
        super(FXML);
        requireAllNonNull(mainWindow, commandExecutor, commandInputListener, uponListening, uponNotListening);
        this.commandExecutor = commandExecutor;
        this.commandInputListener = commandInputListener;
        this.uponListening = uponListening;
        this.uponNotListening = uponNotListening;
        this.mainWindow = mainWindow;
        this.isListening = false;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        if (isListening) {
            return;
        }
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void handleInputChange() {
        if (!isListening) {
            return;
        }
        String currentText = commandTextField.getText();
        commandInputListener.onInputChanged(currentText, mainWindow);
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        if (isListening) {
            setStyleToIndicateListening();
        } else {
            commandTextField.getStyleClass().remove(LISTENING_STYLE_CLASS);
        }
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate listening.
     */
    private void setStyleToIndicateListening() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(LISTENING_STYLE_CLASS)) {
            return;
        }

        styleClass.add(LISTENING_STYLE_CLASS);
    }

    /**
     * Toggles listening mode.
     */
    public void toggleListening() {
        isListening = !isListening;
        if (isListening) {
            uponListening.accept(mainWindow);
        } else {
            uponNotListening.accept(mainWindow);
        }
        setStyleToDefault();
        commandTextField.setText("");
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function that is called on input change.
     */
    @FunctionalInterface
    public interface CommandInputListener {
        void onInputChanged(String input, MainWindow mainWindow);
    }
}
