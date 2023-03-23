package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalTracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Flag;
import seedu.address.model.Level;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.LectureNameContainsKeywordsPredicate;
import seedu.address.model.module.LectureTagContainsKeywordsPredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.ModuleTagContainsKeywordsPredicate;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.VideoNameContainsKeywordsPredicate;
import seedu.address.model.module.VideoTagContainsKeywordsPredicate;
import seedu.address.model.video.Video;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private static final Module CS2040S = TypicalModules.getCs2040s();
    private static final Module ST2334 = TypicalModules.getSt2334();
    private static final Lecture week1 = TypicalLectures.getCs2040sWeek1();
    private static final Lecture week2 = TypicalLectures.getCs2040sWeek2();
    private static final Lecture week3 = TypicalLectures.getCs2040sWeek3();
    private static final Lecture week4 = TypicalLectures.getCs2040sWeek4();
    private static final Video vid1 = TypicalVideos.CONTENT_VIDEO;
    private static final Video vid2 = TypicalVideos.ANALYSIS_VIDEO;
    private static final Flag flagTag = new Flag("-t");

    private Model model = new ModelManager(getTypicalTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTracker(), new UserPrefs());

    @Test
    public void equals() {
        List<String> keywordsA = Arrays.asList("Apple", "Aries");
        List<String> keywordsB = Arrays.asList("Banana", "Base");

        FindCommand findFirstCommand = new FindCommand(keywordsA, null);
        FindCommand findSecondCommand = new FindCommand(keywordsB, null);
        FindCommand findThirdCommand = new FindCommand(keywordsA, flagTag);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(keywordsA, null);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different modules -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // 1 has flagTag, 1 does not  -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_zeroKeywords_noModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        ModuleCodeContainsKeywordsPredicate predicate =
            (ModuleCodeContainsKeywordsPredicate) preparePredicate(" ", Level.MODULE, null);
        FindCommand command = new FindCommand(Collections.emptyList(), null);
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_zeroKeywords_noModulesFoundByTag() {
        String expectedMessage = String.format(
            Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        ModuleTagContainsKeywordsPredicate predicate =
            (ModuleTagContainsKeywordsPredicate) preparePredicate(" ", Level.MODULE, flagTag);
        FindCommand command = new FindCommand(Collections.emptyList(), flagTag);
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_zeroKeywords_noLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 0);
        LectureNameContainsKeywordsPredicate predicate =
            (LectureNameContainsKeywordsPredicate) preparePredicate(" ", Level.LECTURE, null);
        ReadOnlyModule module = new ModuleBuilder().build();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), null);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLectureList());
    }

    @Test
    public void execute_zeroKeywords_noLecturesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 0);
        LectureTagContainsKeywordsPredicate predicate =
            (LectureTagContainsKeywordsPredicate) preparePredicate(" ", Level.LECTURE, flagTag);
        ReadOnlyModule module = new ModuleBuilder().build();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), flagTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLectureList());
    }

    @Test
    public void execute_zeroKeywords_noVideosFound() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 0);
        VideoNameContainsKeywordsPredicate predicate =
            (VideoNameContainsKeywordsPredicate) preparePredicate(" ", Level.VIDEO, null);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ReadOnlyLecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        expectedModel.updateFilteredVideoList(predicate, lecture);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), lecture.getName(), null);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVideoList());
    }

    @Test
    public void execute_zeroKeywords_noVideosFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 0);
        VideoTagContainsKeywordsPredicate predicate =
            (VideoTagContainsKeywordsPredicate) preparePredicate(" ", Level.VIDEO, flagTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ReadOnlyLecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        expectedModel.updateFilteredVideoList(predicate, lecture);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), lecture.getName(), flagTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVideoList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        String input = "CS2040S ST2334";
        ModuleCodeContainsKeywordsPredicate predicate =
            (ModuleCodeContainsKeywordsPredicate) preparePredicate(input, Level.MODULE, null);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input), null);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        String input = "heavy math";
        ModuleTagContainsKeywordsPredicate predicate =
            (ModuleTagContainsKeywordsPredicate) preparePredicate(input, Level.MODULE, flagTag);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input), flagTag);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 3);
        String input = "week1 week2 week3";
        LectureNameContainsKeywordsPredicate predicate =
            (LectureNameContainsKeywordsPredicate) preparePredicate(input, Level.LECTURE, null);
        ReadOnlyModule module = new ModuleBuilder().build();
        ModuleCode moduleCode = module.getCode();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input), moduleCode, null);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(week1, week2, week3), model.getFilteredLectureList());
    }

    @Test
    public void execute_multipleKeywords_multipleLecturesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 4);
        String input = "intro arrays sorting";
        LectureTagContainsKeywordsPredicate predicate =
            (LectureTagContainsKeywordsPredicate) preparePredicate(input, Level.LECTURE, flagTag);
        ReadOnlyModule module = new ModuleBuilder().build();
        ModuleCode moduleCode = module.getCode();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input), moduleCode, flagTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(week1, week2, week3, week4), model.getFilteredLectureList());
    }

    @Test
    public void execute_multipleKeywords_multipleVideosFound() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 2);
        String input = "vid1 vid2";
        VideoNameContainsKeywordsPredicate predicate =
            (VideoNameContainsKeywordsPredicate) preparePredicate(input, Level.VIDEO, null);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).build();
        expectedModel.updateFilteredVideoList(predicate, lecture);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input),
            module.getCode(), lecture.getName(), null);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(vid1, vid2), model.getFilteredVideoList());
    }

    @Test
    public void execute_multipleKeywords_multipleVideosFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 2);
        String input = "content analysis";
        VideoTagContainsKeywordsPredicate predicate =
            (VideoTagContainsKeywordsPredicate) preparePredicate(input, Level.VIDEO, flagTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).build();
        expectedModel.updateFilteredVideoList(predicate, lecture);
        FindCommand command = new FindCommand(StringUtil.spaceDelimetedStringsToList(input),
            module.getCode(), lecture.getName(), flagTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(vid1, vid2), model.getFilteredVideoList());
    }

    /**
     * Parses {@code userInput} into a {@code CodeContainsKeywordsPredicate
     * or LectureNameContainsKeywordsPredicate or VideoNameContainsKeywordsPredicate}.
     */
    private Predicate<?> preparePredicate(String userInput, Level level, Flag flagTag) {
        List<String> keywords = Arrays.asList(userInput.split("\\s+"));
        if (level == Level.MODULE) {
            return flagTag == null
                ? new ModuleCodeContainsKeywordsPredicate(keywords)
                : new ModuleTagContainsKeywordsPredicate(keywords);
        }
        if (level == Level.LECTURE) {
            return flagTag == null
                ? new LectureNameContainsKeywordsPredicate(keywords)
                : new LectureTagContainsKeywordsPredicate(keywords);
        }
        return flagTag == null
            ? new VideoNameContainsKeywordsPredicate(keywords)
            : new VideoTagContainsKeywordsPredicate(keywords);
    }
}
