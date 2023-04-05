package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalPowerConnectStudents;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Class;
import seedu.address.model.person.Comment;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;

class StudentCommentCommandTest {
    private Model model = new ModelManager(getTypicalPowerConnectStudents(), new UserPrefs());

    @Test
    public void execute_validIndexAndCommentUnfilteredList_success() {
        Student studentToComment = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert studentToComment != null : "Student to comment should not be null!";
        StudentCommentCommand studentCommentCommand = new StudentCommentCommand(Class.of("1A"),
               new IndexNumber("1"), new Comment("Good Student!"));
        String expectedMessage = String.format(StudentCommentCommand.MESSAGE_ADD_COMMENT_SUCCESS,
                studentToComment);

        ModelManager expectedModel = new ModelManager(model.getPcClass(), new UserPrefs());
        Student editedStudent = new Student(studentToComment.getName(), studentToComment.getStudentClass(),
                studentToComment.getIndexNumber(), studentToComment.getSex(), studentToComment.getParentName(),
                studentToComment.getParentNumber(), studentToComment.getRls(), studentToComment.getAge(),
                studentToComment.getImage(), studentToComment.getEmail(), studentToComment.getPhone(),
                studentToComment.getCca(), studentToComment.getAddress(), studentToComment.getAttendance(),
                studentToComment.getHomework(), studentToComment.getTest(), studentToComment.getTags(),
                studentToComment.getComment());
        List<Student> lastShownList =
                expectedModel.getPcClass().getClassList().get(0).getStudents().asUnmodifiableObservableList();
        for (int i = 0; i < expectedModel.getPcClass().getClassList().size(); i++) {
            if (expectedModel.getPcClass().getClassList().get(i).getClassName().equals(
                    studentToComment.getStudentClass().getClassName())) {
                lastShownList = expectedModel.getPcClass().getClassList().get(i).getStudents()
                        .asUnmodifiableObservableList();
                break;
            }
        }
        Student studentToEdit = null;

        for (int i = 0; i < lastShownList.size(); i++) {
            Student curr = lastShownList.get(i);
            if (curr.getIndexNumber().equals(studentToComment.getIndexNumber())
                    && curr.getStudentClass().equals(studentToComment.getStudentClass())) {
                studentToEdit = curr;
                break;
            }
        }
        model.setStudent(studentToEdit, editedStudent);
        assertEquals(model, expectedModel);
    }

}
