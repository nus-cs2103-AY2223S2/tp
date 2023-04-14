package seedu.library.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;
import seedu.library.model.bookmark.Url;
import seedu.library.model.tag.Tag;
import seedu.library.model.util.SampleDataUtil;

/**
 * A utility class to help with building Bookmark objects.
 */
public class BookmarkBuilder {

    public static final String DEFAULT_TITLE = "Chainsaw Man";
    public static final String[] DEFAULT_PROGRESS = {"1", "50", "~"};
    public static final String DEFAULT_GENRE = "Fantasy";
    public static final String DEFAULT_AUTHOR = "Tatsuki Fujimoto";
    public static final String DEFAULT_URL = "https://www.abc.com";

    private Title title;
    private Progress progress;
    private Genre genre;
    private Author author;
    private Rating rating;
    private Url url;
    private Set<Tag> tags;

    /**
     * Creates a {@code BookmarkBuilder} with the default details.
     */
    public BookmarkBuilder() {
        title = new Title(DEFAULT_TITLE);
        progress = new Progress(DEFAULT_PROGRESS);
        genre = new Genre(DEFAULT_GENRE);
        author = new Author(DEFAULT_AUTHOR);
        url = new Url(DEFAULT_URL);
        tags = new HashSet<>();
        rating = Rating.DEFAULT_RATING;
    }

    /**
     * Initializes the BookmarkBuilder with the data of {@code bookmarkToCopy}.
     */
    public BookmarkBuilder(Bookmark bookmarkToCopy) {
        title = bookmarkToCopy.getTitle();
        progress = bookmarkToCopy.getProgress();
        genre = bookmarkToCopy.getGenre();
        author = bookmarkToCopy.getAuthor();
        url = bookmarkToCopy.getUrl();
        rating = bookmarkToCopy.getRating();
        tags = new HashSet<>(bookmarkToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withAuthor(String author) {
        if (author == null) {
            this.author = null;
        } else {
            this.author = new Author(author);
        }
        return this;
    }

    /**
     * Sets the {@code Progress} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withProgress(String progress) {
        if (progress == null) {
            this.progress = null;
        } else {
            String[] splitProgress = progress.split(" ");
            this.progress = new Progress(splitProgress);
        }
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withGenre(String genre) {
        this.genre = new Genre(genre);
        return this;
    }
    /**
     * Sets the {@code Url} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withUrl(String url) {
        this.url = new Url(url);
        return this;
    }

    public Bookmark build() {
        return new Bookmark(title, progress, genre, author, rating, url, tags);
    }

}
