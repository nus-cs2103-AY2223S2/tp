package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.jobs.ImportDeliveryJobCommandParser;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Person;

/**
 * Mass imports delivery jobs from CSV file to the delivery job system.
 */
public class ImportDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD = "import_job";
    public static final String MESSAGE_EMPTY_FILE = "File is empty";
    public static final String MESSAGE_SUCCESS = "File is imported";
    public static final String MESSAGE_WRONG_FILE = "File type is unsupported. Please upload a CSV file and check if "
            + "file extension is named .csv";

    private final File toAdd;

    /**
     * Creates an ImportDeliveryJobCommand to mass add delivery jobs
     * from file.
     */
    public ImportDeliveryJobCommand(File file) {
        requireNonNull(file);
        toAdd = file;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException, FileNotFoundException {
        requireNonNull(model);
        List<Person> listOfCustomers = new ArrayList<>();

        if (!getFileExtensions(toAdd).equals("csv")) {
            throw new CommandException(MESSAGE_WRONG_FILE);
        }
        if (toAdd.length() == 0) {
            throw new CommandException(MESSAGE_EMPTY_FILE);
        }

        List<DeliveryJob> listOfAddDeliveryJob;
        listOfAddDeliveryJob = ImportDeliveryJobCommandParser.parse(toAdd, listOfCustomers);

        for (int i = 0; i < listOfCustomers.size(); i++) {
            Person customer = listOfCustomers.get(i);
            if (!model.hasPerson(customer) && !customer.getPersonId().equals("null")) {
                model.addPerson(customer);
            }
        }

        for (int i = 0; i < listOfAddDeliveryJob.size(); i++) {
            if (!model.hasDeliveryJob(listOfAddDeliveryJob.get(i))) {
                model.addDeliveryJob(listOfAddDeliveryJob.get(i));
            }
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Gets type of file extension.
     *
     * @param file File that extension is checked
     * @return String file extension name
     */
    public String getFileExtensions(File file) {
        String fileName = file.getName();
        String extension = "";
        int i = fileName.lastIndexOf(".");
        extension = fileName.substring(i + 1, fileName.length());
        return extension;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportDeliveryJobCommand // instanceof handles nulls
                && toAdd.equals(((ImportDeliveryJobCommand) other).toAdd));
    }
}

