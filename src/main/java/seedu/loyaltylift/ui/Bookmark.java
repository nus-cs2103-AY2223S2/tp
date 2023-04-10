package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import seedu.loyaltylift.model.customer.Marked;

/**
 * A small icon that indicates whether the customer is bookmarked.
 */
public class Bookmark extends UiPart<StackPane> {

    private static final String FXML = "Bookmark.fxml";

    @FXML
    private ImageView markedIcon;

    /**
     * Creates a {@code Bookmark} based on the marked status of the customer.
     * @param marked The marked status of the customer.
     */
    private Bookmark(Marked marked) {
        super(FXML);

        markedIcon.setVisible(marked.value);
    }

    /**
     * Constructs a {@code Bookmark} based on the marked status of the customer.
     * @param marked The marked status of the customer.
     * @return An instance of the Bookmark.
     */
    public static Bookmark createBookmarkIcon(Marked marked) {
        if (marked instanceof Marked) {
            return new Bookmark(marked);
        } else {
            throw new NullPointerException();
        }
    }
}
