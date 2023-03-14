package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.Part;

/**
 * Command that manages adding parts
 */
public class AddPartCommand extends Command {

    public static final String COMMAND_WORD = "addpart";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a part to the shop. "
            + "Parameters: "
            + PREFIX_PART_NAME + "PART NAME "
            + PREFIX_QUANTITY + "PART QUANTITY "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PART_NAME + "Cylinder Head "
            + PREFIX_QUANTITY + "50";

    public static final String MESSAGE_SUCCESS = "New part added: %1$s";
    public static final String MESSAGE_DUPLICATE_PART = "%s quantity increased";

    private final Part toAdd;

    /**
     * Constructs command that adds vehicle to the model
     *
     * @param part Part to be added
     */
    public AddPartCommand(Part part) {
        this.toAdd = part;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPart(toAdd)) {
            model.addPart(toAdd);
            return new CommandResult(String.format(MESSAGE_DUPLICATE_PART, toAdd));
        }

        model.addPart(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPartCommand // instanceof handles nulls
                && toAdd.equals(((AddPartCommand) other).toAdd));
    }
}
