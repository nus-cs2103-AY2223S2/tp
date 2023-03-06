package seedu.address.logic.commands.crew;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;

/**
 * Adds a crew to the address book.
 */
public class AddCrewCommand implements Command {

    public static final String COMMAND_WORD = "add crew";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a crew to the crew list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New crew added: %1$s";
    public static final String MESSAGE_DUPLICATE_CREW = "This crew already exists in the crew list";

    private final Crew toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCrewCommand(Crew crew) {
        requireNonNull(crew);
        toAdd = crew;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCrew(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CREW);
        }

        model.addCrew(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCrewCommand // instanceof handles nulls
                && toAdd.equals(((AddCrewCommand) other).toAdd));
    }
}
