package seedu.powercards.logic.commands.commandresult;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should select a Deck**/
    private final boolean selectDeck;

    /** The application should unselect a Deck**/
    private final boolean unselectDeck;

    /** The application should end a review **/
    private final boolean startReview;

    /** The application should end a review **/
    private final boolean endReview;

    /** The application should find cards **/
    private final boolean findCards;

    /** The application should find decks **/
    private final boolean findDecks;

    /** The application should show all cards**/
    private final boolean showCards;

    /** The application should show all decks**/
    private final boolean showDecks;



    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(
            String feedbackToUser, boolean showHelp, boolean exit,
            boolean startReview, boolean endReview, boolean selectDeck, boolean unselectDeck,
            boolean findCards, boolean findDecks, boolean showCards, boolean showDecks) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.selectDeck = selectDeck;
        this.unselectDeck = unselectDeck;
        this.startReview = startReview;
        this.endReview = endReview;
        this.findCards = findCards;
        this.findDecks = findDecks;
        this.showCards = showCards;
        this.showDecks = showDecks;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser,
                false, false, false, false, false, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isStartReview() {
        return startReview;
    }

    public boolean isEndReview() {
        return endReview;
    }

    public boolean isSelectDeck() {
        return selectDeck;
    }

    public boolean isUnselectDeck() {
        return unselectDeck;
    }

    public boolean isFindCards() {
        return findCards;
    }

    public boolean isFindDecks() {
        return findDecks;
    }

    public boolean isShowCards() {
        return showCards;
    }

    public boolean isShowDecks() {
        return showDecks;
    }

    public boolean isClear() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && startReview == otherCommandResult.startReview
                && endReview == otherCommandResult.endReview
                && selectDeck == otherCommandResult.selectDeck
                && unselectDeck == otherCommandResult.unselectDeck
                && findCards == otherCommandResult.findCards
                && findDecks == otherCommandResult.findDecks
                && showCards == otherCommandResult.showCards
                && showDecks == otherCommandResult.showDecks;

    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, startReview, endReview, selectDeck, unselectDeck);
    }

}
