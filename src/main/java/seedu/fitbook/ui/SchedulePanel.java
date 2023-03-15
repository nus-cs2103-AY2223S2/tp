package seedu.fitbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.ClientAppointmentComparator;

/**
 * Panel containing the list of appointments.
 */
public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private ListView<Client> scheduleView;

    /**
     * Creates a {@code SchedulePanel} with the given {@code ObservableList}.
     */
    public SchedulePanel(ObservableList<Client> scheduleList) {
        super(FXML);

        FilteredList<Client> filteredList =
                new FilteredList<>(scheduleList, client -> !client.getAppointments().isEmpty());
        ClientAppointmentComparator comparator = new ClientAppointmentComparator();
        SortedList<Client> sortedList = new SortedList<>(filteredList, comparator);

        scheduleView.setItems(sortedList);
        scheduleView.setCellFactory(listView -> new ScheduleViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code ScheduleCard}.
     */
    class ScheduleViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);
            if (client != null) {
                System.out.println(client.getAppointments().toString());
            }
            if (empty || client == null || client.getAppointments().isEmpty()) {

                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
