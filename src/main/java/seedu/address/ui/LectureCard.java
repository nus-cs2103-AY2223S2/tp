package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lecture.ReadOnlyLecture;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class LectureCard extends UiPart<Region> {

    private static final String FXML = "LectureListCard.fxml";
    private static final String NO_VIDEOS_FOUND_TEXT = "No videos found";
    private static final String VIDEO_PROGRESS_FORMAT = "Watched %o/%o videos";

    private final ReadOnlyLecture lecture;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label lectureName;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progress;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCard} with the given {@code ReadOnlyModule}.
     */
    public LectureCard(ReadOnlyLecture lecture, int displayedIndex) {
        super(FXML);
        this.lecture = lecture;
        id.setText(displayedIndex + ". ");
        lectureName.setText(lecture.getName().toString());

        progress.setText(getProgressText(lecture));
        lecture.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private String getProgressText(ReadOnlyLecture lecture) {
        int videoCount = lecture.getVideoList().size();
        String progressText = NO_VIDEOS_FOUND_TEXT;

        double progressPerc = 0;

        if (videoCount > 0) {
            int watched = lecture.getVideoList().filtered(vid -> vid.hasWatched()).size();
            progressText = String.format(VIDEO_PROGRESS_FORMAT, watched, videoCount);
            progressPerc = (double) watched / videoCount;
        }

        progressBar.setProgress(progressPerc);
        return progressText;
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
