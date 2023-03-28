package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.commands.CommandUtil.convertSetToList;
import static seedu.connectus.logic.commands.CommandUtil.isIndexValid;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.connectus.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Deletes a tag from a person using their displayed indexes from ConnectUS.
 */
public class DeleteTagFromPersonCommand extends Command {
    public static final String COMMAND_WORD = "deletet";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a tag from the person identified"
        + "by the index number used in the displayed person list. \n"
        + "Parameters: PERSON_INDEX (must be a positive integer) "
        + "[" + PREFIX_TAG + "TAG_INDEX] "
        + "[" + PREFIX_MODULE + "MODULE_INDEX]"
        + "\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "1 "
        + PREFIX_MODULE + "1";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from Person: %1$s";

    private final Index personIndex;
    private final Index tagIndex;
    private final Index moduleIndex;

    /**
     * @param personIndex            of the person in the filtered person list to edit
     * @param tagIndex         of the tag in the tag list to delete
     * @param moduleIndex      of the module in the module list to delete
     */
    public DeleteTagFromPersonCommand(Index personIndex, Index tagIndex, Index moduleIndex) {
        requireNonNull(personIndex);
        this.personIndex = personIndex;
        this.tagIndex = tagIndex;
        this.moduleIndex = moduleIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        var lastShownList = model.getFilteredPersonList();

        if (!isIndexValid(personIndex, lastShownList)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        var personToEdit = lastShownList.get(personIndex.getZeroBased());

        Set<Tag> editedTags = personToEdit.getTags();
        Set<Module> editedModules = personToEdit.getModules();

        if (tagIndex != null) {
            var originalTags = convertSetToList(personToEdit.getTags());
            if (!isIndexValid(tagIndex, originalTags)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "tag"));
            }
            editedTags = createEditedTagList(originalTags, tagIndex);
        }

        if (moduleIndex != null) {
            var originalModules = convertSetToList(personToEdit.getModules());
            if (!isIndexValid(moduleIndex, originalModules)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "module"));
            }
            editedModules = createEditedTagList(originalModules, moduleIndex);
        }


        var editedPerson = new Person(personToEdit, editedTags, editedModules);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, editedPerson));
    }

    private <T> Set<T> createEditedTagList(List<T> tags, Index tagIndex) {
        tags.remove(tagIndex.getZeroBased());
        return new HashSet<>(tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagFromPersonCommand)) {
            return false;
        }

        // state check
        var e = (DeleteTagFromPersonCommand) other;
        return personIndex.equals(e.personIndex)
            && tagIndex.equals(e.tagIndex)
            && moduleIndex.equals(e.moduleIndex);
    }
}
