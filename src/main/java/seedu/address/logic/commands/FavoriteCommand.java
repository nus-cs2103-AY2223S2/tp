package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Favorites a person so that he shows up on the favorites panel.
 */
public class FavoriteCommand extends Command {
    public static final String COMMAND_WORD = "favorite";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favorites/unfavorites the person identified by "
            + "the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVORITE_SUCCESS = "Successfully favorited person";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index targetIndex;

    public FavoriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    private static Person createEditedPerson(Person personToEdit, boolean isFavorite) {
        assert personToEdit != null;

        Person newPerson = new Person(personToEdit.getRank(), personToEdit.getName(), personToEdit.getUnit(),
                personToEdit.getCompany(), personToEdit.getPlatoon(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags());
        newPerson.setIsFavorite(isFavorite);
        return newPerson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavorite = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToFavorite, !personToFavorite.getIsFavorite());

        if (!personToFavorite.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToFavorite, editedPerson);
        model.commit(model.getAddressBook());

        return new CommandResult(String.format(MESSAGE_FAVORITE_SUCCESS, personToFavorite));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavoriteCommand // instanceof handles nulls
                && targetIndex.equals(((FavoriteCommand) other).targetIndex)); // state check
    }

}
