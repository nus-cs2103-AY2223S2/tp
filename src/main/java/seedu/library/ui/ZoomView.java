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



        public ZoomView(Bookmark bookmark) {
            super(FXML);
            this.bookmark = bookmark;
            view_Title.setText(bookmark.getTitle().value);
            authorView.setText(bookmark.getAuthor().value);
            GenreView.setText(bookmark.getGenre().value);
            progressView.setText(bookmark.getProgress().value);
            bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));

        }


//        public void setFeedbackToUser(String feedbackToUser) {
//            requireNonNull(feedbackToUser);
//            resultDisplay.setText(feedbackToUser);
//        }

    }
