package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ward.Ward;

/**
 * Adds a ward to MedInfo.
 */
public class AddWardCommand extends Command {

    public static final String COMMAND_WORD = "addward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ward to MedInfo. \n"
            + "Parameters: "
            + PREFIX_WARD + "WARD "
            + "[" + PREFIX_CAPACITY + "CAPACITY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WARD + "A03 " + PREFIX_CAPACITY + "100\n";

    public static final String MESSAGE_SUCCESS = "New ward added: %1$s";
    public static final String MESSAGE_DUPLICATE_WARD = "This ward already exists in MedInfo";

    private final Ward toAdd;

    /**
     * Constructs a new {@code AddWardCommand} to add the specified {@code Ward}.
     */
    public AddWardCommand(Ward ward) {
        requireNonNull(ward);
        toAdd = ward;
    }

    /**
     * Executes the {@code AddWardCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
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
