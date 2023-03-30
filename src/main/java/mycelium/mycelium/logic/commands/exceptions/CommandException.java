package mycelium.mycelium.logic.commands.exceptions;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import mycelium.mycelium.logic.uiaction.DoNothingAction;
import mycelium.mycelium.logic.uiaction.UiAction;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents an error which occurs during execution of a {@code Command}.
 */
public class CommandException extends Exception {
    private UiAction action;

    /**
     * Constructs a new {@code CommandException} with the
     * specified detail {@code message} and {@code action} to be executed.
     * {@code action} cannot be null.
     *
     * @param message the detail message.
     * @param action the action to be executed.
     */
    public CommandException(String message, UiAction action) {
        super(message);
        this.action = requireNonNull(action);
    }

    /**
     * Constructs a new {@code CommandException} with the
     * specified detail {@code message} and {@code cause}.
     *
     * @param message the detail message.
     * @param cause the throwable that caused this exception.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code CommandException} with the
     * specified detail {@code message} and a {@code DoNothingAction}.
     *
     * @param message the detail message.
     */
    public CommandException(String message) {
        this(message, new DoNothingAction());
    }

    public void executeUiAction(MainWindow mainWindow) {
        action.execute(mainWindow);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandException // instanceof handles nulls
                && getMessage().equals(((CommandException) other).getMessage()) // state check
                && action.equals(((CommandException) other).action)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), action);
    }
}
