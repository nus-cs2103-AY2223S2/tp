package seedu.library.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.library.model.Library;
import seedu.library.model.ReadOnlyLibrary;
import seedu.library.model.bookmark.*;
import seedu.library.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static final Url EMPTY_URL = new Url("");
    public static Bookmark[] getSampleBookmarks() {
        return new Bookmark[] {
            new Bookmark(new Title("Rankers Guide"), new Progress("Chapter 40"),
                    new Genre("Modern Fantasy"), new Author("TeJe"),
                    EMPTY_URL,
                    getTagSet("Hunters")),
            new Bookmark(new Title("Chainsaw Man"), new Progress("Not Started"), new Genre("Action"),
                    new Author("Tatsuki Fujimoto"),
                    EMPTY_URL,
                    getTagSet("Gore")),
            new Bookmark(new Title("Solo Leveling"), new Progress("Chapter 120"), new Genre("Modern Fantasy"),
                    new Author("Chugong"),
                    EMPTY_URL,
                    getTagSet("Hunters", "System", "Cheats")),
            new Bookmark(new Title("Dungeon Defense"), new Progress("Chapter 40"), new Genre("Western Fantasy"),
                    new Author("Yoo Heonhwa"),
                    EMPTY_URL,
                    getTagSet("Antihero"))
        };
    }
    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleAb = new Library();
        for (Bookmark sampleBookmark : getSampleBookmarks()) {
            sampleAb.addBookmark(sampleBookmark);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
