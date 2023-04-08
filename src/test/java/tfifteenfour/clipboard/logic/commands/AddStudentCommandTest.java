
// package tfifteenfour.clipboard.logic.commands;

// import static java.util.Objects.requireNonNull;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

// import java.nio.file.Path;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.function.Predicate;

// import org.junit.jupiter.api.Test;

// import javafx.collections.ObservableList;
// import tfifteenfour.clipboard.commons.core.GuiSettings;
// import tfifteenfour.clipboard.logic.CurrentSelection;
// import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
// import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
// import tfifteenfour.clipboard.model.Model;
// import tfifteenfour.clipboard.model.ReadOnlyRoster;
// import tfifteenfour.clipboard.model.ReadOnlyUserPrefs;
// import tfifteenfour.clipboard.model.Roster;
// import tfifteenfour.clipboard.model.course.Course;
// import tfifteenfour.clipboard.model.student.Student;
// import tfifteenfour.clipboard.testutil.StudentBuilder;

// public class AddStudentCommandTest {

//     public static final CurrentSelection TEST_CURRENT_SELECTION = new CurrentSelection();

//     @Test
//     public void constructor_nullStudent_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> new AddStudentCommand(null));
//     }

//     @Test
//     public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
//         ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
//         Student validStudent = new StudentBuilder().build();

//         CommandResult commandResult = new AddStudentCommand(validStudent).execute(modelStub, TEST_CURRENT_SELECTION);

//         assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent),
//                 commandResult.getFeedbackToUser());
//         assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);
//     }

//     @Test
//     public void execute_duplicateStudent_throwsCommandException() {
//         Student validStudent = new StudentBuilder().build();
//         AddStudentCommand addStudentCommand = new AddStudentCommand(validStudent);
//         ModelStub modelStub = new ModelStubWithStudent(validStudent);

//         assertThrows(CommandException.class,
//                 AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, () -> addStudentCommand.execute(modelStub,
//                         TEST_CURRENT_SELECTION));
//     }

//     @Test
//     public void equals() {
//         Student alice = new StudentBuilder().withName("Alice").build();
//         Student bob = new StudentBuilder().withName("Bob").build();
//         AddStudentCommand addAliceCommand = new AddStudentCommand(alice);
//         AddStudentCommand addBobCommand = new AddStudentCommand(bob);

//         // same object -> returns true
//         assertTrue(addAliceCommand.equals(addAliceCommand));

//         // same values -> returns true
//         AddStudentCommand addAliceCommandCopy = new AddStudentCommand(alice);
//         assertTrue(addAliceCommand.equals(addAliceCommandCopy));

//         // different types -> returns false
//         assertFalse(addAliceCommand.equals(1));

//         // null -> returns false
//         assertFalse(addAliceCommand.equals(null));

//         // different student -> returns false
//         assertFalse(addAliceCommand.equals(addBobCommand));
//     }

//     /**
//      * A default model stub that have all of the methods failing.
//      */
//     private class ModelStub implements Model {

//         @Override
//         public boolean hasCourse(Course course) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void addCourse(Course course) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ObservableList<Course> getModifiableFilteredCourseList() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ObservableList<Course> getUnmodifiableFilteredCourseList() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void deleteCourse(Course course) {
//             throw new AssertionError("This method should not be called.");
//         }


//         @Override
//         public Model copy() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setCommandExecuted(Command command) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public Command getCommandExecuted() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setCommandTextExecuted(String commandText) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public String getCommandTextExecuted() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ReadOnlyUserPrefs getUserPrefs() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public GuiSettings getGuiSettings() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setGuiSettings(GuiSettings guiSettings) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public Path getRosterFilePath() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setRosterFilePath(Path addressBookFilePath) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void addStudent(Student student) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setRoster(ReadOnlyRoster newData) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ReadOnlyRoster getRoster() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public boolean hasStudent(Student student) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void deleteStudent(Student target) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void setStudent(Student target, Student editedStudent) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ObservableList<Student> getUnmodifiableFilteredStudentList() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ObservableList<Student> getModifiableFilteredStudentList() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void updateFilteredStudentList(Predicate<Student> predicate) {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public ObservableList<Student> getViewedStudent() {
//             throw new AssertionError("This method should not be called.");
//         }

//         @Override
//         public void updateViewedStudent(Predicate<Student> predicate) {
//             throw new AssertionError("This method should not be called");
//         }
//     }

//     /**
//      * A Model stub that contains a single student.
//      */
//     private class ModelStubWithStudent extends ModelStub {
//         private final Student student;

//         ModelStubWithStudent(Student student) {
//             requireNonNull(student);
//             this.student = student;
//         }

//         @Override
//         public boolean hasStudent(Student student) {
//             requireNonNull(student);
//             return this.student.isSameStudent(student);
//         }
//     }

//     /**
//      * A Model stub that always accept the student being added.
//      */
//     private class ModelStubAcceptingStudentAdded extends ModelStub {
//         final ArrayList<Student> studentsAdded = new ArrayList<>();

//         @Override
//         public boolean hasStudent(Student student) {
//             requireNonNull(student);
//             return studentsAdded.stream().anyMatch(student::isSameStudent);
//         }

//         @Override
//         public void addStudent(Student student) {
//             requireNonNull(student);
//             studentsAdded.add(student);
//         }

//         @Override
//         public ReadOnlyRoster getRoster() {
//             return new Roster();
//         }
//     }

// }
