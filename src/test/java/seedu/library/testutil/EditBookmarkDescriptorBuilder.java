package seedu.library.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.library.logic.commands.EditCommand.EditBookmarkDescriptor;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;
import seedu.library.model.bookmark.Url;
import seedu.library.model.tag.Tag;

/**
 * A utility class to help with building EditBookmarkDescriptor objects.
 */
public class EditBookmarkDescriptorBuilder {

    private EditBookmarkDescriptor descriptor;

    public EditBookmarkDescriptorBuilder() {
        descriptor = new EditBookmarkDescriptor();
    }

    public EditBookmarkDescriptorBuilder(EditBookmarkDescriptor descriptor) {
        this.descriptor = new EditBookmarkDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookmarkDescriptor} with fields containing {@code bookmark}'s details
     */
    public EditBookmarkDescriptorBuilder(Bookmark bookmark) {
        descriptor = new EditBookmarkDescriptor();
        descriptor.setTitle(bookmark.getTitle());
        descriptor.setProgress(bookmark.getProgress());
        descriptor.setGenre(bookmark.getGenre());
        descriptor.setAuthor(bookmark.getAuthor());
        descriptor.setTags(bookmark.getTags());
        descriptor.setRating(bookmark.getRating());
        descriptor.setUrl(bookmark.getUrl());
    }

    /**
     * Sets the {@code Title} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Progress} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withProgress(String progress) {
        String[] splitProgress = progress.split(" ");
        descriptor.setProgress(new Progress(splitProgress));
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withGenre(String genre) {
        descriptor.setGenre(new Genre(genre));
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withAuthor(String author) {
        descriptor.setAuthor(new Author(author));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBookmarkDescriptor}
     * that we are building.
     */
    public EditBookmarkDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new Url(url));
        return this;
    }

    public EditBookmarkDescriptor build() {
        return descriptor;
    }
}
