package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.lecture.ReadOnlyLecture;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class LectureCard extends UiPart<Region> {

    private static final String FXML = "LectureListCard.fxml";

    private final ReadOnlyLecture lecture;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label lectureName;
    @FXML
    private Label videoCount;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCard} with the given {@code ReadOnlyModule}.
     */
    public LectureCard(ReadOnlyLecture lecture, int displayedIndex) {
        super(FXML);
        this.lecture = lecture;
        id.setText(displayedIndex + ". ");
        String s = lecture.getName().toString();
        lectureName.setText(s);

        videoCount.setText("Videos: " + lecture.getVideoList().size());
        lecture.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LectureCard)) {
            return false;
        }

        // state check
        LectureCard card = (LectureCard) other;
        return id.getText().equals(card.id.getText()) && lecture.equals(card.lecture);
    }
}
