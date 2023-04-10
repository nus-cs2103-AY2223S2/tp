package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Deletes a module identified using its unique module code
 */
public class DeleteModuleCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            "(1) Deletes one or more modules from the tracker.\n"
            + "Parameters: {module code_1}[, {module_code_2}[, ...]]\n"
            + "Example: " + COMMAND_WORD + " CS2040S";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final ModuleCode targetModuleCode;

    /**
     * Creates a Delete Module Command executable that deletes a module of {@code targetModuleCode}
     *
     * @param targetModuleCode Module Code of module to delete
     */
    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(targetModuleCode)) {

            ReadOnlyModule moduleToDelete = model.getModule(targetModuleCode);
            model.deleteModule(moduleToDelete);

            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, targetModuleCode),
                    new ModuleEditInfo(moduleToDelete, null));

        } else {

            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, targetModuleCode));

        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }
}
