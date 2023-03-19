package seedu.fitbook.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Client;

/**
 * A UI component that displays information of a {@code Appointment}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitBook level 4</a>
     */

    public final Client client;
    private LocalDateTime currentTime;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label appointments;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ScheduleCard} with the given {@code Client} and index to display.
     */
    public ScheduleCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;

        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList(client.getAppointments());
        ObservableList<LocalDateTime> dateTimeList = FXCollections.observableArrayList();
        for (Appointment appointment : appointmentsList) {
            dateTimeList.add(appointment.getDateTime());
        }

        //Display the appointments
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        for (LocalDateTime dateTime : dateTimeList) {
            sb.append(dateTime.format(f));
            sb.append("\n");
        }

        currentTime = LocalDateTime.now();
        boolean isExpired = true;
        for (LocalDateTime dateTime: dateTimeList) {
            if (currentTime.isBefore(dateTime)) {
                isExpired = false;
                break;
            }
        }

        if (isExpired) {
            getRoot().setStyle("-fx-background-color: derive(#888888, 20%); -fx-border-color: #BEBEBE;");
        }

        String dateTimeString = sb.toString();
        appointments.setText(dateTimeString);

        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
