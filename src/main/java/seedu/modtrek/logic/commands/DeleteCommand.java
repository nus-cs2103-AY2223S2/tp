package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.model.Model.PREDICATE_SHOW_NO_MODULES;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.exceptions.ModuleNotFoundException;

/**
 * Deletes a module identified using module code from ModTrek.
 */
public class DeleteCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "delete";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the module code\n"
            + "Parameters: {all} /m MODULE CODE\n"
            + "Example: " + COMMAND_WORD + " /m CS1101S";

    /**
     * The constant MESSAGE_DELETE_MODULE_SUCCESS.
     */
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Modules: %1$s";

    /**
     * The constant MESSAGE_DELETE_MODULE_NOT_FOUND.
     */
    public static final String MESSAGE_DELETE_MODULE_NOT_FOUND = "Could not find Module to delete: %1$s";

    /**
     * The constant MESSAGE_DELETE_ALL_MODULES_SUCCESS.
     */
    public static final String MESSAGE_DELETE_ALL_MODULES_SUCCESS = "All modules have been deleted!";

    private final Set<Code> targetCodes;

    private final boolean isAll;

    /**
     * Instantiates a new Delete command.
     *
     * @param isALl       the is a ll
     * @param targetCodes the target codes
     */
    public DeleteCommand(boolean isALl, Set<Code> targetCodes) {
        this.isAll = isALl;
        this.targetCodes = targetCodes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isAll) {
            model.setDegreeProgression(new DegreeProgression());
            return new CommandResult(MESSAGE_DELETE_ALL_MODULES_SUCCESS);
        }

        ObservableList<Module> lastShownList = model.getFilteredModuleList();

        for (Code code : targetCodes) {
            try {
                Module moduleToDelete = new Module(code);
                model.deleteModule(moduleToDelete);
            } catch (ModuleNotFoundException e) {
                throw new CommandException(String.format(MESSAGE_DELETE_MODULE_NOT_FOUND, code));
            }
        }

        model.updateFilteredModuleList(PREDICATE_SHOW_NO_MODULES);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, targetCodes));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetCodes.equals(((DeleteCommand) other).targetCodes)); // state check
    }
}
