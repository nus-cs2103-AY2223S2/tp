package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsAllKeywordsPredicate;
import seedu.address.model.person.Person;



/**
 * Edits the details of an existing person in the address book.
 */
public class EditByNameCommand extends EditCommand {
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final EditPersonDescriptor editPersonDescriptor;

    private final NameContainsAllKeywordsPredicate predicate;

    /**
     * @param predicate            predicate of name containing keywords
     * @param editPersonDescriptor details to edit the person with
     */
    public EditByNameCommand(NameContainsAllKeywordsPredicate predicate, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.predicate = predicate;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> updatedList = model.getFilteredPersonList();
        if (updatedList.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        if (updatedList.size() == 1) {
            Person personToEdit = updatedList.get(0);
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        } else {
            throw new CommandException(
                    String.format(Messages.MESSAGE_MULTIPLE_PERSONS_FOUND));
        }
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditByNameCommand)) {

            // state check
            return false;
        }

        // state check
        EditByNameCommand e = (EditByNameCommand) other;
        return predicate.equals(e.predicate)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
