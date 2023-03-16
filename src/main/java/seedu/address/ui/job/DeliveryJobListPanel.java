package seedu.address.ui.job;

import java.util.function.BiConsumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class DeliveryJobListPanel extends UiPart<Region> {
    private static final String FXML = "DeliveryJobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeliveryJobListPanel.class);

    private BiConsumer<Integer, DeliveryJob> onSelectHandler;

    @FXML
    private ListView<DeliveryJob> deliveryJobListView;

    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public DeliveryJobListPanel(ObservableList<DeliveryJob> jobList, BiConsumer<Integer, DeliveryJob> handler) {
        super(FXML);
        deliveryJobListView.setItems(jobList);
        deliveryJobListView.setCellFactory(listView -> new DeliveryJobListViewCell());
        this.onSelectHandler = handler;

        deliveryJobListView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
            }

        });
    }

    /**
     * selectItem
     *
     * @param idx
     */
    public void selectItem(int idx) {
        logger.info("Delivery selected:" + deliveryJobListView.getSelectionModel().getSelectedIndex());
        deliveryJobListView.getSelectionModel().select(idx);
        onSelectHandler.accept(idx, deliveryJobListView.getSelectionModel().getSelectedItem());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a
     * {@code PersonCard}.
     */
    class DeliveryJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeliveryJobCard(job, getIndex() + 1).getRoot());
            }
        }
    }

}
