package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.drug.Drug;

public class DrugListPanel extends UiPart<Region> {
    private static final String FXML = "DrugListPanel.fxml";
    @FXML
    private ListView<Drug> drugListView;
    public DrugListPanel(ObservableList<Drug> drugList) {
        super(FXML);
        // DRUG LIST
        drugListView.setItems(drugList);
        drugListView.setCellFactory(listView -> new DrugListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Drug} using a {@code DrugCard}.
     */
    class DrugListViewCell extends ListCell<Drug> {
        @Override
        protected void updateItem(Drug drug, boolean empty) {
            super.updateItem(drug, empty);

            if (empty || drug == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DrugCard(drug, getIndex() + 1).getRoot());
            }
        }
    }

}
