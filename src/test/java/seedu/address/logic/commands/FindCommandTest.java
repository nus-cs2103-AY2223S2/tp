package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.DisplayListLevel;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.LectureNameContainsKeywordsPredicate;
import seedu.address.model.module.LectureTagContainsKeywordsPredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
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
    private static final Lecture week5 = TypicalLectures.getCs2040sWeek5();
    private static final Lecture week6 = TypicalLectures.getCs2040sWeek6();
    private static final Lecture week7 = TypicalLectures.getCs2040sWeek7();
    private static final Video vid1 = TypicalVideos.CONTENT_VIDEO;
    private static final Video vid2 = TypicalVideos.ANALYSIS_VIDEO;

    private Model model = new ModelManager(getTypicalTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTracker(), new UserPrefs());

    @Test
    public void equals() {
        List<String> keywordsA = Arrays.asList("Apple", "Aries");
        List<String> keywordsB = Arrays.asList("Banana", "Base");

        FindCommand findFirstCommand = new FindCommand(keywordsA, false);
        FindCommand findSecondCommand = new FindCommand(keywordsB, false);
        FindCommand findThirdCommand = new FindCommand(keywordsA, true);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(keywordsA, false);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different modules -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // 1 has hasByTag, 1 does not  -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_zeroKeywords_noModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        boolean hasByTag = false;
        ModuleContainsKeywordsPredicate predicate =
                (ModuleContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.MODULE, hasByTag);
        FindCommand command = new FindCommand(Collections.emptyList(), hasByTag);
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_zeroKeywords_noModulesFoundByTag() {
        String expectedMessage = String.format(
                Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        boolean hasByTag = true;
        ModuleTagContainsKeywordsPredicate predicate =
                (ModuleTagContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.MODULE, hasByTag);
        FindCommand command = new FindCommand(Collections.emptyList(), hasByTag);
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_zeroKeywords_noLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 0);
        boolean hasByTag = false;
        LectureNameContainsKeywordsPredicate predicate =
                (LectureNameContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.LECTURE, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLectureList());
    }

    @Test
    public void execute_zeroKeywords_noLecturesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 0);
        boolean hasByTag = true;
        LectureTagContainsKeywordsPredicate predicate =
                (LectureTagContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.LECTURE, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLectureList());
    }

    @Test
    public void execute_zeroKeywords_noVideosFound() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 0);
        boolean hasByTag = false;
        VideoNameContainsKeywordsPredicate predicate =
                (VideoNameContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.VIDEO, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ReadOnlyLecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        expectedModel.updateFilteredVideoList(predicate, module.getCode(), lecture);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), lecture.getName(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVideoList());
    }

    @Test
    public void execute_zeroKeywords_noVideosFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 0);
        boolean hasByTag = true;
        VideoTagContainsKeywordsPredicate predicate =
                (VideoTagContainsKeywordsPredicate) preparePredicate(" ", DisplayListLevel.VIDEO, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ReadOnlyLecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        expectedModel.updateFilteredVideoList(predicate, module.getCode(), lecture);
        FindCommand command = new FindCommand(Collections.emptyList(), module.getCode(), lecture.getName(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVideoList());
    }

    @Test
    public void execute_multipleCodeKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        String input = "CS2, ST2";
        boolean hasByTag = false;
        ModuleContainsKeywordsPredicate predicate =
                (ModuleContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.MODULE, hasByTag);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input), hasByTag);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        String input = "data, probability";
        boolean hasByTag = false;
        ModuleContainsKeywordsPredicate predicate =
                (ModuleContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.MODULE, hasByTag);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input), hasByTag);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        String input = "heavy, math";
        boolean hasByTag = true;
        ModuleTagContainsKeywordsPredicate predicate =
                (ModuleTagContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.MODULE, hasByTag);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input), hasByTag);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2040S, ST2334), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 3);
        String input = "week 1, week 2, week 3";
        boolean hasByTag = false;
        LectureNameContainsKeywordsPredicate predicate =
                (LectureNameContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.LECTURE, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input), moduleCode, hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(week1, week2, week3), model.getFilteredLectureList());
    }

    @Test
    public void execute_singleKeyword_multipleLecturesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 7);
        String input = "week";
        boolean hasByTag = false;
        LectureNameContainsKeywordsPredicate predicate =
                (LectureNameContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.LECTURE, hasByTag);
        expectedModel.updateFilteredLectureList(predicate, CS2040S);
        FindCommand command =
                new FindCommand(
                StringUtil.commaDelimitedStringsToList(input), CS2040S.getCode(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(week1, week2, week3, week4, week5, week6, week7),
                model.getFilteredLectureList());
    }

    @Test
    public void execute_multipleKeywords_multipleLecturesFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, 4);
        String input = "intro, arrays, sorting";
        boolean hasByTag = true;
        LectureTagContainsKeywordsPredicate predicate =
                (LectureTagContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.LECTURE, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        expectedModel.updateFilteredLectureList(predicate, module);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input), moduleCode, hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(week1, week2, week3, week4), model.getFilteredLectureList());
    }

    @Test
    public void execute_multipleKeywords_multipleVideosFound() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 2);
        String input = "vid 1, vid 2";
        boolean hasByTag = false;
        VideoNameContainsKeywordsPredicate predicate =
                (VideoNameContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.VIDEO, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).build();
        expectedModel.updateFilteredVideoList(predicate, module.getCode(), lecture);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input),
                module.getCode(), lecture.getName(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(vid1, vid2), model.getFilteredVideoList());
    }

    @Test
    public void execute_multipleKeywords_singleVideoFoundByTag() {
        String expectedMessage = String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, 1);
        String input = "content, analysis";
        boolean hasByTag = true;
        VideoTagContainsKeywordsPredicate predicate =
                (VideoTagContainsKeywordsPredicate) preparePredicate(input, DisplayListLevel.VIDEO, hasByTag);
        ReadOnlyModule module = new ModuleBuilder(CS2040S).build();
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).build();
        expectedModel.updateFilteredVideoList(predicate, module.getCode(), lecture);
        FindCommand command = new FindCommand(StringUtil.commaDelimitedStringsToList(input),
                module.getCode(), lecture.getName(), hasByTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(vid2), model.getFilteredVideoList());
    }

    /**
     * Parses {@code userInput} into a {@code CodeContainsKeywordsPredicate
     * or LectureNameContainsKeywordsPredicate or VideoNameContainsKeywordsPredicate}.
     */
    private Predicate<?> preparePredicate(String userInput, DisplayListLevel level, boolean hasByTag) {
        String trimmedInput = userInput.trim();
        List<String> keywords = Arrays.asList(trimmedInput.split("\\s*,\\s*"));
        if (trimmedInput.isBlank()) {
            keywords = new ArrayList<>();
        }
        if (level == DisplayListLevel.MODULE) {
            return hasByTag
                        ? new ModuleTagContainsKeywordsPredicate(keywords)
                        : new ModuleContainsKeywordsPredicate(keywords);
        }
        if (level == DisplayListLevel.LECTURE) {
            return hasByTag
                        ? new LectureTagContainsKeywordsPredicate(keywords)
                        : new LectureNameContainsKeywordsPredicate(keywords);
        }
        return hasByTag
                    ? new VideoTagContainsKeywordsPredicate(keywords)
                    : new VideoNameContainsKeywordsPredicate(keywords);
    }
}
