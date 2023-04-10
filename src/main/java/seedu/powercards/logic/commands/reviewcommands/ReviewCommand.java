package seedu.powercards.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX;
import static seedu.powercards.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag.TagName;


/**
 * Starts a review session of a Deck.
 */
public class ReviewCommand extends Command {

    public static final String COMMAND_WORD = "review";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Review a deck of cards "
            + "by the index number used in the displayed deck list and by the tagged difficulty.\n"
            + "Parameter: INDEX (must be a positive integer).\n"
            + "Flags: -e -m -h for EASY, MEDIUM, HARD tags\n"
            + "Example (to test all cards in deck 1): " + COMMAND_WORD + " 1\n"
            + "Example (to test MEDIUM and HARD cards in deck 1): " + COMMAND_WORD + " 1 -m -h\n"
            + "No other input is allowed between the flags.";

    public static final String MESSAGE_SUCCESS = "Deck to be reviewed: %1$s\nReviewing cards of %2$s";
    public static final String MESSAGE_EMPTY_DECK = "The deck you chose to review is empty";
    public static final String MESSAGE_NO_CARDS_WITH_TAG = "There are no cards "
            + "tagged with those difficulties in the deck";
    private final Index deckIndex;
    private final List<TagName> difficulties;

    /**
     * Creates a ReviewCommand with the specified index of the deck.
     */
    public ReviewCommand(Index idx, List<TagName> difficulties) {
        requireAllNonNull(idx, difficulties);
        this.deckIndex = idx;
        this.difficulties = difficulties;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Deck> deckList = model.getFilteredDeckList();
        boolean isIndexOutOfBound = deckIndex.getZeroBased() >= deckList.size();
        if (isIndexOutOfBound) {
            throw new CommandException(MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        } else if (model.getDeckSize(deckIndex.getZeroBased()) == 0) {
            throw new CommandException(MESSAGE_EMPTY_DECK);
        } else if (model.getDeckSizeFilteredTag(deckIndex.getZeroBased(), difficulties) == 0) {
            throw new CommandException(MESSAGE_NO_CARDS_WITH_TAG);
        }

        model.reviewDeck(deckIndex, difficulties);

        String diffString = "";
        if (difficulties.containsAll(Arrays.asList(TagName.EASY, TagName.MEDIUM, TagName.HARD))) {
            diffString = "EASY/MEDIUM/HARD difficulties";
        } else if (difficulties.containsAll(Arrays.asList(TagName.EASY, TagName.MEDIUM))) {
            diffString = "EASY/MEDIUM difficulties";
        } else if (difficulties.containsAll(Arrays.asList(TagName.EASY, TagName.HARD))) {
            diffString = "EASY/HARD difficulties";
        } else if (difficulties.containsAll(Arrays.asList(TagName.MEDIUM, TagName.HARD))) {
            diffString = "MEDIUM/HARD difficulties";
        } else if (difficulties.contains(TagName.EASY)) {
            diffString = "EASY difficulty";
        } else if (difficulties.contains(TagName.MEDIUM)) {
            diffString = "MEDIUM difficulty";
        } else if (difficulties.contains(TagName.HARD)) {
            diffString = "HARD difficulty";
        } else {
            diffString = "ALL difficulties";
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getReviewDeckName(), diffString),
                false, false, true, false, false, false, false, false, false, false
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviewCommand // instanceof handles nulls
                && deckIndex.equals(((ReviewCommand) other).deckIndex));
    }
}
