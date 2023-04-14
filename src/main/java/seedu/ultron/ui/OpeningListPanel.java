package seedu.ultron.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.Logic;
import seedu.ultron.model.opening.Opening;

/**
 * Panel containing the list of openings.
 */
public class OpeningListPanel extends UiPart<Region> {
    private static final String FXML = "OpeningListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OpeningListPanel.class);

    @FXML
    private ListView<Opening> openingListView;

    /**
     * Creates a {@code OpeningListPanel} with the given {@code ObservableList}.
     */
    public OpeningListPanel(ObservableList<Opening> openingList, Logic logic, MainWindow mainWindow) {
        super(FXML);
        openingListView.setItems(openingList);
        openingListView.setCellFactory(listView -> new OpeningListViewCell());
        openingListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Index selected = Index.fromZeroBased(openingListView.getSelectionModel().getSelectedIndex());
                logic.setSelectedOpening(selected);
                mainWindow.handleShow();
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Opening} using a {@code OpeningCard}.
     */
    class OpeningListViewCell extends ListCell<Opening> {
        @Override
        protected void updateItem(Opening opening, boolean empty) {
            super.updateItem(opening, empty);

            if (empty || opening == null) {
                StackPane sp = new StackPane();
                sp.setPrefHeight(105);
                setGraphic(sp);
            } else {
                setGraphic(new OpeningCard(opening, getIndex() + 1).getRoot());
            }
        }
    }

}
