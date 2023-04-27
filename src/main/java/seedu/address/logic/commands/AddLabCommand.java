package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Lab;

/**
 * Adds a lab to the events that the teaching assistant would like to schedule.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "vim";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to the address book. "
            + "Parameters: "
            + PREFIX_LAB + "LAB_NAME " + "[-date dd/MM/yyyy HH:mm] \n"
            + "Restrictions: Name has a maximum of 20 characters, and dates cannot clash \n"
            + "Example: " + COMMAND_WORD + " Lab/TreeMap -date 10/10/2025 10:00";

    public static final String MESSAGE_SUCCESS = "New lab added: %1$s";
    public static final String MESSAGE_DUPLICATE_LAB = "This lab already exists in the address book";

    private final Lab toAdd;

    /**
     * Creates an AddLab to add the specified {@code Lab}.
     *
     * @param lab the lab to be added.
     */
    public AddLabCommand(Lab lab) {
        requireNonNull(lab);
        toAdd = lab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLab(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LAB);
        }

        model.addLab(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand // instanceof handles nulls
                && toAdd.equals(((AddLabCommand) other).toAdd));
    }
}
