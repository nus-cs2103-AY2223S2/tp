package seedu.library.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.library.model.bookmark.Bookmark;


/**
 * UI component that is responsible for displaying the details of a single bookmark.
 */
public class ZoomView extends UiPart<Region> {

    private static final String FXML = "ZoomView.fxml";
    private Bookmark bookmark;

    @FXML
    private Label viewTitle;
    @FXML
    private Label authorView;
    @FXML
    private Label genreView;
    @FXML
    private FlowPane tagsView;
    @FXML
    private Label progressView;


    /**
     * Constructs a ZoomView that displays the details of the provided bookmark.
     *
     * @param bookmark a single Bookmark object
     */
    public ZoomView(Bookmark bookmark) {
        super(FXML);
        this.bookmark = bookmark;
        viewTitle.setText(bookmark.getTitle().value);
        authorView.setText(bookmark.getAuthor().value);
        genreView.setText(bookmark.getGenre().value);
        progressView.setText(bookmark.getProgress().value);
        bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));

    }


//        public void setFeedbackToUser(String feedbackToUser) {
//            requireNonNull(feedbackToUser);
//            resultDisplay.setText(feedbackToUser);
//        }

}
