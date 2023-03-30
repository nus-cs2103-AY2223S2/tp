package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.result.ResultDisplay;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditContactCommand extends EditPersonCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Edits the details of the person identified",
                    "by the index number used in the displayed person list.",
                    "Existing values will be overwritten by the input values other than for Modules and Tags.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    "INDEX (must be a positive integer)",
                    "{SPECIFIER}/{INPUT}...")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "For more information on specifiers, use the help command.",
                    "Note that Modules and Tags have a different behaviour from the other fields.",
                    "Editing a mod or tag will remove the module or tag",
                    "if they already exist in the Person's Modules field or Tags field,",
                    "and will add it if it does not exist.",
                    "For more information, check out the user guide in the link that appears in the help command.");

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    protected final PersonDescriptor editPersonDescriptor;
    private final Index index;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditContactCommand(PersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.index = editPersonDescriptor.getIndex().get();
        this.editPersonDescriptor = new PersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }


        model.setPerson(personToEdit, editedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    public PersonDescriptor getEditPersonDescriptor() {
        return this.editPersonDescriptor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return index.equals(e.index)
                && this.getEditPersonDescriptor().equals(e.getEditPersonDescriptor());
    }
}
