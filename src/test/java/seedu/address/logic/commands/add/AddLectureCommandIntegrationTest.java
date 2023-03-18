package seedu.address.logic.commands.add;

import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;


/**
 * Contains integration tests {interactions with the Model} for {@code AddLectureCommand}.
 */
public class AddLectureCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
    }

    @Test
    public void execute_newLecture_success() {
        Module module = new ModuleBuilder(TypicalModules.CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        Lecture validLecture = TypicalLectures.ST2334_TOPIC_1;

        // Prevents the original module from TypicalModules from being modified
        model.deleteModule(module);
        model.addModule(module);

        Model expectedModel = new ModelManager(model.getTracker(), model.getUserPrefs());

        // Prevents the module in model from being modified
        Module moduleCopy = new ModuleBuilder(module).build();
        expectedModel.deleteModule(module);
        expectedModel.addModule(moduleCopy);
        expectedModel.addLecture(moduleCopy, validLecture);

        assertCommandSuccess(new AddLectureCommand(moduleCode, validLecture), model,
                String.format(AddLectureCommand.MESSAGE_SUCCESS, moduleCode, validLecture), model);
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        Module module = new ModuleBuilder(TypicalModules.CS2107).build();
        ModuleCode moduleCode = module.getCode();
        Lecture validLecture = TypicalLectures.CS2040S_WEEK_1;

        assertCommandFailure(new AddLectureCommand(moduleCode, validLecture), model,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
    }

    @Test
    public void execute_duplicateLecture_throwsCommandException() {
        Module module = new ModuleBuilder(TypicalModules.CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        Lecture lectureInList = TypicalLectures.CS2040S_WEEK_1;

        assertCommandFailure(new AddLectureCommand(moduleCode, lectureInList), model,
                String.format(AddLectureCommand.MESSAGE_DUPLICATE_LECTURE, moduleCode));
    }

}
