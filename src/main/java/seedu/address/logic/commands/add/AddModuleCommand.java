package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the tracker.
 */
public class AddModuleCommand extends AddCommand {

    /** The message for when a {@code Module} is successfully added. */
    public static final String MESSAGE_SUCCESS = "New module added: %1$s";

    /** The error message for when a duplicate {@code Module} is detected. */
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the tracker.";

    private final Module toAdd;

    /**
     * Constructs an {@code AddModuleCommand} to add {@code module} to the tracker.
     *
     * @param module The module to be added.
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        validateModuleIsNotDuplicate(model);

        model.addModule(toAdd);

        return createSuccessResult();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddModuleCommand
                        && toAdd.equals(((AddModuleCommand) other).toAdd));
    }

    private void validateModuleIsNotDuplicate(Model model) throws CommandException {
        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
    }

    private CommandResult createSuccessResult() {
        String message = String.format(MESSAGE_SUCCESS, toAdd);
        ModuleEditInfo editInfo = new ModuleEditInfo(null, toAdd);

        return new CommandResult(message, editInfo);
    }

}
