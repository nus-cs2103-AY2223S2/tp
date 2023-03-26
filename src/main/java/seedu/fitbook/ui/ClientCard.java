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

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitBook level 4</a>
     */

    public final Client client;

    @FXML
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
    private ImageView genderIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView weightIcon;
    @FXML
    private ImageView goalIcon;
    @FXML
    private ImageView caloriesIcon;


    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        String genderText;
        if (client.getGender().value.equals("M") || client.getGender().value.equals("m")) {
            genderText = "maleIcon";
        } else {
            genderText = "femaleIcon";
        }
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        weight.setText(client.getWeight().value + " Kg");
        setGoalCondition(client, goal);
        setCalorieCondition(client, calorie);
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
            logger.info("The calorie is invalid.");
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
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
