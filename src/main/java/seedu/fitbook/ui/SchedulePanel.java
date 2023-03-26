package seedu.fitbook.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.ClientAppointmentComparator;

/**
 * Panel containing the list of appointments.
 */
public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);
    // private FilteredList<Client> filteredList;
    @FXML
    private ListView<Client> scheduleView;

    /**
     * Creates a {@code SchedulePanel} with the given {@code ObservableList}.
     */
    public SchedulePanel(ObservableList<Client> scheduleList) {
        super(FXML);

        FilteredList<Client> filteredList = new FilteredList<>(scheduleList, client -> !client.isAppointmentEmpty(client));
        ClientAppointmentComparator comparator = new ClientAppointmentComparator();

        scheduleView.setCellFactory(listView -> new ScheduleViewCell());

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Remove clients with expired appointments from the filtered list
            LocalDateTime currentTime = LocalDateTime.now();
            filteredList.setPredicate(client -> {
                for (Appointment appointment : client.getAppointments()) {
                    if (currentTime.isBefore(appointment.getDateTime())) {
                        return true; // Keep client in list
                    }
                }
                return false; // Remove client from list
            });
            SortedList<Client> sortedList = new SortedList<>(filteredList, comparator);
            sortedList.setComparator(comparator);
            scheduleView.setItems(sortedList);
            LocalDateTime now = LocalDateTime.now();
            for (Client client : filteredList) {
                for (Appointment appointment : client.getAppointments()) {
                    if (now.isAfter(appointment.getDateTime())) {
                        client.removeAppointment(appointment);
                    }
                }
            }
            scheduleView.refresh();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code ScheduleCard}.
     */
    class ScheduleViewCell extends ListCell<Client> {
        private ScheduleCard scheduleCard;
        public ScheduleViewCell() {
            // Add a listener to the textProperty of the cell
            textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == null) {
                    System.out.println("change detected");
                    setGraphic(null);
                    if (scheduleCard != null) {
                        // Remove the ScheduleCard from the cell if it exists
                        scheduleCard = null;
                    }
                }
            });
        }
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);
            if (empty || client == null || client.getAppointments().isEmpty()) {
                setGraphic(null);
                setText(null);
            } else {
                if(!client.isAppointmentEmpty(client)) {
                    setGraphic(new ScheduleCard(client, getIndex() + 1).getRoot());
                } else {
                    setGraphic(null);
                }
            }
        }

    }
}
