package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.result.ResultDisplay;

/**
 * Favorites a Person in the Address Book
 */
public class FavoriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Favorites the contact specified by the given index.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS, "INDEX (must be positive integer)")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD, "1");

    public static final String MESSAGE_NOT_EXISTS = "Contact doesn't exist in the address book";

    public static final String MESSAGE_ISFAVORITED = "Contact is already a favorite: %1s";

    public static final String MESSAGE_FAVORITE_PERSON_SUCCESS = "Contact marked as favorite: %1$s";

    public final Index index;

    public FavoriteCommand(Index index) {
        this.index = index;
    }

    /**
     * Returns a CommandResult where the Selected Person
     * is Favorited.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_NOT_EXISTS);
        }

        Person personToFav = lastShownList.get(index.getZeroBased());

        if (personToFav.getFavorite().getFavoriteStatus()) {
            return new CommandResult(String.format(MESSAGE_ISFAVORITED, personToFav));
        }

        Person favoritedPerson = personToFav.setFavorite(true);

        model.setPerson(personToFav, favoritedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_FAVORITE_PERSON_SUCCESS, favoritedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof FavoriteCommand) && index.equals(((FavoriteCommand) other).index));
    }
}
