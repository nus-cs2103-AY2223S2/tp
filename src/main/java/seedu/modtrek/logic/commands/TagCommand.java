package seedu.modtrek.logic.commands;

import static seedu.modtrek.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.modtrek.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Set;

import seedu.modtrek.commons.core.Messages;
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
            + ": Adds tags to the module identified "
            + "Parameters: CODE (module code) "
            + "Example: " + COMMAND_WORD + " CS1101S "
            + "/t UNIVERSITY LEVEL REQUIREMENTS";

    /**
     * The constant MESSAGE_NOT_IMPLEMENTED_YET.
     */
    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Tag command not implemented yet";
    /**
     * The constant MESSAGE_ADD_TAG_SUCCESS.
     */
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Module: %1$s";
    /**
     * The constant MESSAGE_ADD_TAG_FAILURE.
     */
    public static final String MESSAGE_ADD_TAG_FAILURE = "Oops tag is empty";

    private final Code code;
    private final Tag tag;

    /**
     * Instantiates a new Tag command.
     *
     * @param code of the module in the filtered module list to edit the tag
     * @param tag  of the module to be updated to
     */
    public TagCommand(Code code, Tag tag) {
        requireAllNonNull(code, tag);

        this.code = code;
        this.tag = tag;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Module> lastShownList = model.getFilteredModuleList();

        // possibly make module list a hash set instead of list
        Module moduleToEdit = new Module(code);
        int index = lastShownList.indexOf(moduleToEdit);
        moduleToEdit = lastShownList.get(index);
        if (!lastShownList.contains(moduleToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Set<Tag> newTags = moduleToEdit.getModifiableTags();
        newTags.add(tag);

        Module editedModule = new Module(
                moduleToEdit.getCode(), moduleToEdit.getCredit(), moduleToEdit.getSemYear(),
                newTags, moduleToEdit.getGrade());

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        return new CommandResult(generateSuccessMessage(editedModule));
    }

    /**
     * Generates a command execution success message based on whether
     * the tag is added to or removed from
     * {@code moduleToEdit}.
     */
    private String generateSuccessMessage(Module moduleToEdit) {
        String message = !tag.tagName.isEmpty() ? MESSAGE_ADD_TAG_SUCCESS : MESSAGE_ADD_TAG_FAILURE;
        return String.format(message, moduleToEdit.getCode().toString());
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
                && tag.equals(e.tag);
    }
}
