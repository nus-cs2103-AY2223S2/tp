package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes multiple modules identified using their resepective module codes
 * If one or more of the modules do not exist, nothing happens.
 */
public class DeleteMultipleModulesCommand extends DeleteMultipleCommand {

    public static final String MESSAGE_SUCCESS = "Several Modules deleted";
    private final ArrayList<ModuleCode> targetModuleCodes;

    /**
     * Creates an executable Command that deletes multiple modules of {@code moduleCodes}
     * @param moduleCode
     */
    public DeleteMultipleModulesCommand(ModuleCode ... moduleCodes) {
        ArrayList<ModuleCode> moduleCodesArr = new ArrayList<ModuleCode>();
        for (ModuleCode each: moduleCodes) {
            moduleCodesArr.add(each);
        }

        this.targetModuleCodes = moduleCodesArr;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isValidModCodes = true;
        for (ModuleCode each: this.targetModuleCodes) {
            isValidModCodes = isValidModCodes && model.hasModule(each);
        }

        if (isValidModCodes) {
            for (ModuleCode each: this.targetModuleCodes) {
                DeleteModuleCommand dmc = new DeleteModuleCommand(each);
                dmc.execute(model);
            }
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(Messages.MESSAGE_MODULES_DONT_EXIST);
        }
    }
}
