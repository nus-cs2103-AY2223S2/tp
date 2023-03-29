package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.jobs.ImportDeliveryJobCommandParser;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportDeliveryJobCommand extends Command {

    public static final String COMMAND_WORD = "import_job";
    public static final String MESSAGE_EMPTY_FILE = "File is empty";
    public static final String MESSAGE_SUCCESS = "File is imported";

    private final File toAdd;

    public ImportDeliveryJobCommand(File file) {
        requireNonNull(file);
        toAdd = file;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException, FileNotFoundException {
        requireNonNull(model);

        if (toAdd.length() == 0) {
            throw new CommandException(MESSAGE_EMPTY_FILE);
        }

        List<DeliveryJob> listOfAddDeliveryJob;
        listOfAddDeliveryJob = ImportDeliveryJobCommandParser.parse(toAdd);

        for (int i = 0; i < listOfAddDeliveryJob.size(); i++) {
            model.addDeliveryJob(listOfAddDeliveryJob.get(i));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

