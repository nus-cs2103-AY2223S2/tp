package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.model.module.Module;

/**
 * A utility class for {@code Module}.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     *
     * @param module The module to be added.
     * @return An add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of the command string for the given {@code module}'s details.
     *
     * @param module The module.
     * @return The part of the command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(module.getCode() + " ");
        sb.append(PREFIX_NAME + module.getName().name);

        return sb.toString();
    }

}
