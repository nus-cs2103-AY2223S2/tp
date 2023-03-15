package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class AddClientCommandParserTest {
    // TODO I noticed the argument tokenizer takes the LAST instance of a
    // prefix, not the first. We must add this to the user guide or something.
    @Test
    public void parse_missingRequiredArgs_throwsParseException() {
        // Just a silly little check, so that we can use -cn and -e verbatim in
        // our test cases instead of relying on this variable.
        assertEquals(CliSyntax.PREFIX_CLIENT_NAME.getPrefix(), "-cn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");
        // For each of the following cases, we expect the parser to throw an exception.
        Map<String, String> tests = Map.ofEntries(
            Map.entry("empty string", ""),
            Map.entry("only whitespace", " "),

            Map.entry("has name prefix only, but no name", "-cn"),
            Map.entry("has name prefix only, but no name (whitespace)", "-cn "),
            Map.entry("has name prefix only, but no whitespace", "-cnBob"),

            Map.entry("has email prefix only, but no email", "-e"),
            Map.entry("has email prefix only, but no email (whitespace)", "-e "),
            Map.entry("has email prefix only, but no whitespace", "-ehogrider@coc.org"),

            Map.entry("prefixes are not separated", "-cn-e"),
            Map.entry("name and email not separated", "-cnBob-ehogrider@coc.org")
        );
        tests.forEach((desc, tt) -> {
            // Don't know why the argument tokenizer is written in this way
            // where it expects an initial whitespace. But this is why I added
            // the " " in front.
            String input = " " + tt;
            assertParseFailure(new AddClientCommandParser(), input, String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddClientCommand.MESSAGE_USAGE
            ), "While testing case: " + desc);
        });
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertEquals(CliSyntax.PREFIX_CLIENT_NAME.getPrefix(), "-cn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        // For each of the following cases, we expect the parser to throw an exception.
        Map<String, Pair<String, String>> tests = Map.ofEntries(
            Map.entry("name is whitespace", Pair.of("-cn   -e hogrider@coc.org", Name.MESSAGE_CONSTRAINTS)),
            Map.entry("email is whitespace", Pair.of("-cn Bob -e   ", Email.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid email", Pair.of("-cn Bob -e foobar", Email.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid name", Pair.of("-cn SHA-256 -e hogrider@coc.org", Name.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid mobile number",
                Pair.of("-cn Jamal -e jamal@hogriders.org -mn hogridaaaa", Phone.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid source (empty)",
                Pair.of("-cn Jamal -e jamal@hogriders.org -src ", Messages.MESSAGE_EMPTY_STR)),
            Map.entry("invalid year of birth",
                Pair.of("-cn Jamal -e jamal@hogriders.org -y 42069", YearOfBirth.MESSAGE_CONSTRAINTS))
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt.first;
            assertParseFailure(new AddClientCommandParser(), input, tt.second, "While testing case: " + desc);
        });
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        assertEquals(CliSyntax.PREFIX_CLIENT_NAME.getPrefix(), "-cn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        // Suppose we are trying to add Jamal (or Jamal the Hogrider) from jamal@hogriders.org.
        AddClientCommand addJamal = new AddClientCommand(
            new ClientBuilder().withName("jamal").withEmail("jamal@hogriders.org").build());
        AddClientCommand
            addJamalTheHogrider =
            new AddClientCommand(new ClientBuilder().withName("Jamal the Hogrider")
                .withEmail("jamal@hogriders.org")
                .build());

        Map<String, Pair<String, AddClientCommand>> tests = Map.ofEntries(
            Map.entry("name first, then email", Pair.of("-cn Jamal -e jamal@hogriders.org", addJamal)),
            Map.entry("email first, then name", Pair.of("-e jamal@hogriders.org -cn Jamal", addJamal)),
            Map.entry("trailing whitespace", Pair.of("-cn Jamal -e jamal@hogriders.org  ", addJamal)),

            Map.entry("multiple words in name",
                Pair.of("-cn Jamal the Hogrider -e jamal@hogriders.org", addJamalTheHogrider)),

            Map.entry("multiple names", Pair.of("-e jamal@hogriders.org -cn Alice -cn jamal", addJamal)),
            Map.entry("multiple emails", Pair.of("-cn jamal -e alice@bakers.com -e jamal@hogriders.org", addJamal)),
            Map.entry("multiple names and emails",
                Pair.of("-cn Alice -e alice@bakers.com -cn jamal -e jamal@hogriders.org", addJamal)),

            Map.entry("all arguments present",
                Pair.of("-cn jamal -e jamal@hogriders.org -mn 62353535 -src Fiverr -y 1843", addJamal))
        );
        for (String desc : tests.keySet()) {
            String input = " " + tests.get(desc).first;
            AddClientCommand want = tests.get(desc).second;
            assertParseSuccess(new AddClientCommandParser(), input, want, "While testing case: " + desc);
        }
    }
}
