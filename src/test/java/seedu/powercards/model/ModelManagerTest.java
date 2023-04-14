package seedu.powercards.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.model.Model.PREDICATE_SHOW_NO_CARDS;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.VARIABLE;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.model.card.QuestionContainsKeywordsPredicate;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.testutil.MasterDeckBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MasterDeck(), new MasterDeck(modelManager.getMasterDeck()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMasterDeckFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMasterDeckFilePath(Paths.get("new/address/book/file/path"));
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
    public void setMasterDeckFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMasterDeckFilePath(null));
    }

    @Test
    public void setMasterDeckFilePath_validPath_setsMasterDeckFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setMasterDeckFilePath(path);
        assertEquals(path, modelManager.getMasterDeckFilePath());
    }

    @Test
    public void hasCard_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCard(null));
    }

    @Test
    public void hasCard_cardNotInMasterDeck_returnsFalse() {
        assertFalse(modelManager.hasCard(LOOP));
    }

    @Test
    public void hasCard_cardInMasterDeck_returnsTrue() {
        modelManager.addCard(LOOP);
        assertTrue(modelManager.hasCard(LOOP));
    }

    @Test
    public void getFilteredCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCardList().remove(0));
    }

    @Test
    public void getDeckSize_success() {
        modelManager.addDeck(new Deck("Test"));
        assertEquals(0, modelManager.getDeckSize(0));
    }

    @Test
    public void getDeckSizeFilteredTag_success() {
        ModelManager model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        assertEquals(1, model.getDeckSizeFilteredTag(1,
                List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED})));
    }

    @Test
    public void equals() {
        MasterDeck masterDeck = new MasterDeckBuilder().withCard(LOOP).withCard(VARIABLE).build();
        MasterDeck differentMasterDeck = new MasterDeck();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(masterDeck, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(masterDeck, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different masterDeck -> returns false
        assertNotEquals(modelManager, new ModelManager(differentMasterDeck, userPrefs));

        // different filteredList -> returns false
        String[] keywords = LOOP.getQuestion().question.split("\\s+");
        modelManager.updateFilteredCardList(new QuestionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManager(masterDeck, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCardList(PREDICATE_SHOW_NO_CARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMasterDeckFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(masterDeck, differentUserPrefs));
    }
}
