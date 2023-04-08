package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.LectureEditInfo;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.testutil.EditLectureDescriptorBuilder;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalLectures;

/**
 * Contains integration tests (interactions with the Model) and unit tests for {@code EditLectureCommand}.
 */
public class EditLectureCommandTest {

    private final ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
    private final LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
    private final EditLectureDescriptor descriptor = CommandTestUtil.getEditLectureDescriptorL2();

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(null, lectureName, descriptor));
    }

    @Test
    public void constructor_nullLectureName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(moduleCode, null, descriptor));
    }

    @Test
    public void constructor_nullEditLectureDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(moduleCode, lectureName, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        /* Setup */
        // Setup modules and lectures
        Module originalModule = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Module editedModule = new ModuleBuilder(originalModule).build();
        Lecture originalLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        Lecture editedLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2())
                .withVideos(originalLecture.getVideoList().toArray(Video[]::new))
                .build();

        originalModule.addLecture(originalLecture);

        // Setup commad
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder(editedLecture).build();
        EditLectureCommand command = new EditLectureCommand(
                originalModule.getCode(), originalLecture.getName(), descriptor);

        // Setup model
        model.addModule(originalModule);

        /* Create expected results */
        // Create expected command result
        String expectedMessage = String.format(
                EditLectureCommand.MESSAGE_SUCCESS, originalModule.getCode(), editedLecture);
        LectureEditInfo expectedEditInfo = new LectureEditInfo(originalModule.getCode(),
                originalLecture, editedLecture);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        // Create expected model
        Model expectedModel = new ModelManager();
        editedModule.addLecture(editedLecture);
        expectedModel.addModule(editedModule);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        /* Setup */
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        /* Create expected results */
        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_lectureDoesNotExist_failure() {
        /* Setup */
        // Setup module
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();

        // Setup command
        EditLectureCommand command = new EditLectureCommand(module.getCode(), lectureName, descriptor);

        // Setup model
        model.addModule(module);

        /* Create expected results */
        String expectedMessage = String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateLecture_failure() {
        /* Setup */
        // Setup module and lectures
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture originalLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        Lecture editedLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2())
                .withVideos(originalLecture.getVideoList().toArray(Video[]::new))
                .build();

        module.addLecture(originalLecture);
        module.addLecture(editedLecture);

        // Setup command
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder(editedLecture).build();
        EditLectureCommand command = new EditLectureCommand(module.getCode(), originalLecture.getName(), descriptor);

        // Setup model
        model.addModule(module);

        /* Create expected result */
        String expectedMessage = String.format(EditLectureCommand.MESSAGE_DUPLICATE_LECTURE, module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        EditLectureCommand commandWithSameValues = new EditLectureCommand(moduleCode, lectureName, descriptor);

        EditLectureCommand commandWithDiffModuleCode = new EditLectureCommand(
                new ModuleCode(VALID_MODULE_CODE_2040), lectureName, descriptor);
        EditLectureCommand commandWithDiffLectureName = new EditLectureCommand(
                moduleCode, new LectureName(VALID_LECTURE_NAME_L2), descriptor);
        EditLectureCommand commandWithDiffDescriptor = new EditLectureCommand(
                moduleCode, lectureName, CommandTestUtil.getEditLectureDescriptorL1());

        ObjectUtil.testEquals(command, commandWithSameValues, 1,
                commandWithDiffModuleCode, commandWithDiffLectureName, commandWithDiffDescriptor);
    }
}
