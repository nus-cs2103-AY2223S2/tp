package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.commands.CommandUtil.convertSetToList;
import static seedu.connectus.logic.commands.CommandUtil.isIndexValid;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;

/**
 * Deletes a tag from a person using their displayed indexes from ConnectUS.
 */
public class DeleteTagFromPersonCommand extends Command {
    public static final String COMMAND_WORD = "delete-t";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a tag from the person identified "
        + "by the index number used in the displayed person list. \n"
        + "Parameters: PERSON_INDEX (must be a positive integer) "
        + "[" + PREFIX_MODULE + "MODULE]... "
        + "[" + PREFIX_CCA + "CCA]... "
        + "[" + PREFIX_MAJOR + "MAJOR]... "
        + "[" + PREFIX_REMARK + "REMARK]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_MODULE + "1 "
        + PREFIX_CCA + "2 "
        + PREFIX_MAJOR + "1 "
        + PREFIX_REMARK + "1";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from Person: %1$s";

    private final Index personIndex;
    private final Index moduleIndex;
    private final Index ccaIndex;
    private final Index majorIndex;
    private final Index remarkIndex;

    /**
     * @param personIndex      of the person in the filtered person list to edit
     * @param moduleIndex      of the module in the module list to delete
     * @param ccaIndex         of the CCA in the CCA list to delete
     * @param majorIndex       of the major in the major list to delete
     * @param remarkIndex      of the remark in the remark list to delete
     */
    public DeleteTagFromPersonCommand(Index personIndex, Index moduleIndex, Index ccaIndex, Index majorIndex,
                                      Index remarkIndex) {
        requireNonNull(personIndex);
        this.personIndex = personIndex;
        this.moduleIndex = moduleIndex;
        this.ccaIndex = ccaIndex;
        this.majorIndex = majorIndex;
        this.remarkIndex = remarkIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        var lastShownList = model.getFilteredPersonList();

        if (!isIndexValid(personIndex, lastShownList)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        var personToEdit = lastShownList.get(personIndex.getZeroBased());

        var editedModules = personToEdit.getModules();
        var editedCcas = personToEdit.getCcas();
        var editedMajors = personToEdit.getMajors();
        var editedRemarks = personToEdit.getRemarks();

        if (moduleIndex != null) {
            var originalModules = convertSetToList(personToEdit.getModules());
            if (!isIndexValid(moduleIndex, originalModules)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "module"));
            }
            editedModules = createEditedTagList(originalModules, moduleIndex);
        }

        if (ccaIndex != null) {
            var originalCcas = convertSetToList(personToEdit.getCcas());
            if (!isIndexValid(ccaIndex, originalCcas)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "CCA"));
            }
            editedCcas = createEditedTagList(originalCcas, ccaIndex);
        }

        if (majorIndex != null) {
            var originalMajors = convertSetToList(personToEdit.getMajors());
            if (!isIndexValid(majorIndex, originalMajors)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "major"));
            }
            editedMajors = createEditedTagList(originalMajors, majorIndex);
        }

        if (remarkIndex != null) {
            var originalRemarks = convertSetToList(personToEdit.getRemarks());
            if (!isIndexValid(remarkIndex, originalRemarks)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "remark"));
            }
            editedRemarks = createEditedTagList(originalRemarks, remarkIndex);
        }

        var editedPerson = new Person(personToEdit, editedRemarks, editedModules, editedCcas, editedMajors);

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
        return Objects.equals(personIndex, e.personIndex)
            && Objects.equals(moduleIndex, e.moduleIndex)
            && Objects.equals(ccaIndex, e.ccaIndex)
            && Objects.equals(majorIndex, e.majorIndex)
            && Objects.equals(remarkIndex, e.remarkIndex);
    }
}
