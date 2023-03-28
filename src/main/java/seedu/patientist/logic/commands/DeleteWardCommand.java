package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.ward.Ward;

/**
 * Deletes a ward identified using its name.
 */
public class DeleteWardCommand extends Command {
    public static final String COMMAND_WORD = "delward";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                               + ": Deletes the ward identified by its name. Ward must be empty.\n"
                                               + "Parameters: " + PREFIX_NAME + "NAME (CASE-SENSITIVE)\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + PREFIX_NAME + "Block A Ward 1";

    public static final String MESSAGE_DUPLICATE_WARD = "This ward doesn't exists in the patientist book";
    public static final String MESSAGE_NON_EMPTY_WARD = "This ward is not empty";

    public static final String MESSAGE_DELETE_WARD_SUCCESS = "Deleted Ward: %1$s";

    private final String targetString;

    public DeleteWardCommand(String targetString) {
        this.targetString = targetString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);



        Ward wardToDelete = model.getWard(targetString);

        if (wardToDelete == null) {
            throw new CommandException(MESSAGE_DUPLICATE_WARD);
        }
        if (!wardToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_NON_EMPTY_WARD);
        }

        model.deleteWard(wardToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_WARD_SUCCESS, wardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof DeleteWardCommand // instanceof handles nulls
                   && targetString.equals(((DeleteWardCommand) other).targetString)); // state check
    }
}
