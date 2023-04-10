package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.internship.Internship;

/**
 * Adds an internship to InternBuddy.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to InternBuddy. "
            + "Fields: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_ROLE + "Software Engineer "
            + PREFIX_STATUS + "Applied "
            + PREFIX_DATE + "2023-02-01 "
            + PREFIX_COMMENT + "I like the company culture! "
            + PREFIX_TAG + "Go "
            + PREFIX_TAG + "Java";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in InternBuddy";

    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Internship}
     */
    public AddCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        //Add internship
        model.addInternship(toAdd);
        //Update right panel
        model.updateSelectedInternship(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
