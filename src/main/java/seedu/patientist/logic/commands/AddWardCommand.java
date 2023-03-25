package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.ward.Ward;


/**
 * Adds a hospital ward to patientist.
 */
public class AddWardCommand extends Command {
    public static final String COMMAND_WORD = "addward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ward to patientist. "
                                               + "Parameters: "
                                               + PREFIX_NAME + "NAME "
                                               + "Example: " + COMMAND_WORD + " "
                                               + PREFIX_NAME + "Block B Ward 2";

    public static final String MESSAGE_SUCCESS = "New ward added: %1$s";
    public static final String MESSAGE_DUPLICATE_WARD = "This ward already exists in the patientist book";

    private final Ward toAdd;

    /**
     * Creates an AddWardCommand to add the specified {@code Ward}
     *
     * @param ward The ward member to be created.
     */
    public AddWardCommand(Ward ward) {
        requireNonNull(ward);
        toAdd = ward;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasWard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_WARD);
        }

        model.addWard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof AddWardCommand // instanceof handles nulls
                   && toAdd.equals(((AddWardCommand) other).toAdd));
    }
}
