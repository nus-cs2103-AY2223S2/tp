 package tfifteenfour.clipboard.logic.commands;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertFalse;
 import static org.junit.jupiter.api.Assertions.assertNotEquals;
 import static org.junit.jupiter.api.Assertions.assertTrue;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;

 import tfifteenfour.clipboard.logic.CurrentSelection;
 import tfifteenfour.clipboard.logic.PageType;
 import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
 import tfifteenfour.clipboard.model.Model;
 import tfifteenfour.clipboard.model.student.Student;
 import tfifteenfour.clipboard.testutil.TypicalModel;

 public class CommandResultTest {
     private Model model;
     private Student selectedStudent;
     private CurrentSelection actualSelection;

     @BeforeEach
     public void setUp() {
         this.model = new TypicalModel().getTypicalModel();
         selectedStudent = model.getCurrentSelection().getSelectedStudent();

         actualSelection = this.model.getCurrentSelection();
         actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
     }

     @Test
     public void isStateModified_returnsTrue() {
         CommandResult commandResult = new CommandResult(new AddStudentCommand(selectedStudent),
                 AddStudentCommand.MESSAGE_SUCCESS, true);
         assertTrue(commandResult.isStateModified());
     }

     @Test
     public void isStateModified_returnsFalse() {
         CommandResult commandResult = new CommandResult(new AddStudentCommand(selectedStudent),
                 AddStudentCommand.MESSAGE_SUCCESS, false);
         assertFalse(commandResult.isStateModified());
     }

     @Test
     public void getCommand_returnsCommand() {
         Command mockCommand = new MockCommand(true);
         String mockFeedback = "Mock command executed";
         boolean mockStateModified = true;
         CommandResult commandResult = new CommandResult(mockCommand, mockFeedback, mockStateModified);
         assertEquals(mockCommand, commandResult.getCommand());
     }

     @Test
     public void equals() {
         CommandResult commandResult = new CommandResult(null, "feedback", false);

         // same values -> returns true
         assertTrue(commandResult.equals(new CommandResult(null, "feedback", false)));

         // same object -> returns true
         assertTrue(commandResult.equals(commandResult));

         // null -> returns false
         assertFalse(commandResult.equals(null));

         // different types -> returns false
         assertFalse(commandResult.equals(0.5f));

         // different feedbackToUser value -> returns false
         assertFalse(commandResult.equals(new CommandResult(null, "different", false)));

         // different hasChangedRosterState value -> returns false
         assertFalse(commandResult.equals(new CommandResult(null, "feedback", true)));
     }

     @Test
     public void hashcode() {
         CommandResult commandResult = new CommandResult(null, "feedback", false);

         // same values -> returns same hashcode
         assertEquals(commandResult.hashCode(), new CommandResult(null, "feedback", false).hashCode());

         // different feedbackToUser value -> returns different hashcode
         assertNotEquals(commandResult.hashCode(), new CommandResult(null, "different", false).hashCode());

         // different hasChangedRosterState value -> returns different hashcode
         assertNotEquals(commandResult.hashCode(), new CommandResult(null, "feedback", true).hashCode());
     }

     class MockCommand extends Command {
         public MockCommand(boolean willModifyState) {
             super(willModifyState);
         }

         @Override
         public CommandResult execute(Model model) {
             return new CommandResult(null, "Mock command executed", false);
         }
     }
 }
