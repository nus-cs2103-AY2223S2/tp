package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import static seedu.address.storage.SocContacts.SOC_CONTACTS;
import static seedu.address.storage.ChsContacts.CHS_CONTACTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Adds a person to the address book.
 */



public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a list of contacts to the address book. "
            + "Parameters: FACULTY\n"
            + "Example: " + COMMAND_WORD + " SOC";

    public static final String MESSAGE_SUCCESS = "Contacts successfully imported";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
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
        if (faculty.equalsIgnoreCase("soc")) {
            for (Person toAdd : SOC_CONTACTS) {
                if (model.hasPerson(toAdd)) {
                    throw new CommandException(MESSAGE_DUPLICATE_PERSON);
                }
                model.addPerson(toAdd);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        } else if (faculty.equalsIgnoreCase("chs")) {
            for (Person toAdd : CHS_CONTACTS) {
                if (model.hasPerson(toAdd)) {
                    throw new CommandException(MESSAGE_DUPLICATE_PERSON);
                }
                model.addPerson(toAdd);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        }
        throw new CommandException(MESSAGE_NO_FACULTY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand);
    }
}
