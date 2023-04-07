package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.COMPARATOR_CONTACT_INDEX_RECOMMENDATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECOMMENDATIONS;
import static seedu.address.model.recommendation.TypicalRecommendations.RECOMMENDATION_STEVENS_THU_10AM_2HR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BART;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.testutil.EduMateBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EduMate(), new EduMate(modelManager.getEduMate()));
        assertEquals(new EduMateHistory(), modelManager.getEduMateHistory());
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
    public void hasRecommendation_nullRecommendation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRecommendation(null));
    }

    @Test
    public void hasPerson_personNotInEduMate_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALBERT));
    }

    @Test
    public void hasRecommendation_recommendationNotInEduMate_returnsFalse() {
        assertFalse(modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR));
    }

    @Test
    public void hasPerson_personInEduMate_returnsTrue() {
        modelManager.addPerson(ALBERT);
        assertTrue(modelManager.hasPerson(ALBERT));
    }

    @Test
    public void hasRecommendation_recommendationInEduMate_returnsTrue() {
        modelManager.addRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR);
        assertTrue(modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR));
    }

    @Test
    public void deletePerson_personInEduMate_success() {
        if (!modelManager.hasPerson(ALBERT)) {
            modelManager.addPerson(ALBERT);
        }
        modelManager.deletePerson(ALBERT);
        assertFalse(modelManager.hasPerson(ALBERT));
    }

    @Test
    public void deleteRecommendation_recommendationInEduMate_success() {
        if (!modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR)) {
            modelManager.addRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR);
        }
        assertTrue(modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR));
        modelManager.deleteRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR);
        assertFalse(modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR));
    }

    @Test
    public void getObservablePersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getObservablePersonList().remove(0));
    }

    @Test
    public void getObservableRecommendationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getObservableRecommendationList().remove(0));
    }

    @Test
    public void updateObservableRecommendationList_validComparator_success() {
        assertDoesNotThrow(() -> modelManager
                .updateObservableRecommendationList(COMPARATOR_CONTACT_INDEX_RECOMMENDATION));
    }

    @Test
    public void updateObservableRecommendationList_validPredicate_success() {
        assertDoesNotThrow(() -> modelManager
                .updateObservableRecommendationList(PREDICATE_SHOW_ALL_RECOMMENDATIONS));
    }

    @Test
    public void getRecommendationByIndex_validIndex_success() {
        if (!modelManager.hasRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR)) {
            modelManager.addRecommendation(RECOMMENDATION_STEVENS_THU_10AM_2HR);
        }

        assertTrue(modelManager.getRecommendationByIndex(new ContactIndex(1)).isPresent());
        assertEquals(RECOMMENDATION_STEVENS_THU_10AM_2HR,
                modelManager.getRecommendationByIndex(new ContactIndex(1)).get());
    }

    @Test
    public void setEduMate() {
        EduMate newEduMate = new EduMate(modelManager.getEduMate());
        modelManager.setEduMate(newEduMate);
        assertEquals(newEduMate, modelManager.getEduMate());
    }

    @Test
    public void equals() {
        EduMate eduMate = new EduMateBuilder().withPerson(ALBERT).withPerson(BART).build();
        EduMate differentEduMate = new EduMate();
        UserPrefs userPrefs = new UserPrefs();
        EduMateHistory eduMateHistory = new EduMateHistory();

        // same values -> returns true
        modelManager = new ModelManager(eduMate, userPrefs, eduMateHistory);
        ModelManager modelManagerCopy = new ModelManager(eduMate, userPrefs, eduMateHistory);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different eduMate -> returns false
        assertNotEquals(modelManager, new ModelManager(differentEduMate, userPrefs, eduMateHistory));

        // different filteredList -> returns false
        createEqualsFilteredList(
                Prefix.NAME, ALBERT.getName().getValue().split("\\s+"), eduMate, userPrefs, eduMateHistory);
        createEqualsFilteredList(
                Prefix.EMAIL, ALBERT.getEmail().getValue().split("\\s+"), eduMate, userPrefs, eduMateHistory);
        createEqualsFilteredList(
                Prefix.PHONE, ALBERT.getPhone().getValue().split("\\s+"), eduMate, userPrefs, eduMateHistory);
        createEqualsFilteredList(
                Prefix.STATION, ALBERT.getStation().getValue().getName().split("\\s+"),
                eduMate, userPrefs, eduMateHistory);
        createEqualsFilteredList(
                Prefix.TELEGRAM_HANDLE, ALBERT.getTelegramHandle().getValue().split("\\s+"),
                eduMate, userPrefs, eduMateHistory);

        createEqualsFilteredList(
                Prefix.GROUP_TAG,
                ALBERT.getImmutableGroupTags().toString().replaceAll("[\\[\\], ]", "").split(" "),
                eduMate, userPrefs, eduMateHistory);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateObservablePersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEduMateFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(eduMate, differentUserPrefs, eduMateHistory));
    }

    public void createEqualsFilteredList(
            Prefix prefix, String[] keywords, EduMate eduMate, UserPrefs userPrefs, EduMateHistory eduMateHistory) {
        modelManager.updateObservablePersonList(
                new ContainsKeywordsPredicate(Arrays.asList(keywords), prefix));
        assertNotEquals(modelManager, new ModelManager(eduMate, userPrefs, eduMateHistory));
    }
}
