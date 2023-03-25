package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.LogicManager.UNKNOWN_STATE_MESSAGE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelState;
import seedu.address.model.card.QuestionContainsKeywordsPredicate;
import seedu.address.model.deck.DeckContainsKeywordsPredicate;

/**
 * Finds and lists all cards in the master deck that contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cards that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " gravity constant";

    private final QuestionContainsKeywordsPredicate cardPredicate;
    private final DeckContainsKeywordsPredicate deckPredicate;

    /**
     * Constructs a FindCommand with the given list of keywords.
     * The command will search for cards and decks that contain any of the keywords.
     * @param keywords the list of keywords to search for
     */
    public FindCommand(List keywords) {
        this.cardPredicate = new QuestionContainsKeywordsPredicate(keywords);
        this.deckPredicate = new DeckContainsKeywordsPredicate(keywords);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String returnMessage;
        int returnListSize;
        ModelState currentState = model.getState();
        switch (currentState) {
        case DECK_MODE:
            model.updateFilteredCardList(cardPredicate);
            returnMessage = Messages.MESSAGE_CARDS_LISTED_OVERVIEW;
            returnListSize = model.getFilteredCardList().size();
            break;
        case MAIN_MODE:
            model.updateFilteredDeckList(deckPredicate);
            returnMessage = Messages.MESSAGE_DECKS_LISTED_OVERVIEW;
            returnListSize = model.getFilteredDeckList().size();
            break;
        default:
            throw new CommandException(UNKNOWN_STATE_MESSAGE);
        }

        return new CommandResult(
                String.format(returnMessage, returnListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && cardPredicate.equals(((FindCommand) other).cardPredicate))
                && deckPredicate.equals(((FindCommand) other).deckPredicate); // state check
    }
}
