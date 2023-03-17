package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.video.Video;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class VideoCard extends UiPart<Region> {

    private static final String FXML = "VideoListCard.fxml";

    private final Video video;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label videoName;
    @FXML
    private Label watchedStatus;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCard} with the given {@code ReadOnlyModule}.
     */
    public VideoCard(Video video, int displayedIndex) {
        super(FXML);
        this.video = video;
        id.setText(displayedIndex + ". ");
        videoName.setText(video.getName().toString());

        watchedStatus.setText(video.hasWatched() ? "Watched" : "Not Watched");
        video.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VideoCard)) {
            return false;
        }

        // state check
        VideoCard card = (VideoCard) other;
        return id.getText().equals(card.id.getText()) && video.equals(card.video);
    }
}
