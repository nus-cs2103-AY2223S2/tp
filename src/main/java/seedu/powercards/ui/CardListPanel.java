package seedu.powercards.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.powercards.commons.core.LogsCenter;
import seedu.powercards.model.card.Card;

/**
 * Panel containing the list of persons.
 */
public class CardListPanel extends UiPart<Region> {
    private static final String FXML = "CardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CardListPanel.class);

    @FXML
    private ListView<Card> cardListView;

    /**
     * Creates a {@code CardListPanel} with the given {@code ObservableList}.
     */
    public CardListPanel(ObservableList<Card> cardList, boolean isReview) {
        super(FXML);

        cardListView.setItems(cardList);
        cardListView.setCellFactory(listView -> new CardListViewCell());

        if (isReview) {
            toggleReview();
        } else {
            endReview();
        }
    }

    private void toggleReview() {
        cardListView.setCellFactory(listView -> new ReviewCardListViewCell());
    }

    private void endReview() {
        cardListView.setCellFactory(listView -> new CardListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ReviewElement} using a {@code CardElement}.
     */
    static class ReviewCardListViewCell extends ListCell<Card> {
        @Override
        protected void updateItem(Card card, boolean empty) {
            super.updateItem(card, empty);
            if (empty || card == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new ReviewElement(card).getRoot());
                setStyle("-fx-border-insets: 10px; -fx-background-insets: 10px; -fx-padding: 10 80 10 80; "
                        + "-fx-background-color:#e6eaeb ");
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code CardElement}.
     */
    static class CardListViewCell extends ListCell<Card> {
        @Override
        protected void updateItem(Card card, boolean empty) {
            super.updateItem(card, empty);
            if (empty || card == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new CardElement(card, getIndex() + 1).getRoot());
                setStyle("-fx-border-insets: 10px; -fx-background-insets: 10px; -fx-padding: 10 80 10 80; "
                        + "-fx-background-color:#e6eaeb ");
            }
        }
    }

}
