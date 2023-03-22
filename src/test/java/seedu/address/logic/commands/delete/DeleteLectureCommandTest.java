package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_W1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.testutil.TypicalModules.CS2040S;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model and unit tests for {@code DeleteLectureCommand}
 */
public class DeleteLectureCommandTest {

    public static final String VALID_LECTURE_NAME_W1 = "Week 1";

    private Model model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());

    @Test
    public void execute_deleteLecture_success() throws CommandException {
        ModuleCode validModuleCode = new ModuleCode(VALID_MODULE_CODE_2040);
        LectureName validLectureName = new LectureName(VALID_LECTURE_NAME_W1);
        DeleteLectureCommand delete = new DeleteLectureCommand(validLectureName, validModuleCode);

        CommandResult actual = delete.execute(model);

        Model expectedModel = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
        ReadOnlyModule module = expectedModel.getModule(validModuleCode);
        ReadOnlyLecture lecture = module.getLecture(validLectureName);
        expectedModel.deleteLecture(module, lecture);

        // tests string output
        assertEquals(actual,
                new CommandResult(String.format(DeleteLectureCommand.MESSAGE_DELETE_LECTURE_SUCCESS,
                                                CS2040S.getCode())));;
        //tests model
        assertEquals(model.getTracker().getModuleList(),
                expectedModel.getTracker().getModuleList());
    }

    @Test
    public void execute_toDeleteDoesNotExist_throwsCommandException() {
        // module does not exist
        assertThrows(CommandException.class, () -> new DeleteLectureCommand(
                                                    new LectureName(VALID_LECTURE_NAME_W1),
                                                    new ModuleCode(VALID_MODULE_CODE_2103)
                                            ).execute(model));
        // lecture does not exist
        assertThrows(CommandException.class, () -> new DeleteLectureCommand(
                                                new LectureName(VALID_LECTURE_NAME_L1),
                                                new ModuleCode(VALID_MODULE_CODE_2040)
                                            ).execute(model));
    }

}
