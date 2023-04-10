package seedu.modtrek.logic.commands;

import static seedu.modtrek.commons.core.Messages.MESSAGE_MODULE_MISSING;
import static seedu.modtrek.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Set;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;

/**
 * Tags modules with valid tags that correspond to different areas in degree progression
 */
public class TagCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "tag";

    /**
     * The constant MESSAGE_ARGUMENTS.
     */
    public static final String MESSAGE_ARGUMENTS = "Module Code: %1$d, Tag: %2$s";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tags to the module identified.\n\n"
            + "Parameters: <MODULE_CODE> include/remove /t <TAG>...\n\n"
            + "Example 1: " + COMMAND_WORD + " CS1101S " + "include " + "/t ULR\n"
            + "Example 2: " + COMMAND_WORD + " MA2001 " + "remove " + "/t CSF";

    /**
     * The constant MESSAGE_ADD_TAG_SUCCESS.
     */
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Module: %1$s";

    /**
     * The constant MESSAGE_ADD_TAG_FAILURE.
     */
    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Removed tag from Module: %1$s";

    public static final String MESSAGE_MISSING_PREFIX = "Missing tag prefix /t.\n\n"
            + "Example: tag cs2109s include /t CSF /t CSBD";
    public static final String MESSAGE_TAG_MODULE_FAIL = "Module %1$s is not yet added.";

    private final Code code;
    private final boolean isInclude;
    private final Set<Tag> tags;

    /**
     * Instantiates a new Tag command.
     *  @param code of the module in the filtered module list to edit the tag
     * @param isInclude boolean to indicate if tags are to be removed or added
     * @param tags  of the module to be updated to
     */
    public TagCommand(Code code, boolean isInclude, Set<Tag> tags) {
        requireAllNonNull(code, tags);

        this.code = code;
        this.isInclude = isInclude;
        this.tags = tags;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Module> lastShownList = model.getDegreeProgression().getModuleList();

        // possibly make module list a hash set instead of list
        Module moduleToEdit = new Module(code);
        int index = lastShownList.indexOf(moduleToEdit);
        if (index < 0) {
            throw new CommandException(String.format(MESSAGE_MODULE_MISSING, moduleToEdit.getCode()));
        }
        moduleToEdit = lastShownList.get(index);

        Set<Tag> newTags = moduleToEdit.getModifiableTags();
        if (isInclude) {
            newTags.addAll(tags);
        } else {
            newTags.removeAll(tags);
        }

        Module editedModule = new Module(
                moduleToEdit.getCode(), moduleToEdit.getCredit(), moduleToEdit.getSemYear(),
                newTags, moduleToEdit.getGrade());

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(model.getPredicate());

        return new CommandResult(generateSuccessMessage(editedModule));
    }

    /**
     * Generates a command execution success message based on whether
     * the tag is added to or removed from
     * {@code editedModule}.
     */
    private String generateSuccessMessage(Module editedModule) {
        String message = isInclude ? MESSAGE_ADD_TAG_SUCCESS : MESSAGE_REMOVE_TAG_SUCCESS;
        return String.format(message, editedModule.getCode().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return code.equals(e.code)
                && isInclude == e.isInclude
                && tags.equals(e.tags);
    }
}
