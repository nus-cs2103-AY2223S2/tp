package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
                    "Edits the details of the contact specified by the given index.",
                    "Existing values will be overwritten by the given parameters",
                    "for all fields except Modules and Tags.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    "INDEX", "[PREFIX/PARAMETER]...")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE,
                    COMMAND_WORD, "1",
                    PREFIX_PHONE + "87654321",
                    PREFIX_EMAIL + "johnd@apple.com",
                    PREFIX_TAG + "colleagues")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "Note that INDEX must be a positive integer.",
                    "For more information on prefixes, refer to the user guide using the help command.",
                    "Editing Modules and Tags have a different behaviour from editing the other fields.",
                    "If a module or tag already exists in the contact, it will be removed.",
                    "Otherwise, it will be added to the contact.");

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This contact already exists in the address book.";

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
