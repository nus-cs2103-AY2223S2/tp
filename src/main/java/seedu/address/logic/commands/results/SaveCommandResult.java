package seedu.address.logic.commands.results;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * CommandResult to be shown when executing a SaveCommand.
 */
public class SaveCommandResult extends CommandResult {

    /** Path to save the EduMate */
    private final Path filePath;

    /**
     * Constructs a {@code SaveCommandResult} with the specified fields.
     */
    public SaveCommandResult(String feedbackToUser, Path filePath) {
        super(feedbackToUser);
        requireNonNull(filePath);

        this.filePath = filePath;
    }

    @Override
    public boolean isSave() {
        return true;
    }

    @Override
    public Optional<Path> getFilePath() {
        return Optional.of(filePath);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && other instanceof SaveCommandResult
                && filePath.equals(((SaveCommandResult) other).filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath);
    }
}
