package seedu.library.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.library.model.bookmark.Bookmark;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import java.util.Comparator;

import static java.util.Objects.requireNonNull;
import seedu.library.model.bookmark.Bookmark;


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
