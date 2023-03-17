package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class AddLectureCommandTest {

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddLectureCommand(null, TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void constructor_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddLectureCommand(TypicalModules.CS2040S.getCode(), null));
    }

    @Test
    public void execute_lectureAcceptedByModel_addSuccessful() throws CommandException {
        Module module = TypicalModules.CS2040S;
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = TypicalLectures.ST2334_TOPIC_1;

        ModelStubAcceptingLectureAdded modelStub = new ModelStubAcceptingLectureAdded();
        CommandResult result = new AddLectureCommand(moduleCode, lecture).execute(modelStub);

        assertEquals(String.format(AddLectureCommand.MESSAGE_SUCCESS, moduleCode, lecture), result.getFeedbackToUser());
        assertEquals(Arrays.asList(module), modelStub.modulesAddedTo);
        assertEquals(Arrays.asList(lecture), modelStub.lecturesAdded);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddLectureCommand command =
                new AddLectureCommand(TypicalModules.CS2040S.getCode(), TypicalLectures.CS2040S_WEEK_1);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        ModuleCode moduleCode = TypicalModules.CS2040S.getCode();
        ModelStub modelStub = new ModelStubNoModule();
        AddLectureCommand command = new AddLectureCommand(moduleCode, TypicalLectures.CS2040S_WEEK_1);

        assertThrows(CommandException.class,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode), ()
                -> command.execute(modelStub));
    }

    @Test
    public void execute_duplicateLecture_throwsCommandException() {
        Module module = TypicalModules.CS2040S;
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = TypicalLectures.CS2040S_WEEK_1;

        AddLectureCommand command = new AddLectureCommand(moduleCode, lecture);

        ModelStub modelStub = new ModelStubWithLecture(module, lecture);

        assertThrows(CommandException.class,
                String.format(AddLectureCommand.MESSAGE_DUPLICATE_LECTURE, moduleCode), () ->
                command.execute(modelStub));
    }

    @Test
    public void equals() {
        ModuleCode moduleCode = TypicalModules.CS2040S.getCode();
        AddLectureCommand addCs2040sW1Command = new AddLectureCommand(moduleCode, TypicalLectures.CS2040S_WEEK_1);
        AddLectureCommand addCs2040sW2Command = new AddLectureCommand(moduleCode, TypicalLectures.CS2040S_WEEK_2);

        // same object -> returns true
        assertTrue(addCs2040sW1Command.equals(addCs2040sW1Command));

        // same values -> returns true
        AddLectureCommand addCs2040sW1CommandCopy = new AddLectureCommand(moduleCode, TypicalLectures.CS2040S_WEEK_1);
        assertTrue(addCs2040sW1Command.equals(addCs2040sW1CommandCopy));

        // different types -> returns false
        assertFalse(addCs2040sW1Command.equals(1));

        // null -> returns false
        assertFalse(addCs2040sW1Command.equals(null));

        // different lecture -> return false
        assertFalse(addCs2040sW1Command.equals(addCs2040sW2Command));
    }

    /**
     * A {@code Model} stub that always accepts the lecture being added.
     */
    private class ModelStubAcceptingLectureAdded extends ModelStub {
        private final ArrayList<ReadOnlyModule> modulesAddedTo = new ArrayList<>();
        private final ArrayList<Lecture> lecturesAdded = new ArrayList<>();

        @Override
        public ReadOnlyTracker getTracker() {
            return TypicalModules.getTypicalTracker();
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return true;
        }

        @Override
        public boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture) {
            return false;
        }

        @Override
        public void addLecture(ReadOnlyModule module, Lecture toAdd) {
            requireAllNonNull(module, toAdd);

            modulesAddedTo.add(module);
            lecturesAdded.add(toAdd);
        }
    }

    /**
     * A {@code Model} stub that contains no module.
     */
    private class ModelStubNoModule extends ModelStub {
        @Override
        public boolean hasModule(ModuleCode code) {
            return false;
        }
    }

    /**
     * A {@code Model} stub that contains a single lecture.
     */
    private class ModelStubWithLecture extends ModelStub {
        private final ReadOnlyModule module;
        private final ReadOnlyLecture lecture;

        public ModelStubWithLecture(ReadOnlyModule module, ReadOnlyLecture lecture) {
            this.module = module;
            this.lecture = lecture;
        }

        @Override
        public ReadOnlyTracker getTracker() {
            return TypicalModules.getTypicalTracker();
        }

        @Override
        public boolean hasModule(ModuleCode code) {
            return true;
        }

        @Override
        public boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture) {
            return this.module.equals(module) && this.lecture.equals(lecture);
        }
    }

}
