package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private final TagContainsKeywordsPredicate predicate;

    private final boolean isTagPresent;

    //private final TagContainsKeywordsPredicate predicate;
    public ListCommand(TagContainsKeywordsPredicate predicate, boolean isTagPresent) {
        this.predicate = predicate;
        this.isTagPresent = isTagPresent;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!isTagPresent) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            model.updateFilteredPersonList(predicate);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && predicate.equals(((ListCommand) other).predicate)); // state check
    }
}
