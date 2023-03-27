package seedu.address.ui;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

public class ResultPersonCard extends UiPart<Region> {
    private static final String FXML = "ResultPersonCard.fxml";
    private final Logger logger = LogsCenter.getLogger(ResultPersonCard.class);

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
//    @FXML
//    private Label id;
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
//        id.setText(displayedIndex + ". ");
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

        List<Label> labels = Arrays.asList(phone, address, email, remark);
        resizeLabels(labels);
        resizeFlowPanes(tags);

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

    public void resizeFlowPanes(FlowPane flowpane) {
        if (flowpane.getChildren().isEmpty()) {
            System.out.println("there is empty flowpane");
            flowpane.setVisible(false);
            flowpane.setManaged(false);
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
