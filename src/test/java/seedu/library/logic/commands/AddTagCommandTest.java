package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Library;
import seedu.library.model.Model;
import seedu.library.model.ReadOnlyLibrary;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.ReadOnlyUserPrefs;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.tag.Tag;
import seedu.library.testutil.TagsBuilder;

public class AddTagCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Set<Tag> validTags = new TagsBuilder().build();
        ArrayList<Tag> checkValidTags = new ArrayList<>(validTags);

        CommandResult commandResult = new AddTagCommand(validTags).execute(modelStub);

        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, validTags), commandResult.getFeedbackToUser());
        assertEquals(checkValidTags, modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Set<Tag> validTags = new TagsBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(validTags);
        ModelStub modelStub = new ModelStubWithTag(validTags);

        assertThrows(CommandException.class,
                AddTagCommand.MESSAGE_DUPLICATE_TAGS, () -> addTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Set<Tag> novelPlant = new TagsBuilder().build();
        Set<Tag> novelPlantOcean = new TagsBuilder().addTag("ocean").build();
        AddTagCommand addNovelPlantCommand = new AddTagCommand(novelPlant);
        AddTagCommand addNovelPlantOceanCommand = new AddTagCommand(novelPlantOcean);

        // same object -> returns true
        assertTrue(addNovelPlantCommand.equals(addNovelPlantCommand));

        // same values -> returns true
        AddTagCommand addNovelPlantCommandCopy = new AddTagCommand(novelPlant);
        assertTrue(addNovelPlantCommandCopy.equals(addNovelPlantCommandCopy));

        // different types -> returns false
        assertFalse(addNovelPlantCommand.equals(1));

        // null -> returns false
        assertFalse(addNovelPlantCommand.equals(null));

        // different bookmark -> returns false
        assertFalse(addNovelPlantCommand.equals(addNovelPlantOceanCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibraryFilePath(Path libraryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBookmark(Bookmark bookmark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibrary(ReadOnlyLibrary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBookmark(Bookmark bookmark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBookmark(Bookmark target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookmark(Bookmark target, Bookmark editedBookmark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTags getTags() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String tagListToString() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Set<Tag> tags) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tags) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTags(Set<Tag> tags) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tags) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean tagInUse(Tag target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bookmark> getFilteredBookmarkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookmarkList(Predicate<Bookmark> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedBookmarkList(String order) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Bookmark getSelectedBookmark() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSelectedBookmark(Bookmark target) {
            assert(true);
        }

        @Override
        public int getSelectedIndex() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateSelectedIndex(int index) {
            assert(true);
        };
    }

    /**
     * A Model stub that contains a single tag.
     */
    private class ModelStubWithTag extends ModelStub {
        private final Set<Tag> tagsAdded;

        ModelStubWithTag(Set<Tag> tags) {
            requireNonNull(tags);
            this.tagsAdded = tags;
        }

        @Override
        public boolean hasTag(Set<Tag> tags) {
            requireNonNull(tags);
            return tagsAdded.containsAll(tags);
        }
    }

    /**
     * A Model stub that always accept the tag being added.
     */
    private class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Set<Tag> tags) {
            requireNonNull(tags);
            return tagsAdded.containsAll(tags);
        }

        @Override
        public void addTags(Set<Tag> tags) {
            requireNonNull(tags);
            tagsAdded.addAll(tags);
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            return new Library();
        }
    }

}
