package seedu.library.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Rating;

/**
 * An UI component that displays information of a {@code Bookmark}.
 */
public class BookmarkCard extends UiPart<Region> {


    private static final String FXML = "BookmarkListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/Library-level4/issues/336">The issue on Library level 4</a>
     */

    public final Bookmark bookmark;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label progress;
    @FXML
    private Label author;
    @FXML
    private Label genre;
    @FXML
    private FlowPane tags;

    @FXML
    private Hyperlink link;
    @FXML
    private ImageView vol;
    @FXML
    private ImageView chap;
    @FXML
    private ImageView page;
    @FXML
    private Label chaptert;
    @FXML
    private Label paget;
    @FXML
    private ImageView rateHead;
    @FXML
    private ImageView ratingI;




    /**
     * Creates a {@code BookmarkCode} with the given {@code Bookmark} and index to display.
     */
    public BookmarkCard(Bookmark bookmark, int displayedIndex) throws IOException {
        super(FXML);
        InputStream svol = this.getClass().getResourceAsStream("/images/volume.png");
        InputStream schap = this.getClass().getResourceAsStream("/images/chapter.png");
        InputStream spage = this.getClass().getResourceAsStream("/images/page.png");
        InputStream ratingImage = this.getClass().getResourceAsStream("/images/ratingHead.png");
        this.bookmark = bookmark;
        id.setText(displayedIndex + ". ");
        title.setText(bookmark.getTitle().value);

        if (bookmark.getProgress() != null) {
            progress.setText(bookmark.getProgress().getVolume());
            chaptert.setText(bookmark.getProgress().getChapter());
            paget.setText(bookmark.getProgress().getPage());
        } else {
            progress.setText("~");
            chaptert.setText("~");
            paget.setText("~");
        }

        vol.setImage(new Image(svol));
        chap.setImage(new Image(schap));
        page.setImage(new Image(spage));

        if (bookmark.getAuthor() != null) {
            author.setText(bookmark.getAuthor().value);
        } else {
            author.setText("");
        }

        genre.setText(bookmark.getGenre().value);
        rateHead.setImage(new Image(ratingImage));
        rateCard(bookmark);
        bookmark.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
    /**
     * Helps set rating image in bookmarkcard
     * @param bookmark
     */
    public void rateCard(Bookmark bookmark) throws IOException {
        InputStream rating0 = this.getClass().getResourceAsStream("/images/Rating0.png");
        InputStream rating1 = this.getClass().getResourceAsStream("/images/Rating1.png");
        InputStream rating2 = this.getClass().getResourceAsStream("/images/Rating2.png");
        InputStream rating3 = this.getClass().getResourceAsStream("/images/Rating3.png");
        InputStream rating4 = this.getClass().getResourceAsStream("/images/Rating4.png");
        InputStream rating5 = this.getClass().getResourceAsStream("/images/Rating5.png");

        if (bookmark.getRating().equals(Rating.DEFAULT_RATING)) {
            ratingI.setImage(new Image(rating0));
            return;
        }

        String rating = bookmark.getRating().toString();

        if (rating.equals("1")) {
            ratingI.setImage(new Image(rating1));
        } else if (rating.equals("2")) {
            ratingI.setImage(new Image(rating2));
        } else if (rating.equals("3")) {
            ratingI.setImage(new Image(rating3));
        } else if (rating.equals("4")) {
            ratingI.setImage(new Image(rating4));
        } else if (rating.equals("5")) {
            ratingI.setImage(new Image(rating5));
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookmarkCard)) {
            return false;
        }

        // state check
        BookmarkCard card = (BookmarkCard) other;
        return id.getText().equals(card.id.getText())
                && bookmark.equals(card.bookmark);
    }
}
