package seedu.powercards.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.logic.commands.cardcommands.AddCardCommand.MESSAGE_NO_SELECTED_DECK;

import java.util.List;
import java.util.Optional;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.card.CardInDeckPredicate;
import seedu.powercards.model.card.QuestionContainsKeywordsPredicate;
import seedu.powercards.model.deck.Deck;

/**
 * Finds and lists all cards in the selected deck that contain any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCardsCommand extends Command {

    public static final String COMMAND_WORD = "findCards";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cards that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " gravity constant";

    private final QuestionContainsKeywordsPredicate cardPredicate;

    /**
     * Constructs a FindCardsCommand with the given list of keywords.
     * The command will search for cards in the selected deck that contain any of the keywords.
     * @param keywords the list of keywords to search for
     */
    public FindCardsCommand(List<String> keywords) {
        this.cardPredicate = new QuestionContainsKeywordsPredicate(keywords);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Deck> selectedDeck = model.getSelectedDeck();
        assert selectedDeck.isPresent() : MESSAGE_NO_SELECTED_DECK;
        model.updateFilteredCardList(cardPredicate.and(new CardInDeckPredicate(selectedDeck.get())));
        return new CommandResult(
                String.format(Messages.MESSAGE_CARDS_LISTED_OVERVIEW, model.getFilteredCardList().size()),
                false, false, false, false, false, false, true, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCardsCommand // instanceof handles nulls
                && cardPredicate.equals(((FindCardsCommand) other).cardPredicate)); // state check
    }
}
