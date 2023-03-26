package seedu.address.ui.jobs;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class DayJobListPanel extends UiPart<Region> {
    private static final String FXML = "DayJobListPane.fxml";
    private final Logger logger = LogsCenter.getLogger(DayJobListPanel.class);

    @FXML
    private ListView<DeliveryJob> jobList1;

    @FXML
    private ListView<DeliveryJob> jobList2;

    @FXML
    private ListView<DeliveryJob> jobList3;

    @FXML
    private ListView<DeliveryJob> jobList4;

    @FXML
    private ListView<DeliveryJob> jobList5;


    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public DayJobListPanel(DeliveryList jobListInDay) {
        super(FXML);
        addJobListToSlot(jobList1, jobListInDay.get(0));
        addJobListToSlot(jobList2, jobListInDay.get(1));
        addJobListToSlot(jobList3, jobListInDay.get(2));
        addJobListToSlot(jobList4, jobListInDay.get(3));
        addJobListToSlot(jobList5, jobListInDay.get(4));

        /*jobList1.setItems(FXCollections.observableArrayList(jobListInDay.get(0)));
        jobList1.setCellFactory(listView -> new DayJobListViewCell());

        jobList2.setItems(FXCollections.observableArrayList(jobListInDay.get(1)));
        jobList2.setCellFactory(listView -> new DayJobListViewCell());

        jobList3.setItems(FXCollections.observableArrayList(jobListInDay.get(2)));
        jobList3.setCellFactory(listView -> new DayJobListViewCell());

        jobList4.setItems(FXCollections.observableArrayList(jobListInDay.get(3)));
        jobList4.setCellFactory(listView -> new DayJobListViewCell());

        jobList5.setItems(FXCollections.observableArrayList(jobListInDay.get(4)));
        jobList5.setCellFactory(listView -> new DayJobListViewCell());*/
    }

    private void addJobListToSlot(ListView<DeliveryJob> jobSlot, ArrayList<DeliveryJob> jobList) {
        if (jobList != null) {
            jobSlot.setItems(FXCollections.observableArrayList(jobList));
            jobSlot.setCellFactory(listView -> new DayJobListViewCell());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a
     * {@code PersonCard}.
     */
    class DayJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayDeliveryJobCard(job, getIndex() + 1).getRoot());
            }
        }
    }

}
