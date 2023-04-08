 package tfifteenfour.clipboard.logic.commands;

 import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;

 import tfifteenfour.clipboard.logic.CurrentSelection;
 import tfifteenfour.clipboard.logic.PageType;
 import tfifteenfour.clipboard.model.Model;
 import tfifteenfour.clipboard.model.ModelManager;
 import tfifteenfour.clipboard.model.Roster;
 import tfifteenfour.clipboard.model.course.Course;
 import tfifteenfour.clipboard.model.course.Group;
 import tfifteenfour.clipboard.model.course.Session;
 import tfifteenfour.clipboard.model.student.Student;
 import tfifteenfour.clipboard.testutil.TypicalModel;

 public class ClearCommandTest {
     private Model model;
     private Model expectedModel;
     private Course selectedCourse;
     private Group selectedGroup;
     private Session selectedSession;
     private Student selectedStudent;
     private CurrentSelection actualSelection;

     @BeforeEach
     public void setUp() {
         this.model = new TypicalModel().getTypicalModel();
         selectedCourse = model.getCurrentSelection().getSelectedCourse();
         selectedGroup = model.getCurrentSelection().getSelectedGroup();
         selectedSession = model.getCurrentSelection().getSelectedSession();
         selectedStudent = model.getCurrentSelection().getSelectedStudent();

         actualSelection = this.model.getCurrentSelection();
         actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
     }

     @Test
     public void execute_emptyRoster_success() {
         Model model = new ModelManager();
         Model expectedModel = new ModelManager();

         assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
     }

     @Test
     public void execute_nonEmptyRoster_success() {
         expectedModel.setRoster(new Roster());
         String expectedMessage = (ClearCommand.MESSAGE_SUCCESS)

         assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
     }

 }
