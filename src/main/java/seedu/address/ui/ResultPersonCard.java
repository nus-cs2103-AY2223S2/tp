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
import seedu.address.model.tag.Subject;

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
    private FlowPane subjects;
    @FXML
    private HBox tagset;
    @FXML
    private Label remark;

    public ResultPersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getOptionalPhone().map(Phone::toString).orElse(null));
        address.setText(person.getOptionalAddress().map(Address::toString).orElse(null));
        email.setText(person.getOptionalEmail().map(Email::toString).orElse(null));
        remark.setText(person.getOptionalRemark().map(Remark::toString).orElse(null));

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
