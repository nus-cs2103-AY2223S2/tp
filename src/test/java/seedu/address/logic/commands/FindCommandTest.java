package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLectures.CS2040S_WEEK_1;
import static seedu.address.testutil.TypicalModules.CS2040S;
import static seedu.address.testutil.TypicalModules.ST2334;
import static seedu.address.testutil.TypicalModules.getTypicalTracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Level;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.CodeContainsKeywordsPredicate;
import seedu.address.model.module.LectureNameContainsKeywordsPredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.VideoNameContainsKeywordsPredicate;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTracker(), new UserPrefs());

    @Test
    public void equals() {
        List<String> keywordsA = Arrays.asList("Apple", "Aries");
        List<String> keywordsB = Arrays.asList("Banana", "Base");

        FindCommand findFirstCommand = new FindCommand(keywordsA);
        FindCommand findSecondCommand = new FindCommand(keywordsB);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(keywordsA);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different modules -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        CodeContainsKeywordsPredicate predicate = (CodeContainsKeywordsPredicate) preparePredicate(" ", Level.MODULE);
        FindCommand command = new FindCommand(Collections.emptyList());
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_zeroKeywords_noLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 0);
        LectureNameContainsKeywordsPredicate predicate =
            (LectureNameContainsKeywordsPredicate) preparePredicate(" ", Level.LECTURE);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(Collections.emptyList(), moduleCode);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLectureList());
    }

    @Test
    public void execute_zeroKeywords_noVideosFound() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 0);
        VideoNameContainsKeywordsPredicate predicate =
            (VideoNameContainsKeywordsPredicate) preparePredicate(" ", Level.VIDEO);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ReadOnlyLecture lecture = new LectureBuilder(CS2040S_WEEK_1).build();
        expectedModel.updateFilteredVideoList(predicate, lecture);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), lecture.getName());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVideoList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        CodeContainsKeywordsPredicate predicate =
            (CodeContainsKeywordsPredicate) preparePredicate("CS2040S ST2334", Level.MODULE);
        FindCommand command = new FindCommand(Arrays.asList("CS2040S", "ST2334"));
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code CodeContainsKeywordsPredicate
     * or LectureNameContainsKeywordsPredicate or VideoNameContainsKeywordsPredicate}.
     */
    private Predicate<?> preparePredicate(String userInput, Level level) {
        List<String> keywords = Arrays.asList(userInput.split("\\s+"));
        if (level == Level.MODULE) {
            return new CodeContainsKeywordsPredicate(keywords);
        }
        if (level == Level.LECTURE) {
            return new LectureNameContainsKeywordsPredicate(keywords);
        }
        return new VideoNameContainsKeywordsPredicate(keywords);
    }
}
