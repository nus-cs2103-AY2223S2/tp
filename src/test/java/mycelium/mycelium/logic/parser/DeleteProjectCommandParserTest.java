package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;


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
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser,
                    input,
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
        });
    }

    @Test
    public void parse_wrongArgs_fails() {
        Map<String, String> tests = Map.ofEntries(
                Map.entry("delete project by email", "-e alicebaker@gmail.com"),
                Map.entry("wrong prefix for project name", "-n Luminus"),
                Map.entry("delete project by client name", "-cn NUS")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser,
                    input,
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
        });
    }

    @Test
    public void parse_validProjectName_success() {
        DeleteProjectCommand want = new DeleteProjectCommand("Luminus");
        Map<String, String> tests = Map.ofEntries(
                Map.entry("valid project name", "-pn Luminus")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseSuccess(parser, input, want);
        });
    }
}
