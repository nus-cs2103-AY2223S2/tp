package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.LecturePredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.VideoPredicate;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    private ListCommand listCommand;

    private final Module existingModule = TypicalModules.getCs2040s();
    private final ModuleCode existingModuleCode = existingModule.getCode();
    private final Lecture existingLecture = TypicalLectures.getCs2040sWeek1();
    private final LectureName existingLectureName = existingLecture.getName();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getTracker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS_MODULES, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_moduleFoundShowLectures() {
        listCommand = new ListCommand(existingModuleCode);
        String expectedString = String.format(ListCommand.MESSAGE_SUCCESS_LECTURES, existingModuleCode);
        expectedModel.updateFilteredLectureList(new LecturePredicate(existingModule), existingModule);
        assertCommandSuccess(listCommand, model, expectedString, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_moduleLectureFoundShowVideos() {
        listCommand = new ListCommand(existingModuleCode, existingLectureName);
        String expectedString = String.format(
                ListCommand.MESSAGE_SUCCESS_VIDEOS, existingModuleCode, existingLectureName);
        expectedModel.updateFilteredVideoList(new VideoPredicate(existingLecture), existingModuleCode, existingLecture);
        assertCommandSuccess(listCommand, model, expectedString, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_moduleNotFoundThrowsCommandException() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        listCommand = new ListCommand(moduleCode);
        String expectedString = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);
        assertCommandFailure(listCommand, expectedModel, expectedString);
    }

    @Test
    public void execute_listIsFiltered_lectureNotFoundThrowsCommandException() {
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        String expectedString = String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, existingModuleCode);
        listCommand = new ListCommand(existingModuleCode, lectureName);
        assertCommandFailure(listCommand, expectedModel, expectedString);
    }
}
