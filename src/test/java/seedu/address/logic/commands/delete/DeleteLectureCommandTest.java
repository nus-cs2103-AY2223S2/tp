package seedu.address.logic.commands.delete;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lecture.LectureName;
// import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
// import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model and unit tests for {@code DeleteLectureCommand}
 */
public class DeleteLectureCommandTest {

    // @Test
    // public void execute_deleteLecture_success() throws CommandException {
    //     ReadOnlyModule module = TypicalModules.getCs2040s();
    //     ReadOnlyLecture lecture = TypicalLectures.getCs2040sWeek1();

    //     ModelStubCs2040sAcceptingLectureDeleted model = new ModelStubCs2040sAcceptingLectureDeleted();

    //     DeleteLectureCommand delete = new DeleteLectureCommand(
    //             lecture.getName(),
    //             module.getCode());

    //     CommandResult actual = delete.execute(model);

    //     ModelStubCs2040sAcceptingLectureDeleted expectedModel = new ModelStubCs2040sAcceptingLectureDeleted();


    //     expectedModel.deleteLecture(module, lecture);

    //     // tests string output
    //     assertEquals(actual,
    //             new CommandResult(String.format(DeleteLectureCommand.MESSAGE_DELETE_LECTURE_SUCCESS,
    //                     module.getCode())));;
    //     //tests model
    //     assertEquals(model, expectedModel);
    // }

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

    //     private class ModelStubCs2040sAcceptingLectureDeleted extends ModelStub {
    //         private ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
    //         private LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();

    //         @Override
    //         public boolean hasModule(ModuleCode moduleCode) {
    //             return true;
    //         }

    //         @Override
    //         public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
    //             if (module.getCode().equals(this.moduleCode) && target.getName().equals(this.lectureName)) {
    //                 this.lectureName = null;
    //             }
    //         }

    //         @Override
    //         public void deleteModule(ReadOnlyModule module) {
    //             if (module.getCode().equals(this.moduleCode)) {
    //                 this.moduleCode = null;
    //             }
    //         }

    //         @Override
    //         public boolean equals(Object other) {
    //             if (other instanceof ModelStubCs2040sAcceptingLectureDeleted) {
    //                 ModelStubCs2040sAcceptingLectureDeleted model = (ModelStubCs2040sAcceptingLectureDeleted) other;
    //                 return model.lectureName.equals(this.lectureName) && model.moduleCode.equals(this.moduleCode);
    //             } else {
    //                 return false;
    //             }
    //         }
    //     }
}
