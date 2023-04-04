package seedu.address.ui.timetable;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of jobs divided into slots in day.
 */
public class DayJobListPanel extends UiPart<Region> {
    private static final String FXML = "DayJobListPane.fxml";
    private final Logger logger = LogsCenter.getLogger(DayJobListPanel.class);
    private final Logic logic;

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

    @FXML
    private ListView<DeliveryJob> jobList6;


    /**
     * Creates a {@code DayJobListPanel}
     * @param logic logic
     * @param jobListInDay observable list of jobs
     */
    public DayJobListPanel(Logic logic, DeliveryList jobListInDay) {
        super(FXML);
        this.logic = logic;

        addJobListToSlot(jobList1, jobListInDay.get(0));
        addJobListToSlot(jobList2, jobListInDay.get(1));
        addJobListToSlot(jobList3, jobListInDay.get(2));
        addJobListToSlot(jobList4, jobListInDay.get(3));
        addJobListToSlot(jobList5, jobListInDay.get(4));
        addJobListToSlot(jobList6, jobListInDay.get(5));
    }

    private void addScrollPaneToAll() {
        ScrollPane pane = new ScrollPane();

    }

    private void addJobListToSlot(ListView<DeliveryJob> jobSlot, ArrayList<DeliveryJob> jobList) {
        if (jobList != null) {
            jobSlot.setItems(FXCollections.observableArrayList(jobList));
            jobSlot.setCellFactory(listView -> new DayJobListViewCell());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a
     * {@code DayDeliveryJobCard}.
     */
    class DayJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
                setStyle("-fx-background-color: transparent;");
            } else {
                setGraphic(new DayDeliveryJobCard(logic, job, getIndex() + 1).getRoot());
            }

            if (getIndex() % 2 == 1) {
                setStyle("-fx-background-color: DARKCYAN;");
            } else {
                setStyle("-fx-background-color: GREY;");
            }
        }
    }

}
