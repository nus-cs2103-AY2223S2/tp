package seedu.address.logic.commands.results;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * CommandResult to be shown when executing a LoadCommand.
 */
public class LoadCommandResult extends CommandResult {

    /** Path to save the EduMate */
    private final Path filePath;

    /**
     * Constructs a {@code LoadCommandResult} with the specified fields.
     */
    public LoadCommandResult(String feedbackToUser, Path filePath) {
        super(feedbackToUser);
        requireNonNull(filePath);

        this.filePath = filePath;
    }

    @Override
    public boolean isLoad() {
        return true;
    }

    @Override
    public Optional<Path> getFilePath() {
        return Optional.of(filePath);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && other instanceof LoadCommandResult
                && filePath.equals(((LoadCommandResult) other).filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath);
    }
}
