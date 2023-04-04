package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.model.client.Email;

public class DeleteClientCommandParserTest {
    // This command is simple, as it should look like this:
    //   dc -e EMAIL
    // So our tests are simple as well!

    private DeleteClientCommandParser parser = new DeleteClientCommandParser();

    @Test
    public void parse_missingRequiredArgs_fails() {
        Map<String, String> tests = Map.of(
            "empty string", "",
            "only whitespace", " "
        );
        String expectedErr = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE);
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser, input, expectedErr, "While testing case: " + desc);
        });
    }

    @Test
    public void parse_invalidEmail_fails() {
        Map<String, String> tests = Map.of(
            "email is whitespace", "-e ",
            "email is empty", "-e",
            "invalid email", "-e foobar"
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser, input, Email.MESSAGE_CONSTRAINTS, "While testing case: " + desc);
        });
    }

    @Test
    public void parse_validEmail_success() {
        DeleteClientCommand want = new DeleteClientCommand(new Email("jamal@hogriders.org"));
        Map<String, String> tests = Map.of(
            "valid email", "-e jamal@hogriders.org",
            "valid email with trailing whitespace", "-e jamal@hogriders.org   ",
            "multiple emails", "-e jamar@hogriders.org -e jamal@hogriders.org"
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseSuccess(parser, input, want, "While testing case: " + desc);
        });
    }
}
