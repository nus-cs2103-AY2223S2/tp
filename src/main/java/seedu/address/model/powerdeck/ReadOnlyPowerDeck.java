package seedu.address.model.powerdeck;

import javafx.collections.ObservableList;
import seedu.address.model.powercard.PowerCard;

/**
 * Unmodifiable view of a PowerDeck
 */
public interface ReadOnlyPowerDeck {
    /**
     * Returns an unmodifiable view of the powerdeck.
     * This list will not contain any duplicate powercards.
     */
    ObservableList<PowerCard> getCardList();
}
