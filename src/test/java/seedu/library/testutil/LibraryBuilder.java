package seedu.library.testutil;

import seedu.library.model.Library;
import seedu.library.model.bookmark.Bookmark;

/**
 * A utility class to help with building Library objects.
 * Example usage: <br>
 *     {@code Library ab = new LibraryBuilder().withBookmark("John", "Doe").build();}
 */
public class LibraryBuilder {

    private Library library;

    public LibraryBuilder() {
        library = new Library();
    }

    public LibraryBuilder(Library library) {
        this.library = library;
    }

    /**
     * Adds a new {@code Bookmark} to the {@code Library} that we are building.
     */
    public LibraryBuilder withBookmark(Bookmark bookmark) {
        library.addBookmark(bookmark);
        return this;
    }

    public Library build() {
        return library;
    }
}
