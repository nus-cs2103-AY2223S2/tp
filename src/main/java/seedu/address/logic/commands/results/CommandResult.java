package seedu.address.logic.commands.results;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Returns false by default.
     * Overridden by the HelpCommand.
     */
    public boolean isShowHelp() {
        return false;
    }

    /**
     * Returns false by default.
     * Overridden by the ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns false by default.
     * Overridden by the SaveCommand.
     */
    public boolean isSave() {
        return false;
    }

    /**
     * Returns false by default.
     * Overridden by the LoadCommand.
     */
    public boolean isLoad() {
        return false;
    }

    /**
     * Returns Optional.empty by default.
     * Overridden by SaveCommand and LoadCommand.
     */
    public Optional<Path> getFilePath() {
        return Optional.empty();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandResult // instanceof handles nulls
                && feedbackToUser.equals(((CommandResult) other).feedbackToUser)
                && isShowHelp() == ((CommandResult) other).isShowHelp()
                && isExit() == ((CommandResult) other).isExit()
                && isLoad() == ((CommandResult) other).isLoad()
                && isSave() == ((CommandResult) other).isSave());
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp(), isExit(), isSave(), isLoad());
    }

}
