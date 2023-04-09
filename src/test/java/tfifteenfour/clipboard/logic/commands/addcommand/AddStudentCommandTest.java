package tfifteenfour.clipboard.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.StudentBuilder;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class AddStudentCommandTest {
    public static final Student JOHN = new StudentBuilder()
            .withName("John")
            .withPhone("98765422")
            .withEmail("johnstub@example.com")
            .withStudentId("A3456789B")
            .build();
    public static final Student BOB = new StudentBuilder()
            .withName("Bob")
            .withPhone("98765433")
            .withEmail("bobstub@example.com")
            .withStudentId("A3459876C")
            .build();
    private Model model;
    private Model expectedModel;
    private Group selectedGroup;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() {
        AddStudentCommand addStudentCommand = new AddStudentCommand(JOHN);
        String expectedMessage = String.format(AddStudentCommand.MESSAGE_SUCCESS, selectedGroup, JOHN);

        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.addStudent(JOHN);

        assertCommandSuccess(addStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);

        AddStudentCommand addStudentCommand = new AddStudentCommand(JOHN);
        assertThrows(CommandException.class,
                AddStudentCommand.MESSAGE_WRONG_PAGE, () -> addStudentCommand.execute(model));
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student existingStudent = selectedStudent;
        Student duplicateStudent = new StudentBuilder(existingStudent).build();

        AddStudentCommand commandCopy = new AddStudentCommand(duplicateStudent);
        assertThrows(CommandException.class,
                AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, () -> commandCopy.execute(model));
    }

    @Test
    public void equals() {
        //same object
        AddStudentCommand command = new AddStudentCommand(JOHN);
        assertEquals(command, command);

        //null
        assertNotEquals(null, command);

        //different types
        assertNotEquals(1, command);

        //same values
        AddStudentCommand commandCopy = new AddStudentCommand(new StudentBuilder(JOHN).build());
        assertEquals(command, commandCopy);

        //different course
        AddStudentCommand commandDifferent = new AddStudentCommand(BOB);
        assertNotEquals(command, commandDifferent);
    }

}
