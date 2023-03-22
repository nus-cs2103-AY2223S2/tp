package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bookmark extends UiPart<StackPane> {

    private static final String FXML = "Bookmark.fxml";

    @FXML
    private ImageView markedIcon;

    private Bookmark(boolean marked) {
        super(FXML);

        markedIcon.setVisible(marked);
    }

    public static Bookmark createBookmarkIcon(boolean marked) {
        return new Bookmark(marked);
    }
}
