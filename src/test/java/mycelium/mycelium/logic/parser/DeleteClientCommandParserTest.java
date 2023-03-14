package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.model.person.Email;

public class DeleteClientCommandParserTest {
    // This command is simple, as it should look like this:
    //   dc -e EMAIL
    // So our tests are simple as well!

    private DeleteClientCommandParser parser = new DeleteClientCommandParser();

    @Test
    public void parse_missingRequiredArgs_fails() {
        Map<String, String> tests = Map.ofEntries(
            Map.entry("empty string", ""),
            Map.entry("only whitespace", " "),
            Map.entry("no email", "-e")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser,
                input,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE));
        });
    }

    @Test
    public void parse_invalidEmail_fails() {
        Map<String, String> tests = Map.ofEntries(
            Map.entry("email is whitespace", "-e "),
            Map.entry("invalid email", "-e foobar")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(parser, input, Email.MESSAGE_CONSTRAINTS);
        });
    }

    @Test
    public void parse_validEmail_success() {
        DeleteClientCommand want = new DeleteClientCommand(new Email("jamal@hogriders.org"));
        Map<String, String> tests = Map.ofEntries(
            Map.entry("valid email", "-e jamal@hogriders.org"),
            Map.entry("valid email with trailing whitespace", "-e jamal@hogriders.org   "),
            Map.entry("multiple emails", "-e jamar@hogriders.org -e jamal@hogriders.org")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseSuccess(parser, input, want);
        });
    }
}
