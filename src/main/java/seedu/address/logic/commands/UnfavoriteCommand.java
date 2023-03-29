package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unfavorites a Person in the Address Book
 */
public class UnfavoriteCommand extends Command {
    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unfavourites the person "
            + "according to the Contact ID provided by user input.\n "
            + "Parameters: INDEX (must be positive integer).\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_NOT_EXISTS = "Person doesn't exist in the address book";

    public static final String MESSAGE_ISUNFAVORITED = "Person is already unfavorited in this address book: %1$s";

    public static final String MESSAGE_UNFAVORITE_PERSON_SUCCESS = "Unfavourited Person: %1$s";

    public final Index index;

    public UnfavoriteCommand(Index index) {
        this.index = index;
    }

    /**
     * Returns a CommandResult where the Selected Person
     * is Unfavorited.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_NOT_EXISTS);
        }

        Person personToUnfav = lastShownList.get(index.getZeroBased());

        if (!personToUnfav.getIsFavorite().getFavoriteStatus()) {
            return new CommandResult(String.format(MESSAGE_ISUNFAVORITED, personToUnfav));
        }

        Person unfavoritedPerson = personToUnfav.unfavorite();

        model.setPerson(personToUnfav, unfavoritedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNFAVORITE_PERSON_SUCCESS, unfavoritedPerson));
    }
}
