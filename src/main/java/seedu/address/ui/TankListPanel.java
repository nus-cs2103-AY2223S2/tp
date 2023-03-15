package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tank.Tank;

/**
 * Panel containing the list of Tanks.
 */
public class TankListPanel extends UiPart<Region> {
    private static final String FXML = "TankListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FishListPanel.class);

    @FXML
    private ListView<Tank> tankListView;

    /**
     * Creates a {@code FishListPanel} with the given {@code ObservableList}.
     */
    public TankListPanel(ObservableList<Tank> tankList) {
        super(FXML);
        tankListView.setItems(tankList);
        tankListView.setCellFactory(listView -> new TankListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tank} using a {@code TankCard}.
     */
    class TankListViewCell extends ListCell<Tank> {
        @Override
        protected void updateItem(Tank tank, boolean empty) {
            super.updateItem(tank, empty);

            if (empty || tank == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TankCard(tank, getIndex() + 1).getRoot());
            }
        }
    }

}
