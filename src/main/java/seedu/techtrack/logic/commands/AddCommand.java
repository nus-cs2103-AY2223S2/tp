package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.role.Role;

/**
 * Adds a role to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a role to TechTrack. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CONTACT + "CONTACT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_JOBDESCRIPTION + "JOB DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]... "
            + PREFIX_WEBSITE + "WEBSITE "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_EXPERIENCE + "EXPERIENCE \n \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Software Engineer "
            + PREFIX_CONTACT + "98765432 "
            + PREFIX_EMAIL + "google@example.com "
            + PREFIX_COMPANY + "Google "
            + PREFIX_TAG + "Java "
            + PREFIX_TAG + "Golang "
            + PREFIX_WEBSITE + "www.google.com "
            + PREFIX_JOBDESCRIPTION + "Data Engineering team - penultimate students preferred "
            + PREFIX_SALARY + "4000 "
            + PREFIX_DEADLINE + "2023-10-20 "
            + PREFIX_EXPERIENCE + "Javascript - 1 Year ";

    public static final String MESSAGE_SUCCESS = "New role added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This role already exists in the Techtrack.";

    private final Role toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Role}
     */
    public AddCommand(Role role) {
        requireNonNull(role);
        toAdd = role;
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasRole(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addRole(toAdd);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
