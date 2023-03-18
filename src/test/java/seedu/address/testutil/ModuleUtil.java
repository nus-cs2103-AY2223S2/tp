package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

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

    /**
     * Returns an edit command string for editing the module with code {@code moduleCode} using details in
     * {@code descriptor}.
     *
     * @param moduleCode The code of the module to be edited.
     * @param descriptor The details on how to edit the module.
     * @return An edit command string for editing the module with code {@code moduleCode} using details in
     * {@code descriptor}.
     */
    public static String getEditCommand(ModuleCode moduleCode, EditModuleDescriptor descriptor) {
        return EditModuleCommand.COMMAND_WORD + " " + moduleCode.toString() + " "
                + getEditModuleDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of the command string for the given {@code descriptor}'s details.
     *
     * @param descriptor The descriptor.
     * @return The part of the command string for the given {@code descriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCode().ifPresent(code -> sb.append(PREFIX_CODE).append(" " + code.code).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(" " + name.name).append(" "));
        return sb.toString();
    }

}
