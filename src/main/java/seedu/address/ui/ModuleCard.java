package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ReadOnlyModule;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";
    private static final String NO_LECTURES_FOUND_TEXT = "No lectures found";
    private static final String LECTURE_PROGRESS_FORMAT = "Covered %o/%o lectures";

    private final ReadOnlyModule module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleName;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progress;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCard} with the given {@code ReadOnlyModule}.
     */
    public ModuleCard(ReadOnlyModule module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getCode().toString());
        moduleName.setText(module.getName().toString());

        setProgressUi(module);

        module.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setProgressUi(ReadOnlyModule module) {
        int totalLectureCount = module.getLectureList().size();
        int lectureCompletedCount = 0;

        if (totalLectureCount > 0) {
            lectureCompletedCount = module.getLectureList().filtered(lecture -> {
                int watchCount = lecture.getVideoList().filtered(vid -> vid.hasWatched()).size();
                return watchCount == lecture.getVideoList().size();
            }).size();
        }

        progress.setText(getProgressText(lectureCompletedCount, totalLectureCount));
        progressBar.setProgress(totalLectureCount == 0 ? 0 : (double) lectureCompletedCount / totalLectureCount);
    }

    private String getProgressText(int completed, int total) {
        return total == 0 ? NO_LECTURES_FOUND_TEXT : String.format(LECTURE_PROGRESS_FORMAT, completed, total);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText()) && module.equals(card.module);
    }
}
