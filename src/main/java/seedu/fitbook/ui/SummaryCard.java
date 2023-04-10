package seedu.fitbook.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbook.AppParameters;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;

/**
 * A UI component that displays detailed information of a {@code Client} with {@code Routine}.
 */
public class SummaryCard extends UiPart<Region> {
    private static final String FXML = "SummaryCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitBook level 4</a>
     */

    public final Client client;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label weight;
    @FXML
    private Label gender;
    @FXML
    private Label routines;
    @FXML
    private Label subTitle;
    @FXML
    private FlowPane appointments;
    @FXML
    private FlowPane tags;
    @FXML
    private Label goal;
    @FXML
    private Label calorie;
    @FXML
    private ImageView genderList;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView weightIcon;
    @FXML
    private ImageView goalIcon;
    @FXML
    private ImageView caloriesIcon;
    @FXML
    private ImageView routineIcon;


    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public SummaryCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        String genderText;
        if (client.getGender().value.equals("M") || client.getGender().value.equals("m")) {
            genderText = "maleIcon";
        } else {
            genderText = "femaleIcon";
        }
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        weight.setText(client.getWeight().value + " Kg");
        setGoalCondition(client, goal);
        setCalorieCondition(client, calorie);
        setRoutineCondition(client, routines);
        //routines.setText(client.getRoutines().toString());
        client.getAppointments().stream()
                .sorted(Comparator.comparing(appointment -> appointment.appointmentTime))
                .forEach(appointment -> appointments.getChildren().add(new Label(appointment.appointmentTime)));
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        genderList.setImage(
                new Image(this.getClass().getResourceAsStream("/images/genderList/" + genderText + ".png")));
        phoneIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/phoneIcon.png")));
        addressIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/AddressIcon.png")));
        emailIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/emailIcon.png")));
        weightIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/weightIcon.png")));
    }

    /**
     * Sets the goal to be displayed.
     * If goal value is "client has not added a goal" , remove display.
     *
     * @param client The current client.
     * @param goal The client's goal.
     */
    private void setGoalCondition(Client client, Label goal) {
        if (!client.getGoal().value.equals("client has not added a goal")) {
            goal.setText(client.getGoal().value);
            goalIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/goalIcon.png")));
        } else {
            goal.setManaged(false);
            goalIcon.setManaged(false);
        }
    }

    /**
     * Sets the goal to be displayed.
     * If goal value is "client has not added a goal" , remove display.
     *
     * @param client The current client.
     * @param routines The client's goal.
     */
    private void setRoutineCondition(Client client, Label routines) {
        if (!client.getRoutines().isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (Routine routine : client.getRoutines()) {
                str.append(routine.getRoutineName());
                str.append("\n");
                str.append(routine.exerciseListToString() + "\n");
            }
            routines.setText(str.toString());
            routineIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/routineIcon.png")));
        } else {
            routines.setManaged(false);
            subTitle.setManaged(false);
            routineIcon.setManaged(false);
            routines.setText("No routines");
        }
    }

    /**
     * Sets the calorie to be displayed.
     * If calorie value is 0000, remove display.
     *
     * @param client The current client.
     * @param calorie The recommended calorie intake of the client.
     */
    private void setCalorieCondition(Client client, Label calorie) {
        if (!client.getCalorie().value.equals("0000")) {
            calorie.setText(client.getCalorie().value + " cal");
            caloriesIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/caloriesIcon.png")));
        } else {
            calorie.setManaged(false);
            caloriesIcon.setManaged(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        SummaryCard card = (SummaryCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
