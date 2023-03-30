package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Adds an application to the internship tracker.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship application to the tracker.\n"
            + "Compulsory Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_JOB_TITLE + "JOB_TITLE\n"
            + "Optional Parameters: "
            + PREFIX_REVIEW + "REVIEW "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_PROGRAMMING_LANGUAGE + "PROGRAMMING_LANGUAGE "
            + PREFIX_QUALIFICATION + "QUALIFICATION "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_RATING + "RATING "
            + PREFIX_NOTE + "NOTE "
            + PREFIX_REFLECTION + "REFLECTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "LinkedIn "
            + PREFIX_JOB_TITLE + "Software Engineer "
            + PREFIX_REVIEW + "high learning curve, fast paced environment "
            + PREFIX_LOCATION + "Clementi";

    public static final String MESSAGE_SUCCESS = "New internship application added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship application "
                                                            + "already exists in the address book";

    private final InternshipApplication toAdd;

    /**
     * Creates an AddCommand to add the specified {@code InternshipApplication}
     */
    public AddCommand(InternshipApplication application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
