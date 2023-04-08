// package tfifteenfour.clipboard.logic.commands;

// import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandFailure;
// import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
// import tfifteenfour.clipboard.model.Model;
// import tfifteenfour.clipboard.model.ModelManager;
// import tfifteenfour.clipboard.model.UserPrefs;
// import tfifteenfour.clipboard.model.student.Student;
// import tfifteenfour.clipboard.testutil.StudentBuilder;

// /**
//  * Contains integration tests (interaction with the Model) for {@code AddStudentCommand}.
//  */
// public class AddStudentCommandIntegrationTest {

//     private Model model;

//     @BeforeEach
//     public void setUp() {
//         model = new ModelManager(getTypicalRoster(), new UserPrefs());
//     }

//     @Test
//     public void execute_newStudent_success() {
//         Student validStudent = new StudentBuilder().build();

//         Model expectedModel = new ModelManager(model.getRoster(), new UserPrefs());
//         expectedModel.addStudent(validStudent);

//         assertCommandSuccess(new AddStudentCommand(validStudent), model,
//                 String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
//     }

//     @Test
//     public void execute_duplicateStudent_throwsCommandException() {
//         Student studentInList = model.getRoster().getUnmodifiableStudentList().get(0);
//         assertCommandFailure(new AddStudentCommand(studentInList), model,
//              AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);
//     }

// }
