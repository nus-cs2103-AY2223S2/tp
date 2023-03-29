package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * Boolean to switch between screens
     */
    public final boolean isDisplayAllModules;
    /**
     * The Is display filtered modules.
     */
    public final boolean isDisplayFilteredModules;
    /**
     * The Is display progress.
     */
    public final boolean isDisplayProgress;

    private final String feedbackToUser;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser           the feedback to user
     * @param isDisplayAllModules      the is display all modules
     * @param isDisplayProgress        the is display progress
     * @param isDisplayFilteredModules the is display filtered modules
     * @param exit                     the exit
     */
    public CommandResult(String feedbackToUser, boolean isDisplayAllModules,
            boolean isDisplayProgress, boolean isDisplayFilteredModules, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isDisplayAllModules = isDisplayAllModules;
        this.isDisplayProgress = isDisplayProgress;
        this.isDisplayFilteredModules = isDisplayFilteredModules;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser the feedback to user
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, true, false, false,
                false);
    }

    /**
     * Gets feedback to user.
     *
     * @return the feedback to user
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public boolean isExit() {
        return exit;
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
                && isDisplayAllModules == otherCommandResult.isDisplayAllModules
                && isDisplayFilteredModules == otherCommandResult.isDisplayFilteredModules
                && isDisplayProgress == otherCommandResult.isDisplayProgress
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isDisplayAllModules, isDisplayFilteredModules, isDisplayProgress, exit);
    }

}
