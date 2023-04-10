package seedu.vms.logic;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandMessage {

    private final String message;
    private final State state;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;


    /**
     * Constructs a {@code CommandResult} with {@code showHelp} and {@code exit}
     * set to their default value.
     */
    public CommandMessage(String message, State state) {
        this(message, false, false, state);
    }


    /**
     * Constructs a {@code CommandResult} with {@code state} set to
     * {@link State#INFO}.
     */
    public CommandMessage(String message, boolean showHelp, boolean exit) {
        this(message, showHelp, exit, State.INFO);
    }


    /**
     * Constructs a {@code CommandResult}.
     */
    public CommandMessage(String message, boolean showHelp, boolean exit, State state) {
        this.message = requireNonNull(message);
        this.state = state;
        this.showHelp = showHelp;
        this.exit = exit;
    }


    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandMessage(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getMessage() {
        return message;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandMessage)) {
            return false;
        }

        CommandMessage otherCommandResult = (CommandMessage) other;
        return message.equals(otherCommandResult.message)
                && state.equals(otherCommandResult.state)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, showHelp, exit);
    }





    /**
     * Represents the state of a {@code CommandResult}.
     */
    public static enum State {
        INFO, WARNING, ERROR, DEATH
    }
}
