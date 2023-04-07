package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ExportProgressCommand}.
 */
class ExportProgressCommandTest {
    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExportProgressCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexDefaultDir_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ExportProgressCommand exportProgressCommand = new ExportProgressCommand(outOfBoundIndex, "");

        assertCommandFailure(exportProgressCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_validStudentIndexDefaultDir_success() throws Exception {
        Student studentToExport = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ExportProgressCommand exportProgressCommand = new ExportProgressCommand(INDEX_FIRST_STUDENT, "");

        String studentName = studentToExport.getName().fullName;
        String defaultDir = Paths.get("data").toAbsolutePath().toString();
        String defaultPath = Paths.get("data", studentName + "'s Progress Report.pdf").toAbsolutePath().toString();
        String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, defaultDir,
                studentName + "'s Progress Report.pdf");

        Path parentDir = Paths.get(defaultPath).getParent();
        if (parentDir != null) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                throw new CommandException(e.getMessage());
            }
        }

        Model expectedModel = new ModelManager(model.getMathutoring(), new UserPrefs());
        expectedModel.exportProgress(studentToExport, defaultPath);

        assertCommandSuccess(exportProgressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validStudentIndexValidCustomDir_success() throws IOException {
        Student studentToExport = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ExportProgressCommand exportProgressCommand = new ExportProgressCommand(INDEX_FIRST_STUDENT,
                System.getProperty("user.home"));

        String studentName = studentToExport.getName().fullName;
        String customDir = System.getProperty("user.home");
        String customPath = Paths.get(customDir, studentName + "'s Progress Report.pdf").toString();
        String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, customDir,
                studentName + "'s Progress Report.pdf");

        Model expectedModel = new ModelManager(model.getMathutoring(), new UserPrefs());
        expectedModel.exportProgress(studentToExport, customPath);

        assertCommandSuccess(exportProgressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ExportProgressCommand exportProgressFirstCommand = new ExportProgressCommand(INDEX_FIRST_STUDENT, "");
        ExportProgressCommand exportProgressSecondCommand = new ExportProgressCommand(INDEX_SECOND_STUDENT, "");

        // same object -> returns true
        assertTrue(exportProgressFirstCommand.equals(exportProgressFirstCommand));

        // same values -> returns true
        ExportProgressCommand exportProgressFirstCommandCopy = new ExportProgressCommand(INDEX_FIRST_STUDENT, "");
        assertTrue(exportProgressFirstCommand.equals(exportProgressFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportProgressFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportProgressFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(exportProgressFirstCommand.equals(exportProgressSecondCommand));
    }
}
