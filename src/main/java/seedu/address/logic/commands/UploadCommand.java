package seedu.address.logic.commands;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Uploads a file to CLIpboard. If file already exists in CLIpboard, old file will be replaced.
 * File will be saved to the data folder.
 */
public class UploadCommand extends Command {

    public static final String COMMAND_WORD = "upload";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Uploads a file from a specified absolute file path to CLIpboard.\n"
            + "Parameters: FILEPATH (must be a non-empty and valid file path)\n"
            + "Example: " + COMMAND_WORD + " /Users/AlexYeoh/Desktop/A0123456X.png";

    private static final String MESSAGE_INVALID_FILEPATH = "File path is not valid!";
    private static final String DESTINATION_FILEPATH = "./data";

    private final Path path;

    /**
     * @param path of target file that will be uploaded.
     */
    public UploadCommand(Path path) {
        requireNonNull(path);
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            Path sourcePath = path;
            Path destinationPath = Paths.get(DESTINATION_FILEPATH);
            Files.copy(sourcePath, destinationPath.resolve(sourcePath.getFileName()), REPLACE_EXISTING);
            return new CommandResult(generateSuccessMessage(sourcePath));
        } catch (IOException e) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        }
    }

    /**
     * Generates a command execution success message based on the
     * name of file that has been uploaded.
     * {@code addedPath}.
     */
    private String generateSuccessMessage(Path addedPath) {
        return "File " + addedPath.getFileName() + " successfully added to CLIpboard";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UploadCommand)) {
            return false;
        }

        // state check
        UploadCommand e = (UploadCommand) other;
        return path.equals(e.path);
    }

}
