package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.module.ReadOnlyModule;

public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleListPanel.fxml";

    @FXML
    private ListView<ReadOnlyModule> moduleListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModuleListPanel(ObservableList<ReadOnlyModule> observableList) {
        super(FXML);
        moduleListView.setItems(observableList);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ModuleListViewCell extends ListCell<ReadOnlyModule> {
        @Override
        protected void updateItem(ReadOnlyModule module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module, getIndex() + 1).getRoot());
            }
        }
    }

}
