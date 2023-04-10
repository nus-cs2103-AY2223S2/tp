package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsAllKeywordsPredicate;
import seedu.address.model.person.Person;



/**
 * Finds and deletes person in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DeleteByNameCommand extends DeleteCommand {

    private final NameContainsAllKeywordsPredicate predicate;

    public DeleteByNameCommand(NameContainsAllKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> updatedList = model.getFilteredPersonList();

        if (updatedList.isEmpty()) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(Messages.MESSAGE_PERSON_NOT_FOUND));
        }
        if (updatedList.size() == 1) {
            Person personToDelete = updatedList.get(0);
            model.deletePerson(personToDelete);
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        } else {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MULTIPLE_PERSONS_FOUND));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteByNameCommand // instanceof handles nulls
                && predicate.equals(((DeleteByNameCommand) other).predicate)); // state check
    }
}
