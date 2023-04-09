// package tfifteenfour.clipboard.logic.parser;

// import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseFailure;
// import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseSuccess;

// import org.junit.jupiter.api.Test;

// import tfifteenfour.clipboard.logic.commands.ModuleCommand;
// import tfifteenfour.clipboard.model.student.StudentTakingModulePredicate;

// public class ModuleCommandParserTest {

//     private ModuleCommandParser parser = new ModuleCommandParser();

//     @Test
//     public void parse_emptyArg_throwsParseException() {
//         assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCommand.MESSAGE_USAGE));
//     }

//     @Test
//     public void parse_validArgs_returnsModuleCommand() {
//         // no leading and trailing whitespaces
//         ModuleCommand expectedModuleCommand =
//                 new ModuleCommand(new StudentTakingModulePredicate("CS2103T"));
//         assertParseSuccess(parser, "CS2103T", expectedModuleCommand);

//         // multiple whitespaces between keywords
//         assertParseSuccess(parser, " \n \t CS2103T \n", expectedModuleCommand);
//     }

// }
