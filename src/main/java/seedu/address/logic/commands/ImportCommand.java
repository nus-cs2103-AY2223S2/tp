package seedu.address.logic.commands;

import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import static seedu.address.storage.ChsContacts.CHS_CONTACTS;
import static seedu.address.storage.SocContacts.SOC_CONTACTS;

/**
 * Adds a person to the address book.
 */



public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a list of contacts to the address book. "
            + "Parameters: FACULTY\n"
            + "Example: " + COMMAND_WORD + " SOC";

    public static final String MESSAGE_SUCCESS = "Contacts successfully imported";
    public static final String MESSAGE_DUPLICATE_IMPORT = " already exists in the address book";
    public static final String MESSAGE_NO_FACULTY = "Faculty not found!";

    private final String faculty;

    /**
     * Creates an ImportCommand to add the specified {@code Person}
     */
    public ImportCommand(String faculty) {
        requireNonNull(faculty);
        this.faculty = faculty;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String duplicates = "";
        ArrayList<Person> importList = new ArrayList<Person>();

        if (faculty.equalsIgnoreCase("soc")) {
            importList = SOC_CONTACTS;
        } else if (faculty.equalsIgnoreCase("chs")) {
            importList = CHS_CONTACTS;
        } else {
            throw new CommandException(MESSAGE_NO_FACULTY);
        }

        for (Person toAdd : importList) {
            if (model.hasPerson(toAdd)) {
                duplicates += toAdd.getName() + MESSAGE_DUPLICATE_IMPORT + "\n";
                continue;
            }
            model.addPerson(toAdd);
        }
        if (duplicates.length() > 0) {
            throw new CommandException(duplicates);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand);
    }
}
