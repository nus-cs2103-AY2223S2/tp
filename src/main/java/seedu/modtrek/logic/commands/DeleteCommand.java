package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;

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
            + ": Deletes the module identified by the module code.\n"
            + "Parameters: {all} /m MODULE CODE\n"
            + "Example: " + COMMAND_WORD + " /m CS1101S";

    /**
     * The constant MESSAGE_DELETE_MODULE_SUCCESS.
     */
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module(s): %1$s";

    /**
     * The constant MESSAGE_DELETE_MODULE_NOT_FOUND.
     */
    public static final String MESSAGE_DELETE_MODULE_NOT_FOUND = "Could not find Module(s) to delete: %1$s";

    /**
     * The constant MESSAGE_DELETE_ALL_MODULES_SUCCESS.
     */
    public static final String MESSAGE_DELETE_ALL_MODULES_SUCCESS = "All modules have been deleted!";

    private final Set<Code> targetCodes;

    private final boolean isAll;

    /**
     * Instantiates a new Delete command.
     *
     * @param isAll a flag for whether it is a "delete all" command
     * @param targetCodes the target codes
     */
    public DeleteCommand(boolean isAll, Set<Code> targetCodes) {
        this.isAll = isAll;
        this.targetCodes = targetCodes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isAll) {
            model.setDegreeProgression(new DegreeProgression());
            return new CommandResult(MESSAGE_DELETE_ALL_MODULES_SUCCESS);
        }

        Set<Code> codesFound = new HashSet<>();
        Set<Code> codesNotFound = new HashSet<>();
        for (Code code : targetCodes) {
            Module moduleToDelete = new Module(code);
            if (!model.hasModule(moduleToDelete)) {
                codesNotFound.add(code);
                continue;
            }
            codesFound.add(code);
            model.deleteModule(moduleToDelete);
        }

        String deletedModules = String.format(MESSAGE_DELETE_MODULE_SUCCESS, codesFound);
        String notFoundModules = String.format(MESSAGE_DELETE_MODULE_NOT_FOUND, codesNotFound);

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(deletedModules + "\n" + notFoundModules);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetCodes.equals(((DeleteCommand) other).targetCodes)); // state check
    }
}
