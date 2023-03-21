package mycelium.mycelium.logic.commands.exceptions;

import mycelium.mycelium.ui.MainWindow;
import mycelium.mycelium.ui.action.DoNothingAction;
import mycelium.mycelium.ui.action.UiAction;

import static java.util.Objects.requireNonNull;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    private UiAction action;

    public CommandException(String message, UiAction action) {
        super(message);
        this.action = requireNonNull(action);
    }

    public CommandException(String message) {
        this(message, new DoNothingAction());
    }

    public void executeUiAction(MainWindow mainWindow) {
        action.execute(mainWindow);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
