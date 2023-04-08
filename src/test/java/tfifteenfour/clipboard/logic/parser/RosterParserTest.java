// package tfifteenfour.clipboard.logic.parser;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
// import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
// import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.junit.jupiter.api.Test;

// import tfifteenfour.clipboard.logic.CurrentSelection;
// import tfifteenfour.clipboard.logic.commands.ClearCommand;
// import tfifteenfour.clipboard.logic.commands.ExitCommand;
// import tfifteenfour.clipboard.logic.commands.HelpCommand;
// import tfifteenfour.clipboard.logic.commands.ListCommand;
// import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
// import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCommand;
// import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteStudentCommand;
// import tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand;
// import tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand.EditStudentDescriptor;
// import tfifteenfour.clipboard.logic.commands.studentcommands.FindCommand;
// import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
// import tfifteenfour.clipboard.model.student.NameContainsKeywordsPredicate;
// import tfifteenfour.clipboard.model.student.Student;
// import tfifteenfour.clipboard.testutil.EditStudentDescriptorBuilder;
// import tfifteenfour.clipboard.testutil.StudentBuilder;
// import tfifteenfour.clipboard.testutil.StudentUtil;

// public class RosterParserTest {
//     private static final CurrentSelection TEST_CURRENT_SELECTION = new CurrentSelection();


//     @Test
//     public void parseCommand_add() throws Exception {
//         Student student = new StudentBuilder().build();
//         AddStudentCommand command = (AddStudentCommand) RosterParser.parseCommand(StudentUtil.getAddCommand(student),
//                 TEST_CURRENT_SELECTION);
//         assertEquals(new AddStudentCommand(student), command);
//     }

//     @Test
//     public void parseCommand_clear() throws Exception {
//         assertTrue(RosterParser.parseCommand(ClearCommand.COMMAND_WORD, TEST_CURRENT_SELECTION)
//                 instanceof ClearCommand);
//         assertTrue(RosterParser.parseCommand(ClearCommand.COMMAND_WORD + " 3",
//                 TEST_CURRENT_SELECTION) instanceof ClearCommand);
//     }

//     @Test
//     public void parseCommand_delete() throws Exception {
//         DeleteCommand command = (DeleteCommand) RosterParser.parseCommand(
//                 DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), TEST_CURRENT_SELECTION);
//         assertEquals(new DeleteStudentCommand(INDEX_FIRST_PERSON), command);
//     }

//     @Test
//     public void parseCommand_edit() throws Exception {
//         Student student = new StudentBuilder().build();
//         EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
//         EditCommand command = (EditCommand) RosterParser.parseCommand(EditCommand.COMMAND_WORD + " "
//                 + INDEX_FIRST_PERSON.getOneBased() + " "
//                 + StudentUtil.getEditStudentDescriptorDetails(descriptor), TEST_CURRENT_SELECTION);
//         assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
//     }

//     @Test
//     public void parseCommand_exit() throws Exception {
//         assertTrue(RosterParser.parseCommand(ExitCommand.COMMAND_WORD, TEST_CURRENT_SELECTION)
//                 instanceof ExitCommand);
//         assertTrue(RosterParser.parseCommand(ExitCommand.COMMAND_WORD + " 3", TEST_CURRENT_SELECTION)
//                 instanceof ExitCommand);
//     }

//     @Test
//     public void parseCommand_find() throws Exception {
//         List<String> keywords = Arrays.asList("foo", "bar", "baz");
//         FindCommand command = (FindCommand) RosterParser.parseCommand(
//                 FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
//                 TEST_CURRENT_SELECTION);
//         assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
//     }

//     @Test
//     public void parseCommand_help() throws Exception {
//         assertTrue(RosterParser.parseCommand(HelpCommand.COMMAND_WORD, TEST_CURRENT_SELECTION)
//                 instanceof HelpCommand);
//         assertTrue(RosterParser.parseCommand(HelpCommand.COMMAND_WORD + " 3", TEST_CURRENT_SELECTION)
//                 instanceof HelpCommand);
//     }

//     @Test
//     public void parseCommand_list() throws Exception {
//         assertTrue(RosterParser.parseCommand(ListCommand.COMMAND_WORD, TEST_CURRENT_SELECTION)
//                 instanceof ListCommand);
//         assertTrue(RosterParser.parseCommand(ListCommand.COMMAND_WORD + " 3", TEST_CURRENT_SELECTION)
//                 instanceof ListCommand);
//     }

//     @Test
//     public void parseCommand_unrecognisedInput_throwsParseException() {
//         assertThrows(ParseException.class,
//             String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
//             -> RosterParser.parseCommand("", TEST_CURRENT_SELECTION));
//     }

//     @Test
//     public void parseCommand_unknownCommand_throwsParseException() {
//         assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> RosterParser.parseCommand(
//                 "unknownCommand", TEST_CURRENT_SELECTION));
//     }
// }
