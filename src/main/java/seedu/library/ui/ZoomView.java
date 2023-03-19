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
        private Label view_Title;
        @FXML
        private Label authorView;
        @FXML
        private Label GenreView;
        @FXML
        private FlowPane tagsView;
        @FXML
        private Label progressView;
        @FXML
        private Label zoomTag;



        public ZoomView(Bookmark bookmark) {
            super(FXML);
            this.bookmark = bookmark;
            view_Title.setText("Title: " + bookmark.getTitle().value);
            authorView.setText("Author: " + bookmark.getAuthor().value);
            GenreView.setText("Genre: " + bookmark.getGenre().value);
            progressView.setText("Progress: " + bookmark.getProgress().value);
            bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));

        }


        public void hideFields() {
            view_Title.setVisible(false);
            authorView.setVisible(false);
            GenreView.setVisible(false);
            progressView.setVisible(false);
            tagsView.setVisible(false);
            zoomTag.setVisible(false);

        }

}
