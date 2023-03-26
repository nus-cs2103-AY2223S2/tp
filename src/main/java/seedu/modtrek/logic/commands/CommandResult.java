package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public enum Sort { SEMYEAR, GRADE, CREDIT, CODE, TAG };
    public static final Sort DEFAULT_SORT = Sort.SEMYEAR;

    /** Boolean to switch between screens */
    public final boolean isDisplayAllModules;
    public final boolean isDisplayFilteredModules;
    public final boolean isDisplayProgress;

    public final Sort sort;

    private final String feedbackToUser;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isDisplayAllModules,
            boolean isDisplayProgress, boolean isDisplayFilteredModules, Sort sort, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isDisplayAllModules = isDisplayAllModules;
        this.isDisplayProgress = isDisplayProgress;
        this.isDisplayFilteredModules = isDisplayFilteredModules;
        this.sort = sort;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, true, false, false, DEFAULT_SORT,
                false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

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
