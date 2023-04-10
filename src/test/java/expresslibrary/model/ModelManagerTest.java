package expresslibrary.model;

import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalPersons.ALICE;
import static expresslibrary.testutil.TypicalPersons.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.book.exceptions.BookNotFoundException;
import expresslibrary.model.person.NameContainsKeywordsPredicate;
import expresslibrary.testutil.ExpressLibraryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ExpressLibrary(), new ExpressLibrary(modelManager.getExpressLibrary()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setExpressLibraryFilePath(Paths.get("expresslibrary/prefs/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setExpressLibraryFilePath(Paths.get("new/expresslibrary/prefs/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setExpressLibraryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setExpressLibraryFilePath(null));
    }

    @Test
    public void setExpressLibraryFilePath_validPath_setsExpressLibraryFilePath() {
        Path path = Paths.get("expresslibrary/prefs/file/path");
        modelManager.setExpressLibraryFilePath(path);
        assertEquals(path, modelManager.getExpressLibraryFilePath());
    }

    @Test
    public void hasBook_bookNotInExpressLibrary_returnsFalse() {
        assertFalse(modelManager.hasBook(new Book(new Title("Harry Potter and the Cursed Child"),
                new Author("J.K. Rowling"), new Isbn("1234567890"))));
    }

    @Test
    public void setBook_bookNotFound_throwsException() {
        Book book = new Book(new Title("Harry Potter and the Cursed Child"),
                new Author("J.K. Rowling"), new Isbn("1234567890"));
        Book editedBook = new Book(new Title("Harry Potter and the Goblet of Fire"),
                new Author("J.K. Rowling"), new Isbn("1234567980"));

        assertThrows(BookNotFoundException.class, () -> modelManager.setBook(book, editedBook));
        assertThrows(NullPointerException.class, () -> modelManager.setBook(null, null));
    }

    @Test
    public void getBook_bookIsNotInExpressLibrary_throwsBookNotFoundException() {
        Book book = new Book(new Title("Harry Potter and the Cursed Child"),
                new Author("J.K. Rowling"), new Isbn("1234567890"));
        assertThrows(BookNotFoundException.class, () -> modelManager.getBook(book));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInExpressLibrary_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInExpressLibrary_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        ExpressLibrary expressLibrary = new ExpressLibraryBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ExpressLibrary differentExpressLibrary = new ExpressLibrary();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(expressLibrary, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(expressLibrary, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different expressLibrary -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentExpressLibrary, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(expressLibrary, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setExpressLibraryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(expressLibrary, differentUserPrefs)));
    }
}
