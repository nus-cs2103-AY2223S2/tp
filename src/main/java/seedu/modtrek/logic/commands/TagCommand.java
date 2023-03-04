package seedu.modtrek.logic.commands;

import static seedu.modtrek.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.modtrek.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Set;

import seedu.modtrek.commons.core.Messages;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.module.Module;

public class TagCommand extends Command{
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_ARGUMENTS = "Module Code: %1$d, Tag: %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tags to the module identified "
            + "Parameters: CODE (module code) "
            + " [code]\n"
            + "Example: " + COMMAND_WORD + " CS1101S "
            + "t/ UNIVERSITY LEVEL REQUIREMENT.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Tag command not implemented yet";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Module: %1$s";
    public static final String MESSAGE_ADD_TAG_FAILURE = "Oops tag is empty";

    private final Code code;
    private final Tag tag;

    /**
     * @param code of the module in the filtered module list to edit the tag
     * @param tag of the module to be updated to
     */
    public TagCommand(Code code, Tag tag) {
        requireAllNonNull(code, tag);

        this.code = code;
        this.tag = tag;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Module> lastShownList = model.getFilteredModuleList();

        Module moduleToEdit = new Module(code);
        if (!lastShownList.contains(moduleToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Set<Tag> newTags = moduleToEdit.getTags();
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
        return String.format(message, moduleToEdit);
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
