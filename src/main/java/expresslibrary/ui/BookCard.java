package expresslibrary.ui;

import java.time.LocalDate;

import expresslibrary.commons.util.DateUtil;
import expresslibrary.model.book.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Book}.
 */
public class BookCard extends UiPart<Region> {

    private static final String FXML = "BookListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label isbn;
    @FXML
    private Label borrowerName;
    @FXML
    private Label borrowDate;
    @FXML
    private Label dueDate;
    /**
     * Creates a {@code BookCode} with the given {@code Book} and index to display.
     */
    public BookCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        title.setText(book.getTitle().title);
        author.setText(book.getAuthor().name);
        isbn.setText(book.getIsbn().isbn);
        if (book.getBorrower() != null) {
            borrowerName.setText(String.format("Borrowed by: %s", book.getBorrower().getName().fullName));
            borrowDate.setText(String.format("Borrowed date: %s", DateUtil.formatDate(book.getBorrowDate())));

            dueDate.setText(String.format("Due date: %s", DateUtil.formatDate(book.getDueDate())));
            if (book.getDueDate().isBefore(LocalDate.now())) {
                dueDate.setStyle("-fx-text-fill: orange;");
            } else if (book.getDueDate().isBefore(LocalDate.now().plusDays(3))) {
                dueDate.setStyle("-fx-text-fill: yellow;");
            }

            borrowerName.setVisible(true);
            borrowDate.setVisible(true);
            dueDate.setVisible(true);
        } else {
            borrowerName.setVisible(false);
            borrowDate.setVisible(false);
            dueDate.setVisible(false);
        }
        borrowerName.managedProperty().bind(borrowerName.visibleProperty());
        borrowDate.managedProperty().bind(borrowDate.visibleProperty());
        dueDate.managedProperty().bind(dueDate.visibleProperty());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookCard)) {
            return false;
        }

        // state check
        BookCard card = (BookCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
