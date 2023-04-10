package tfifteenfour.clipboard.logic.commands.sortcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators.AlphaNumericSidComparator;
import tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators.AlphabeticalNameComparator;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

class SortCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();

        actualSelection = this.model.getCurrentSelection();
    }

    @Test
    public void execute_sortByName_success() throws CommandException {
        // Setup
        Comparator<Student> nameComparator = new AlphabeticalNameComparator();
        String categoryName = "name";
        Command sortByNameCommand = new SortCommand(nameComparator, categoryName);

        // Execution
        CommandResult commandResult = sortByNameCommand.execute(model);

        // Verification
        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS, categoryName), commandResult.getFeedbackToUser());
        List<Student> expectedList = new ArrayList<>(selectedGroup.getUnmodifiableFilteredStudentList());
        expectedList.sort(nameComparator);
        assertEquals(expectedList, selectedGroup.getUnmodifiableFilteredStudentList());
        assertEquals(PageType.SESSION_STUDENT_PAGE, actualSelection.getCurrentPage());
        assertEquals(selectedCourse, actualSelection.getSelectedCourse());
        assertEquals(selectedGroup, actualSelection.getSelectedGroup());
        assertEquals(selectedSession, actualSelection.getSelectedSession());
    }

    @Test
    public void execute_sortById_success() throws CommandException {
        // Setup
        Comparator<Student> idComparator = new AlphaNumericSidComparator();
        String categoryName = "id";
        Command sortByIdCommand = new SortCommand(idComparator, categoryName);

        // Execution
        CommandResult commandResult = sortByIdCommand.execute(model);

        // Verification
        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS, categoryName), commandResult.getFeedbackToUser());
        List<Student> expectedList = new ArrayList<>(selectedGroup.getUnmodifiableFilteredStudentList());
        expectedList.sort(idComparator);
        assertEquals(expectedList, selectedGroup.getUnmodifiableFilteredStudentList());
        assertEquals(PageType.SESSION_STUDENT_PAGE, actualSelection.getCurrentPage());
        assertEquals(selectedCourse, actualSelection.getSelectedCourse());
        assertEquals(selectedGroup, actualSelection.getSelectedGroup());
        assertEquals(selectedSession, actualSelection.getSelectedSession());
    }

    @Test
    public void equals() {
        Comparator<Student> nameComparator = new AlphabeticalNameComparator();
        String categoryName = "name";
        Command sortByNameCommand = new SortCommand(nameComparator, categoryName);
        Command sortByNameCommandCopy = new SortCommand(nameComparator, categoryName);
        Comparator<Student> idComparator = new AlphaNumericSidComparator();
        String categoryId = "id";
        Command sortByIdCommand = new SortCommand(idComparator, categoryId);

        // test same object
        assertEquals(sortByNameCommand, sortByNameCommand);

        // test equal objects
        assertEquals(sortByNameCommand, sortByNameCommandCopy);

        // test different objects
        assertNotEquals(sortByNameCommand, sortByIdCommand);
    }
}

