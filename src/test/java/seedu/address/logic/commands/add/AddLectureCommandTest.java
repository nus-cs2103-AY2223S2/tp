package seedu.address.logic.commands.add;

import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.LectureEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the {@code Model}) and unit tests for {@code AddLectureCommand}.
 */
public class AddLectureCommandTest {

    private final Module module = TypicalModules.getCs2040s();
    private final Lecture lecture = TypicalLectures.getSt2334Topic1();

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLectureCommand(null, lecture));
    }

    @Test
    public void constructor_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddLectureCommand(module.getCode(), null));
    }

    @Test
    public void execute_lectureAcceptedByModel_success() throws CommandException {
        /* Setup */
        AddLectureCommand command = new AddLectureCommand(module.getCode(), lecture);
        model.addModule(module);

        /* Create expected results */
        // Create expected command result
        String expectedMessage = String.format(AddLectureCommand.MESSAGE_SUCCESS, module.getCode(), lecture);
        LectureEditInfo expectedEditInfo = new LectureEditInfo(module.getCode(), null, lecture);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        // Create expected model
        Model expectedModel = new ModelManager();
        Module editedModule = new ModuleBuilder(module).build();
        editedModule.addLecture(lecture);
        expectedModel.addModule(editedModule);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddLectureCommand command = new AddLectureCommand(module.getCode(), lecture);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        AddLectureCommand command = new AddLectureCommand(module.getCode(), lecture);
        assertThrows(CommandException.class,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, module.getCode()), () -> command.execute(model));
    }

    @Test
    public void execute_duplicateLecture_failure() {
        /* Setup */
        AddLectureCommand command = new AddLectureCommand(module.getCode(), lecture);
        module.addLecture(lecture);
        model.addModule(module);

        /* Create expected results */
        String expectedMessage = String.format(AddLectureCommand.MESSAGE_DUPLICATE_LECTURE, module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        AddLectureCommand command = new AddLectureCommand(module.getCode(), lecture);

        AddLectureCommand commandWithSameValue = new AddLectureCommand(module.getCode(), lecture);

        AddLectureCommand commandWithDiffModuleCode = new AddLectureCommand(
                TypicalModules.getSt2334().getCode(), lecture);
        AddLectureCommand commandWithDiffLecture = new AddLectureCommand(
                module.getCode(), TypicalLectures.getSt2334Topic2());

        ObjectUtil.testEquals(command, commandWithSameValue, 1,
                commandWithDiffModuleCode, commandWithDiffLecture);
    }

}
