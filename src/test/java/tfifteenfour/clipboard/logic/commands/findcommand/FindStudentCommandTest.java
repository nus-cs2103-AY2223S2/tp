package tfifteenfour.clipboard.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.predicates.CourseNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.StudentParticularsContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

class FindStudentCommandTest {
    private static Student firstStudent;
    private static Student secondStudent;
    private Model model;
    private Model expectedModel;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;
    private CourseNameContainsPredicate predicate;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);

        firstStudent = selectedGroup.getUnmodifiableStudentList().get(INDEX_FIRST.getZeroBased());
        secondStudent = selectedGroup.getUnmodifiableStudentList().get(INDEX_SECOND.getZeroBased());
    }


    @Test
    public void execute_nameMatchesOneStudent_success() {
        String searchTerm = String.valueOf(firstStudent.getName());
        StudentParticularsContainsPredicate predicate =
                new StudentParticularsContainsPredicate(new String[]{searchTerm});
        FindStudentCommand findStudentCommand = new FindStudentCommand(predicate, actualSelection);

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredStudents(predicate);
        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getUnmodifiableFilteredStudentList()
                .filtered(predicate).size();

        assertCommandSuccess(findStudentCommand, model, String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize),
                expectedModel);
    }

    @Test
    public void execute_idMatchesOneStudent_success() {
        String searchTerm = String.valueOf(firstStudent.getStudentId());
        StudentParticularsContainsPredicate predicate =
                new StudentParticularsContainsPredicate(new String[]{searchTerm});
        FindStudentCommand findStudentCommand = new FindStudentCommand(predicate, actualSelection);

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredStudents(predicate);
        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getUnmodifiableFilteredStudentList()
                .filtered(predicate).size();

        assertCommandSuccess(findStudentCommand, model,
                String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void execute_noMatchesFound_success() {
        String searchTerm = "RandomSearchTermThatWontMatchAnyStudent";
        StudentParticularsContainsPredicate predicate =
                new StudentParticularsContainsPredicate(new String[]{searchTerm});
        FindStudentCommand findStudentCommand = new FindStudentCommand(predicate, actualSelection);
        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getUnmodifiableFilteredStudentList()
                .filtered(predicate).size();

        assertCommandSuccess(findStudentCommand, model,
                String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void execute_nameMatchesMultipleStudents_success() {
        String searchTerm = "A";
        StudentParticularsContainsPredicate predicate =
                new StudentParticularsContainsPredicate(new String[]{searchTerm});
        FindStudentCommand findStudentCommand = new FindStudentCommand(predicate, actualSelection);
        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getUnmodifiableFilteredStudentList()
                .filtered(predicate).size();

        assertCommandSuccess(findStudentCommand, model,
                String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void equals() {
        String searchTerm1 = String.valueOf(firstStudent.getName());
        String searchTerm2 = String.valueOf(secondStudent.getName());

        StudentParticularsContainsPredicate predicate1 =
                new StudentParticularsContainsPredicate(new String[]{searchTerm1, searchTerm1});
        FindStudentCommand findStudentCommand1 = new FindStudentCommand(predicate1, actualSelection);

        StudentParticularsContainsPredicate predicate2 =
                new StudentParticularsContainsPredicate(new String[]{searchTerm2, searchTerm2});
        FindStudentCommand findStudentCommand2 = new FindStudentCommand(predicate2, actualSelection);

        // same object -> returns true
        assertEquals(findStudentCommand1, findStudentCommand1);

        // same values -> returns true
        FindStudentCommand findStudentCommand1Copy = new FindStudentCommand(predicate1, actualSelection);
        assertEquals(findStudentCommand1, findStudentCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, findStudentCommand1);

        // null -> returns false
        assertNotEquals(null, findStudentCommand1);

        // different search term -> returns false
        assertNotEquals(findStudentCommand1, findStudentCommand2);
    }
}
