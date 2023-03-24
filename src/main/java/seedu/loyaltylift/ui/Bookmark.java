package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import seedu.loyaltylift.model.customer.Marked;

public class Bookmark extends UiPart<StackPane> {

    private static final String FXML = "Bookmark.fxml";

    @FXML
    private ImageView markedIcon;

    private Bookmark(Marked marked) {
        super(FXML);


        markedIcon.setVisible(marked.value);
    }

    public static Bookmark createBookmarkIcon(Marked marked) {
        return new Bookmark(marked);
    }
}
