package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

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
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane subjects;
    @FXML
    private Label remark;

    public ResultPersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getOptionalPhone().map(Phone::toString).orElse(null));
        address.setText(person.getOptionalAddress().map(Address::toString).orElse(null));
        email.setText(person.getOptionalEmail().map(Email::toString).orElse(null));
        remark.setText(person.getOptionalRemark().map(Remark::toString).orElse(null));
        person.getOptionalEducation()
                .map(education -> new Label("Education: " + education.value))
                .ifPresent(label -> tags.getChildren().add(label));
        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.subjectName))
                .forEach(subject -> tags.getChildren().add(new Label(subject.subjectName)));

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        return id.getText().equals(card.id.getText()) && person.equals(card.person);
    }
}
