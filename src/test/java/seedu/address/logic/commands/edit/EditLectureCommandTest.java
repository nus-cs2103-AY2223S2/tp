package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

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
import seedu.address.testutil.TypicalLectures;

/**
 * Contains integration tests (interactions with the Model) and unit tests for {@code EditLectureCommand}.
 */
public class EditLectureCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(null,
                        new LectureName(VALID_LECTURE_NAME_L1), EDIT_LECTURE_DESC_L2));
    }

    @Test
    public void constructor_nullLectureName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(new ModuleCode(VALID_MODULE_CODE_2103),
                        null, EDIT_LECTURE_DESC_L2));
    }

    @Test
    public void constructor_nullEditLectureDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditLectureCommand(new ModuleCode(VALID_MODULE_CODE_2103),
                        new LectureName(VALID_LECTURE_NAME_L1), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditLectureCommand command = new EditLectureCommand(new ModuleCode(VALID_MODULE_CODE_2103),
                new LectureName(VALID_LECTURE_NAME_L1), EDIT_LECTURE_DESC_L1);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        Module originalModule = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Module editedModule = new ModuleBuilder(originalModule).build();
        Lecture originalLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        Lecture editedLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).withVideos().build();
        originalLecture.getVideoList().stream().forEach(v -> editedLecture.addVideo((Video) v));

        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder(editedLecture).build();

        EditLectureCommand command = new EditLectureCommand(
                originalModule.getCode(), originalLecture.getName(), descriptor);

        originalModule.addLecture(originalLecture);
        model.addModule(originalModule);

        String expectedMessage = String.format(
                EditLectureCommand.MESSAGE_SUCCESS, originalModule.getCode(), editedLecture);

        Model expectedModel = new ModelManager();
        editedModule.addLecture(editedLecture);
        expectedModel.addModule(editedModule);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = EDIT_LECTURE_DESC_L1;

        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_lectureDoesNotExist_failure() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = EDIT_LECTURE_DESC_L1;

        EditLectureCommand command = new EditLectureCommand(module.getCode(), lectureName, descriptor);

        model.addModule(module);

        String expectedMessage = String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, module.getCode());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateLecture_failure() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture originalLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        Lecture editedLecture = new LectureBuilder(TypicalLectures.getCs2040sWeek2()).withVideos().build();
        originalLecture.getVideoList().stream().forEach(v -> editedLecture.addVideo((Video) v));

        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder(editedLecture).build();

        module.addLecture(originalLecture);
        module.addLecture(editedLecture);
        model.addModule(module);

        EditLectureCommand command = new EditLectureCommand(module.getCode(), originalLecture.getName(), descriptor);

        String expectedMessage = String.format(EditLectureCommand.MESSAGE_DUPLICATE_LECTURE, module.getCode());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, EDIT_LECTURE_DESC_L2);

        // same values -> returns true
        EditLectureCommand commandWithSameValues = new EditLectureCommand(
                moduleCode, lectureName, EDIT_LECTURE_DESC_L2);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(1));

        // different module code -> returns false
        assertFalse(command.equals(
                new EditLectureCommand(new ModuleCode(VALID_MODULE_CODE_2040), lectureName, EDIT_LECTURE_DESC_L2)));

        // different lecture name -> returns false
        assertFalse(command.equals(
                new EditLectureCommand(moduleCode, new LectureName(VALID_LECTURE_NAME_L2), EDIT_LECTURE_DESC_L2)));

        // different descriptor -> returns false
        assertFalse(command.equals(
                new EditLectureCommand(moduleCode, lectureName, EDIT_LECTURE_DESC_L1)));
    }
}
