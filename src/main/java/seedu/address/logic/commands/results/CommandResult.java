package seedu.address.logic.commands.results;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.Person;

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

    public boolean isShowHelp() {
        return false;
    }

    public boolean isExit() {
        return false;
    }

    public boolean isSave() {
        return false;
    }

    public boolean isLoad() {
        return false;
    }

    public boolean isToShowNewPerson() {
        return false;
    }

    public Optional<Path> getFilePath() {
        return Optional.empty();
    }

    public Optional<Person> getDisplayPerson() {
        return Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp() == otherCommandResult.isShowHelp()
                && isExit() == otherCommandResult.isExit()
                && isLoad() == otherCommandResult.isLoad()
                && isSave() == otherCommandResult.isSave();
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp(), isExit(), isSave(),
                isLoad(), isToShowNewPerson(), getFilePath(), getDisplayPerson());
    }

}
