package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Card;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Card> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Card> cardList) {
        super(FXML);
        personListView.setItems(cardList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setStyle("-fx-background-color: #AAAAAA");

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Card> {
        @Override
        protected void updateItem(Card card, boolean empty) {
            super.updateItem(card, empty);
            if (empty || card == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new PersonCard(card, getIndex() + 1).getRoot());
            }
        }
    }

}
