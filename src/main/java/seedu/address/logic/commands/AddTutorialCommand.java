package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Tutorial;

/**
 * Adds a tutorial to the events that the teaching assistant would like to schedule.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "touch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the address book. "
            + "Parameters: "
            + PREFIX_TUTORIAL + "TUTORIAL_NAME " + "[-date dd/MM/yyyy HH:mm] \n"
            + "Restrictions: Name has a maximum of 20 characters, and dates cannot clash \n"
            + "Example: " + COMMAND_WORD + " Tutorial/Dijkstra -date 10/10/2024 10:00";

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the address book";

    private final Tutorial toAdd;

    /**
     * Creates an AddTutorial to add the specified {@code Tutorial}.
     *
     * @param tutorial the tutorial to add.
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorial(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTutorialCommand // instanceof handles nulls
                && toAdd.equals(((AddTutorialCommand) other).toAdd));
    }
}
