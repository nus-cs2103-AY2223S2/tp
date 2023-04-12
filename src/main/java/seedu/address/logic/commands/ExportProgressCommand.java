package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Exports a student's progress.
 */
public class ExportProgressCommand extends Command {
    public static final String COMMAND_WORD = "exportp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports a student's progress.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_FILEPATH + "FILE PATH TO DIRECTORY]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_FILEPATH + Paths.get("").toAbsolutePath();

    public static final String MESSAGE_SUCCESS = "%1$s's progress report exported in %2$s with filename %3$s";

    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    private final Index targetIndex;
    private String filePath;

    /**
     * Creates an ExportProgressCommand to export the specified Student {@code Student}'s progress to a PDF file.
     */
    public ExportProgressCommand(Index targetIndex, String filePath) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= studentList.size()) {
            logger.info("The student index is exceeding the total number of students. Index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToExport = studentList.get(targetIndex.getZeroBased());
        String studentName = studentToExport.getName().fullName;
        String fileName = studentName + "'s Progress Report.pdf";

        if (this.filePath.equals("")) {
            this.filePath = "data";
        }

        Path parentDir = Paths.get(this.filePath).toAbsolutePath().getParent();

        if (parentDir != null) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                throw new CommandException(e.getMessage());
            }
        }

        try {
            model.exportProgress(studentToExport, String.valueOf(Paths.get(this.filePath, fileName).toAbsolutePath()));
        } catch (IOException e) {
            throw new CommandException("Error!\n" + e.getMessage());
        }
        if (this.filePath.equals("data")) {
            this.filePath = Paths.get("data").toAbsolutePath().toString();
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToExport.getName().fullName,
                this.filePath, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportProgressCommand // instanceof handles nulls
                && targetIndex.equals(((ExportProgressCommand) other).targetIndex)); // state check
    }
}
