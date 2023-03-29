package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.model.util.NonEmptyString;


public class DeleteProjectCommandParserTest {
    // This command is simple, as it should look like this:
    //   dp -pn PROJECT_NAME
    // So our tests are simple as well!

    private DeleteProjectCommandParser parser = new DeleteProjectCommandParser();

    @Test
    public void parse_missingRequiredArgs_fails() {
        Map<String, String> tests = Map.ofEntries(
            Map.entry("empty string", ""),
            Map.entry("only whitespace", " "),
            Map.entry("no project name", "-pn")
        );
        String expectedErr = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE);
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser, input, expectedErr, "While testing case: " + desc);
        });
    }

    @Test
    public void parse_wrongArgs_fails() {
        Map<String, String> tests = Map.ofEntries(
            Map.entry("delete project by email", "-e alicebaker@gmail.com"),
            Map.entry("wrong prefix for project name", "-n Luminus"),
            Map.entry("delete project by client name", "-cn NUS")
        );
        String expectedErr = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE);
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser, input, expectedErr, "While testing case: " + desc);
        });
    }

    @Test
    public void parse_validProjectName_success() {
        DeleteProjectCommand want = new DeleteProjectCommand(NonEmptyString.of("Luminus"));
        Map<String, String> tests = Map.ofEntries(
            Map.entry("valid project name", "-pn Luminus"),
            Map.entry("valid project name with leading whitespace", "-pn       Luminus"),
            Map.entry("valid project name with trailing whitespace", "-pn Luminus      ")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseSuccess(parser, input, want, "While testing case: " + desc);
        });
    }
}
