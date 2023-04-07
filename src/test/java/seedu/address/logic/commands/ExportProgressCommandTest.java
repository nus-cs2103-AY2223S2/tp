package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockConstruction;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalMockStudents.getTypicalMockStudents;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMathutoring;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains unit tests for
 * {@code ExportProgressCommand}.
 */
class ExportProgressCommandTest {
    private List<Student> typicalStudentList = getTypicalMockStudents();
    private ModelStubWithStudents modelStub = new ModelStubWithStudents(getTypicalMockStudents());

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExportProgressCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexDefaultDir_failure() {
        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index outOfBoundIndex = Index.fromZeroBased(typicalStudentList.size());
            doReturn(typicalStudentList.size()).when(outOfBoundIndex).getZeroBased();
            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(outOfBoundIndex, "");
            assertThrows(CommandException.class,
                    Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () -> exportProgressCommand.execute(modelStub));
        }

        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index outOfBoundIndex = Index.fromOneBased(typicalStudentList.size() + 1);
            doReturn(typicalStudentList.size()).when(outOfBoundIndex).getZeroBased();
            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(outOfBoundIndex, "");
            assertThrows(CommandException.class,
                    Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () -> exportProgressCommand.execute(modelStub));
        }
    }

    @Test
    void execute_validStudentIndexDefaultDir_success() throws Exception {
        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index validIndex = Index.fromZeroBased(0);
            doReturn(0).when(validIndex).getZeroBased();

            String studentName = typicalStudentList.get(0).getName().fullName;
            String defaultDir = Paths.get("").toAbsolutePath().toString();

            String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, defaultDir,
                    studentName + "'s Progress Report.pdf");

            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(validIndex, "");
            CommandResult commandResult = exportProgressCommand.execute(modelStub);
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        }

        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index validIndex = Index.fromOneBased(typicalStudentList.size());
            doReturn(typicalStudentList.size() - 1).when(validIndex).getZeroBased();

            String studentName = typicalStudentList.get(typicalStudentList.size() - 1).getName().fullName;
            String defaultDir = Paths.get("").toAbsolutePath().toString();

            String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, defaultDir,
                    studentName + "'s Progress Report.pdf");

            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(validIndex, "");
            CommandResult commandResult = exportProgressCommand.execute(modelStub);
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        }
    }

    @Test
    void execute_validStudentIndexValidCustomDir_success() throws Exception {
        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index validIndex = Index.fromZeroBased(0);
            doReturn(0).when(validIndex).getZeroBased();

            String studentName = typicalStudentList.get(0).getName().fullName;
            String customDir = Paths.get(System.getProperty("user.home")).toAbsolutePath().toString();

            String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, customDir,
                    studentName + "'s Progress Report.pdf");

            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(validIndex, customDir);
            CommandResult commandResult = exportProgressCommand.execute(modelStub);
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        }

        try (MockedConstruction<Index> mock = mockConstruction(Index.class)) {
            Index validIndex = Index.fromOneBased(typicalStudentList.size());
            doReturn(typicalStudentList.size() - 1).when(validIndex).getZeroBased();

            String studentName = typicalStudentList.get(typicalStudentList.size() - 1).getName().fullName;
            String customDir = Paths.get(System.getProperty("user.home")).toAbsolutePath().toString();

            String expectedMessage = String.format(ExportProgressCommand.MESSAGE_SUCCESS, studentName, customDir,
                    studentName + "'s Progress Report.pdf");

            ExportProgressCommand exportProgressCommand = new ExportProgressCommand(validIndex, customDir);
            CommandResult commandResult = exportProgressCommand.execute(modelStub);
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        }
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

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMathutoringFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMathutoringFilePath(Path mathutoringFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMathutoring(ReadOnlyMathutoring newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMathutoring getMathutoring() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student findSelectedStudent() {
            return null;
        }

        @Override
        public void exportProgress(Student target, String completePath) throws IOException {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains students.
     */
    private class ModelStubWithStudents extends ModelStub {
        private final ObservableList<Student> studentList;

        ModelStubWithStudents(List<Student> studentList) {
            requireNonNull(studentList);
            this.studentList = FXCollections.observableArrayList(studentList);
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return studentList;
        }

        @Override
        public void exportProgress(Student target, String completePath) throws IOException {
            requireAllNonNull(target, completePath);
            PDDocument document = new PDDocument();
            document.save(completePath);
            document.close();
        }
    }
}
