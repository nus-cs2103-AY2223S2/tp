package seedu.library.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.library.model.Library;
import seedu.library.model.ReadOnlyLibrary;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.Tags;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;
import seedu.library.model.bookmark.Url;
import seedu.library.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static Bookmark[] getSampleBookmarks() {
        String[] sampleProgressA = {"1", "40", "~"};
        String[] sampleProgressB = {"~", "2", "~"};
        String[] sampleProgressC = {"~", "~", "50"};
        return new Bookmark[] {
            new Bookmark(new Title("Rankers Guide"), new Progress(sampleProgressA),
                    new Genre("Fantasy"), new Author("TeJe"), new Rating("4"),
                    new Url("https://www.wuxiaworld.eu/novel/the-rankers-guide-to-live-an-ordinary-life"),
                    getTagSet("Hunters")),
            new Bookmark(new Title("Chainsaw Man"), new Progress(sampleProgressB), new Genre("Action"),
                    new Author("Tatsuki Fujimoto"), new Rating("5"),
                    new Url("https://www.chainsaw-man-manga.online/"),
                    getTagSet("Gore")),
            new Bookmark(new Title("Solo Leveling"), new Progress(sampleProgressC), new Genre("Fantasy"),
                    new Author("Chugong"), new Rating("3"),
                   new Url("https://sololeveling-manhwa.online/"),
                    getTagSet("Hunters", "System", "Cheats")),
            new Bookmark(new Title("Dungeon Defense"), new Progress(sampleProgressA), new Genre("Fantasy"),
                    new Author("Yoo Heonhwa"), new Rating("4"),
                    new Url("https://www.wuxiaworld.eu/novel/dungeon-defense-wn"),
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

    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag("MaleProtagonist"),
            new Tag("FemaleProtagonist"),
            new Tag("Manhwa"),
            new Tag("Manhua"),
            new Tag("Manga"),
            new Tag("Novel"),
            new Tag("Hunters"),
            new Tag("Gore"),
            new Tag("Cheats"),
            new Tag("System"),
            new Tag("Antihero"),
            new Tag("Literature"),
            new Tag("School")
        };
    }

    public static ReadOnlyTags getSampleTagList() {
        Tags sampleTags = new Tags();
        for (Tag sampleTag : getSampleTags()) {
            sampleTags.addTag(sampleTag);
        }
        return sampleTags;
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
