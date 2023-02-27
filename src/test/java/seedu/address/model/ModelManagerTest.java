package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BART;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.testutil.EduMateBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EduMate(), new EduMate(modelManager.getEduMate()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setEduMateFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setEduMateFilePath(Paths.get("new/address/book/file/path"));
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
    public void setEduMateFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEduMateFilePath(null));
    }

    @Test
    public void setEduMateFilePath_validPath_setsEduMateFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setEduMateFilePath(path);
        assertEquals(path, modelManager.getEduMateFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEduMate_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALBERT));
    }

    @Test
    public void hasPerson_personInEduMate_returnsTrue() {
        modelManager.addPerson(ALBERT);
        assertTrue(modelManager.hasPerson(ALBERT));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        EduMate eduMate = new EduMateBuilder().withPerson(ALBERT).withPerson(BART).build();
        EduMate differentEduMate = new EduMate();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(eduMate, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(eduMate, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different eduMate -> returns false
        assertNotEquals(modelManager, new ModelManager(differentEduMate, userPrefs));

        // different filteredList -> returns false
        createEqualsFilteredList(
                CliSyntax.PREFIX_NAME, ALBERT.getName().value.split("\\s+"), eduMate, userPrefs);
        createEqualsFilteredList(
                CliSyntax.PREFIX_EMAIL, ALBERT.getEmail().value.split("\\s+"), eduMate, userPrefs);
        createEqualsFilteredList(
                CliSyntax.PREFIX_PHONE, ALBERT.getPhone().value.split("\\s+"), eduMate, userPrefs);
        createEqualsFilteredList(
                CliSyntax.PREFIX_ADDRESS, ALBERT.getAddress().value.split("\\s+"), eduMate, userPrefs);
        createEqualsFilteredList(
                CliSyntax.PREFIX_TELEGRAM_HANDLE, ALBERT.getTelegramHandle().value.split("\\s+"),
                eduMate, userPrefs);

        createEqualsFilteredList(
                CliSyntax.PREFIX_MODULE_TAG,
                ALBERT.getImmutableModuleTags().toString().replaceAll("[\\[\\], ]", "").split(" "),
                eduMate, userPrefs);

        createEqualsFilteredList(
                CliSyntax.PREFIX_GROUP_TAG,
                ALBERT.getImmutableGroupTags().toString().replaceAll("[\\[\\], ]", "").split(" "),
                eduMate, userPrefs);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEduMateFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(eduMate, differentUserPrefs));
    }

    public void createEqualsFilteredList(Prefix prefix, String[] keywords, EduMate eduMate, UserPrefs userPrefs) {
        modelManager.updateFilteredPersonList(
                new ContainsKeywordsPredicate(Arrays.asList(keywords), prefix));
        assertNotEquals(modelManager, new ModelManager(eduMate, userPrefs));
    }
}
