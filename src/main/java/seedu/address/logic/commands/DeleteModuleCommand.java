package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Deletes a module identified using its unique module code
 */
public class DeleteModuleCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            "(1) Deletes the module identified by the module code.\n"
            + "Parameters: module code\n"
            + "Example: " + COMMAND_WORD + " CS2040S\n";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final ModuleCode targetModuleCode;

    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyModule moduleToDelete = model.getTracker().getModule(targetModuleCode);

        if (moduleToDelete.equals(null)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, targetModuleCode));
        }

        model.deleteModule(moduleToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }
}
