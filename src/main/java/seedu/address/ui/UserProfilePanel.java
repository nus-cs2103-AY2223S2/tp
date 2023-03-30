package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;


/**
 * Controller for user profile page
 */
public class UserProfilePanel extends UiPart<Region> {

    private static final String FXML = "UserProfilePanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(UserProfilePanel.class);

    private static final String PHONE_NUMBER = "Phone number: ";
    private static final String EMAIL = "Email: ";
    private static final String STATION = "Station: ";
    private static final String TELEGRAM = "Telegram: ";
    private static final String GROUP_TAG_STYLE = "-fx-text-fill: black; -fx-background-color: rgb(227, 211, 238); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";
    private static final String MODULE_TAG_STYLE = "-fx-text-fill: #FFFFFF; -fx-background-color: rgb(150, 146, 223); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";

    private Logic logic;
    private User user;

    @FXML
    private StackPane userProfilePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label station;
    @FXML
    private Label telegramHandle;

    @FXML
    private FlowPane allTags;

    /**
     * Creates a new User profile window.
     */
    public UserProfilePanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.user = this.logic.getUser();

        name.setText(String.valueOf(this.user.getName()));
        phone.setText(PHONE_NUMBER + this.user.getPhone());
        email.setText(EMAIL + this.user.getEmail());
        station.setText(STATION + this.user.getStation());
        station.setWrapText(true);
        telegramHandle.setText(TELEGRAM + this.user.getTelegramHandle());

        user.getImmutableGroupTags().forEach(groupTag -> {
            Button temp = new Button(groupTag.tagName);
            temp.setStyle(GROUP_TAG_STYLE);
            allTags.getChildren().add(temp);
        });

        user.getImmutableModuleTags().forEach(moduleTag -> {
            Button temp = new Button(moduleTag.tagName);
            temp.setStyle(MODULE_TAG_STYLE);
            allTags.getChildren().add(temp);
        });

        allTags.setHgap(5.0);
        allTags.setVgap(3.0);
    }

    /**
     * Creates a new Person profile window.
     */
    public UserProfilePanel(Person person) {
        super(FXML);
        name.setText(String.valueOf(person.getName()));
        phone.setText(PHONE_NUMBER + person.getPhone());
        email.setText(EMAIL + person.getEmail());
        station.setText(STATION + person.getStation());
        telegramHandle.setText(TELEGRAM + person.getTelegramHandle());

        person.getImmutableGroupTags().forEach(groupTag -> {
            Button temp = new Button(groupTag.tagName);
            temp.setStyle(GROUP_TAG_STYLE);
            allTags.getChildren().add(temp);
        });

        person.getImmutableModuleTags().forEach(moduleTag -> {
            Button temp = new Button(moduleTag.tagName);
            temp.setStyle(MODULE_TAG_STYLE);
            allTags.getChildren().add(temp);
        });

        allTags.setHgap(4.0);
        allTags.setVgap(5.0);
    }
}
