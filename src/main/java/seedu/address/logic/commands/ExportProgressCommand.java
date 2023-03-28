package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exports a student's progress.
 */
public class ExportProgressCommand extends Command {
    public static final String COMMAND_WORD = "exportP";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports a student's progress.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_FILEPATH + "FILE PATH]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_FILEPATH + "C:\\Users\\John Doe\\Downloads\\MATHUTORING";

    public static final String MESSAGE_SUCCESS = "%1$s's progress report exported in %2$s with filename %3$s";

    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    private final Index targetIndex;
    private String filePath;

    /**
     * Creates an ExportProgressCommand to export the specified Person {@code Person}'s progress to a PDF file.
     */
    public ExportProgressCommand(Index targetIndex, String filePath) {
        this.targetIndex = targetIndex;
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= personList.size()) {
            logger.info("The student index is exceeding the total number of students. Index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToExport = personList.get(targetIndex.getZeroBased());
        String studentName = personToExport.getName().fullName;
        String fileName = studentName + "'s Progress Report.pdf";

        if (this.filePath.equals("")) {
            this.filePath = System.getProperty("user.home");
        }
        try {
            model.exportProgress(personToExport, String.valueOf(Paths.get(this.filePath, fileName)));
        } catch (IOException e) {
            throw new CommandException("Error!\n" + e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToExport.getName().fullName,
                this.filePath, fileName));
    }
}
