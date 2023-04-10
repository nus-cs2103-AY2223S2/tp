package seedu.powercards.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.powercards.commons.core.LogsCenter;
import seedu.powercards.model.deck.Deck;

/**
 * Panel containing the list of persons.
 */
public class DeckListPanel extends UiPart<Region> {
    private static final String FXML = "DeckListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeckListPanel.class);

    @FXML
    private ListView<Deck> deckListView;

    /**
     * Creates a {@code CardListPanel} with the given {@code ObservableList}.
     */
    public DeckListPanel(ObservableList<Deck> deckList, boolean isReview) {
        super(FXML);
        deckListView.setItems(deckList);
        if (!isReview) {
            deckListView.setCellFactory(listView -> new DeckListViewCell());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code CardElement}.
     */
    class DeckListViewCell extends ListCell<Deck> {
        @Override
        protected void updateItem(Deck deck, boolean empty) {
            super.updateItem(deck, empty);

            if (empty || deck == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeckElement(deck, getIndex() + 1).getRoot());
                setStyle("-fx-border-insets: 10px; -fx-background-insets: 10px; -fx-padding: 10 20 10 20; "
                        + "-fx-background-color:#FFFFFF");
            }
        }
    }
}
