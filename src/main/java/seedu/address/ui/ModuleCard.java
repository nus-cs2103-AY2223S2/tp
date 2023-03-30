package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ReadOnlyModule;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

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
        progress.setText(getProgressText(module));

        module.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private String getProgressText(ReadOnlyModule module) {
        int totalLectureCount = module.getLectureList().size();

        if (totalLectureCount == 0) {
            return "No lectures found!";
        } else {
            int lectureCompletedCount = module.getLectureList().filtered(lecture -> {
                int watchCount = lecture.getVideoList().filtered(vid -> vid.hasWatched()).size();
                return watchCount == lecture.getVideoList().size();
            }).size();
            return String.format("Progress: %o/%o lectures covered", lectureCompletedCount, totalLectureCount);
        }
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
