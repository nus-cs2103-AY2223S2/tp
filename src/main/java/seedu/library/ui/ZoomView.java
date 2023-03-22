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
    @FXML
    private Label zoomTag;


    /**
     * Constructs a ZoomView that displays the details of the provided bookmark.
     *
     * @param bookmark a single Bookmark object
     */
    public ZoomView(Bookmark bookmark) {
        super(FXML);
        this.bookmark = bookmark;
        viewTitle.setText("Title: " + bookmark.getTitle().value);
        authorView.setText("Author: " + bookmark.getAuthor().value);
        genreView.setText("Genre: " + bookmark.getGenre().value);
        progressView.setText("Progress: " + bookmark.getProgress().toString());
        bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));

    }


    /**
     * Method to hide the contents of ZoomView
     */
    public void hideFields() {
        viewTitle.setVisible(false);
        authorView.setVisible(false);
        genreView.setVisible(false);
        progressView.setVisible(false);
        tagsView.setVisible(false);
        zoomTag.setVisible(false);
    }

}
