//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
//
//import java.util.Arrays;
//import java.util.function.Predicate;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.student.Homework;
//import seedu.address.model.student.Name;
//import seedu.address.model.student.Student;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for ViewHomeworkCommand.
// */
//public class ViewHomeworkCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_multipleKeywords_multipleHomeworkFound() {
//        String expectedMessage = "Viewing homework for 3 students";
//        Name firstStudentName = new Name("Alex");
//        Name secondStudentName = new Name("Bernice");
//        Name thirdStudentName = new Name("Charlotte");
//
//        Predicate<Student> namePredicate = (student) -> student.getName().equals(firstStudentName)
//                || student.getName().equals(secondStudentName) || student.getName().equals(thirdStudentName);
//
//        Predicate<Homework> homeworkStatusPredicate =
//                (homework) -> homework.getStatus().equals(Homework.Status.COMPLETED);
//
//        ViewHomeworkCommand command =
//                new ViewHomeworkCommand(namePredicate, homeworkStatusPredicate, false);
//
//        expectedModel.updateFilteredStudentList(namePredicate);
//        for (Student student : expectedModel.getFilteredStudentList()) {
//            student.updateFilteredHomeworkList(homeworkStatusPredicate);
//        }
//
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//    }
//}
