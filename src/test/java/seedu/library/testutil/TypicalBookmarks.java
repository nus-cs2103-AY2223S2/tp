package seedu.library.testutil;

import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_GENRE_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PROGRESS_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PROGRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.library.model.Library;
import seedu.library.model.bookmark.Bookmark;

/**
 * A utility class containing a list of {@code Bookmark} objects to be used in tests.
 */
public class TypicalBookmarks {

    public static final Bookmark AOT = new BookmarkBuilder().withTitle("Attack on Titans")
            .withAuthor("Hajime Isayama").withGenre("Action")
            .withProgress("1 2 ~").withUrl("https://aotmanga.com/attack-on-titan/chapter-139/")
            .withTags("MaleProtagonist").build();

    public static final String AOTString = "Attack on Titans"
            + "; Progress: Vol. 1 Ch. 2"
            + "; Genre: Action; Author: Hajime Isayama"
            + "; Rating: 0"
            + "; Url: https://aotmanga.com/attack-on-titan/chapter-139/"
            + "; Tags: [MaleProtagonist]";

    public static final Bookmark BENSON = new BookmarkBuilder().withTitle("Benson Meier")
            .withAuthor("311, Clementi Ave 2, #02-25")
            .withGenre("Adventure").withProgress("1 2 ~").withUrl("https://www.def.com")
            .withTags("MaleProtagonist", "FemaleProtagonist").build();
    public static final Bookmark CARL = new BookmarkBuilder().withTitle("Carl Kurz")
            .withProgress("1 2 ~")
            .withGenre("Comedy").withAuthor("wall street").build();
    public static final Bookmark DANIEL = new BookmarkBuilder().withTitle("Daniel Meier")
            .withProgress("1 2 ~")
            .withGenre("Drama").withAuthor("10th street").withTags("friends").build();
    public static final Bookmark ELLE = new BookmarkBuilder().withTitle("Elle Meyer")
            .withProgress("1 2 ~")
            .withGenre("Fantasy").withAuthor("michegan ave").build();
    public static final Bookmark FIONA = new BookmarkBuilder().withTitle("Fiona Kunz")
            .withProgress("1 2 ~")
            .withGenre("Historical").withAuthor("little tokyo").build();
    public static final Bookmark GEORGE = new BookmarkBuilder().withTitle("George Best")
            .withProgress("1 2 ~")
            .withGenre("Horror").withAuthor("4th street").build();

    // Manually added
    public static final Bookmark HOON = new BookmarkBuilder().withTitle("Hoon Meier")
            .withProgress("1 2 ~")
            .withGenre("Martial Arts").withAuthor("little india").build();
    public static final Bookmark IDA = new BookmarkBuilder().withTitle("Ida Mueller")
            .withProgress("1 2 ~")
            .withGenre("Others").withAuthor("chicago ave").build();

    // Manually added - Bookmark's details found in {@code CommandTestUtil}
    public static final Bookmark AMY = new BookmarkBuilder().withTitle(VALID_TITLE_AMY).withProgress(VALID_PROGRESS_AMY)
            .withGenre(VALID_GENRE_AMY).withAuthor(VALID_AUTHOR_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Bookmark BOB = new BookmarkBuilder().withTitle(VALID_TITLE_BOB).withProgress(VALID_PROGRESS_BOB)
            .withGenre(VALID_GENRE_BOB).withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBookmarks() {} // prevents instantiation

    /**
     * Returns an {@code Library} with all the typical Bookmarks.
     */
    public static Library getTypicalLibrary() {
        Library ab = new Library();
        for (Bookmark bookmark : getTypicalBookmarks()) {
            ab.addBookmark(bookmark);
        }
        return ab;
    }

    public static List<Bookmark> getTypicalBookmarks() {
        return new ArrayList<>(Arrays.asList(AOT, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
