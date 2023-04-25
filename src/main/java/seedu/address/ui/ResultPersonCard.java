package seedu.address.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Module;

/**
 * Card containing a person
 */
public class ResultPersonCard extends UiPart<Region> {
    private static final String FXML = "ResultPersonCard.fxml";
    public final Person person;
    private final Logger logger = LogsCenter.getLogger(ResultPersonCard.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane modules;
    @FXML
    private HBox tagset;
    @FXML
    private Label remark;
    @FXML
    private Label telegram;

    /**
     * Creates a {@code ResultPersonCard} with the given {@code Person} to display.
     */
    public ResultPersonCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getOptionalPhone().map(Phone::toString).orElse(null));
        address.setText(person.getOptionalAddress().map(Address::toString).orElse(null));
        email.setText(person.getOptionalEmail().map(Email::toString).orElse(null));
        remark.setText(person.getOptionalRemark().map(Remark::toString).orElse(null));
        telegram.setText(person.getOptionalTelegram().map(Telegram::toString).orElse(null));
        person.getOptionalEducation()
                .map(education -> new Label("Education: " + education.value))
                .ifPresent(label -> tags.getChildren().add(setStyleEducationLabel(label)));
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.moduleName))
                .forEach(module -> tags.getChildren().add(createModuleLabel(module)));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        List<Label> labels = Arrays.asList(phone, address, email, telegram, remark);
        resizeLabels(labels);
        resizeFlowPane(tags);

    }

    /**
     * Sets the style for the Label containing Education.
     * @param label Label containing Education.
     * @return Label after setting style.
     */
    public Label setStyleEducationLabel(Label label) {
        label.setStyle("-fx-background-color : #107896;");
        return label;
    }

    /**
     * Sets the style and creates a Label for Module.
     * @param module Module to create a label for.
     * @return Module as a Label.
     */
    public Label createModuleLabel(Module module) {
        Label moduleLabel = new Label(module.moduleName);
        moduleLabel.setStyle("-fx-background-color : #829356;");
        return moduleLabel;
    }

    /**
     * Checks if Labels are empty and sets them as invisible.
     * @param labels List of labels to check.
     */
    public void resizeLabels(List<Label> labels) {
        for (int i = 0; i < labels.size(); i++) {
            Label label = labels.get(i);
            String text = label.getText();
            if (text == null || text.isEmpty()) {
                label.setVisible(false);
                label.setManaged(false);
            }
        }
    }

    /**
     * Checks if flowpane is empty and sets the flowpane to be invisble if so, otherwise adds an icon to the flowpane.
     * @param flowpane Flowpane to check for.
     */
    public void resizeFlowPane(FlowPane flowpane) {
        if (flowpane.getChildren().isEmpty()) {
            flowpane.setVisible(false);
            flowpane.setManaged(false);
        } else {
            Image tagIcon = new Image(getClass().getResourceAsStream("/images/books.png"));
            ImageView tagIconView = new ImageView(tagIcon);
            tagIconView.setFitWidth(16);
            tagIconView.setFitHeight(16);
            flowpane.getChildren().add(0, tagIconView);
        }
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ResultPersonCard)) {
            return false;
        }

        ResultPersonCard card = (ResultPersonCard) other;
        return person.equals(card.person);
    }
}
