package seedu.address.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Subject;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    private final HashMap<Label, ImageView> iconList = new HashMap<>();

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

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getOptionalPhone().map(Phone::toString).orElse(null));
        address.setText(person.getOptionalAddress().map(Address::toString).orElse(null));
        email.setText(person.getOptionalEmail().map(Email::toString).orElse(null));
        remark.setText(person.getOptionalRemark().map(Remark::truncateRemark).orElse(null));
        person.getOptionalEducation()
                .map(education -> new Label("Education: " + education.value))
                .ifPresent(label -> tags.getChildren().add(setStyleEducationLabel(label)));
        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.subjectName))
                .forEach(subject -> tags.getChildren().add(createSubjectLabel(subject)));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        List<Label> labels = Arrays.asList(phone, address, email, remark);
        resizeLabels(labels);
        resizeFlowPane(tags);
    }

    public Label setStyleEducationLabel(Label label) {
        label.setStyle("-fx-background-color : #107896;");
        return label;
    }

    public Label createSubjectLabel(Subject s) {
        Label subjectLabel = new Label(s.subjectName);
        subjectLabel.setStyle("-fx-background-color : #829356;");
        return subjectLabel;
    }

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
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
