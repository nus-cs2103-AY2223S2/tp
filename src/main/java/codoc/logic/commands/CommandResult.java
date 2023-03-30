package codoc.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Hidden code number to facilitate interaction with UI */
    private String code;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** New person added by user. Scroll to the latest added person */
    private boolean justAdded;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     *
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, String code) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.code = code;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Instantiates a new Command result.
     *
     * @param feedbackToUser the feedback to user
     * @param justAdded      whether a new person is added
     */
    public CommandResult(String feedbackToUser, boolean justAdded) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.justAdded = justAdded;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Optional<String> getCode() {
        return Optional.ofNullable(code);
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isJustAdded() {
        return justAdded;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
