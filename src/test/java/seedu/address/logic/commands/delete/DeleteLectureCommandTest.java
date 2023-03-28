package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model and unit tests for {@code DeleteLectureCommand}
 */
public class DeleteLectureCommandTest {

    @Test
    public void execute_deleteLecture_success() throws CommandException {
        ReadOnlyModule module = TypicalModules.getCs2040s();
        ReadOnlyLecture lecture = TypicalLectures.getCs2040sWeek1();

        ModelStubCs2040sAcceptingLectureDeleted model = new ModelStubCs2040sAcceptingLectureDeleted();

        DeleteLectureCommand delete = new DeleteLectureCommand(
                module.getCode(),
                lecture.getName());

        CommandResult actual = delete.execute(model);

        ModelStubCs2040sAcceptingLectureDeleted expectedModel = new ModelStubCs2040sAcceptingLectureDeleted();

        expectedModel.deleteLecture(module, lecture);

        // tests string output
        assertEquals(actual,
                new CommandResult(String.format(DeleteLectureCommand.MESSAGE_DELETE_LECTURE_SUCCESS,
                        lecture.getName(),
                        module.getCode())));;
        //tests model
        assertEquals(model, expectedModel);
    }

    @Test
    public void execute_toDeleteDoesNotExist_throwsCommandException() {
        // module does not exist
        assertThrows(CommandException.class, () -> new DeleteLectureCommand(
                TypicalModules.getCs2107().getCode(),
                TypicalLectures.getCs2107Lecture1().getName()
                                            ).execute(new ModelStubNoModule()));
        // lecture does not exist in module
        assertThrows(CommandException.class, () -> new DeleteLectureCommand(
            TypicalModules.getCs2040s().getCode(),
            TypicalLectures.getCs2040sWeek7().getName()
                                            ).execute(new ModelStubNoLecture()));
    }

    @Test
    public void equals() {
        ModuleCode modCs2040s = TypicalModules.getCs2040s().getCode();
        ModuleCode modSt2334 = TypicalModules.getSt2334().getCode();

        LectureName lecCs2040sWeek1 = TypicalLectures.getCs2040sWeek1().getName();
        LectureName lecCs2040sWeek2 = TypicalLectures.getCs2040sWeek2().getName();
        LectureName lecSt2334Topic1 = TypicalLectures.getSt2334Topic1().getName();

        DeleteLectureCommand deleteCs2040sWeek1 = new DeleteLectureCommand(modCs2040s, lecCs2040sWeek1);
        DeleteLectureCommand deleteCs2040sWeek2 = new DeleteLectureCommand(modCs2040s, lecCs2040sWeek2);
        DeleteLectureCommand deleteSt2334Topic1 = new DeleteLectureCommand(modSt2334, lecSt2334Topic1);

        // same object -> returns true
        assertTrue(deleteCs2040sWeek1.equals(deleteCs2040sWeek1));

        // same value -> returns true
        DeleteLectureCommand deleteCs2040sWeek2Dup = new DeleteLectureCommand(modCs2040s, lecCs2040sWeek2);
        assertTrue(deleteCs2040sWeek2.equals(deleteCs2040sWeek2Dup));

        // different values -> returns false
        assertFalse(deleteCs2040sWeek1.equals(deleteCs2040sWeek2)); // different lecture
        assertFalse(deleteCs2040sWeek1.equals(deleteSt2334Topic1)); // different mod

        // different typies -> returns false
        assertFalse(deleteCs2040sWeek1.equals("command"));

        // null -> returns false
        assertFalse(deleteSt2334Topic1.equals(null));
    }

    private class ModelStubNoLecture extends ModelStub {
        @Override
        public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
            return false;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return true;
        }
    }

    private class ModelStubNoModule extends ModelStub {
        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return false;
        }
    }

    private class ModelStubCs2040sAcceptingLectureDeleted extends ModelStub {
        private ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        private LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return this.moduleCode.equals(moduleCode);
        }

        @Override
        public ReadOnlyModule getModule(ModuleCode moduleCode) {
            return new Module(moduleCode, new ModuleName(" "), Set.of(), List.of());
        }

        @Override
        public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
            return this.moduleCode.equals(moduleCode) && this.lectureName.equals(lectureName);
        }

        @Override
        public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lecturename) {
            return new Lecture(lecturename, Set.of(), List.of());
        }

        @Override
        public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
            if (module.getCode().equals(this.moduleCode) && target.getName().equals(this.lectureName)) {
                this.lectureName = null;
            }
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof ModelStubCs2040sAcceptingLectureDeleted) {
                ModelStubCs2040sAcceptingLectureDeleted model = (ModelStubCs2040sAcceptingLectureDeleted) other;
                if (this.lectureName == null) {
                    if (this.moduleCode == null) {
                        return model.moduleCode == null && model.lectureName == null;
                    }
                    return this.moduleCode.equals(model.moduleCode) && model.lectureName == null;
                }
                return this.moduleCode.equals(model.moduleCode) && this.lectureName.equals(model.lectureName);
            } else {
                return false;
            }
        }
    }
}
