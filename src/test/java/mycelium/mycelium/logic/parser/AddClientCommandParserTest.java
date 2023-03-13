package mycelium.mycelium.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class AddClientCommandParserTest {
    // TODO I noticed the argument tokenizer takes the LAST instance of a
    // prefix, not the first. We must add this to the user guide or something.
    @Test
    public void parse_invalidInput_throwsParseException() {
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
            Map.entry("no name nor email", "-cn -e"),
            Map.entry("no name nor email (whitespace)", "-cn  -e "),
            Map.entry("email, but no name", "-cn -e hogrider@coc.org"),
            Map.entry("name, but no email", "-cn Bob -e"),
            Map.entry("name, butt email is whitespace", "-cn Bob -e "),
            Map.entry("name and email not separated", "-cnBob-ehogrider@coc.org"),

            Map.entry("invalid email", "-cn Bob -e foobar"),
            Map.entry("invalid name", "-cn SHA-256 -e hogrider@coc.org")
        );
        tests.forEach((desc, tt) -> {
            // Don't know why the argument tokenizer is written in this way
            // where it expects an initial whitespace. But this is why I added
            // the " " in front.
            String input = " " + tt;
            Assertions.assertThrows(ParseException.class, ()
                    -> new AddClientCommandParser().parse(input),
                "While testing case: " + desc);
        });
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        assertEquals(CliSyntax.PREFIX_CLIENT_NAME.getPrefix(), "-cn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        // Suppose we are trying to add Jamal (or Jamal the Hogrider) from jamal@hogrider.org.
        AddClientCommand
            jamal =
            new AddClientCommand(new ClientBuilder().withName("jamal").withEmail("jamal@hogrider.org").build());
        AddClientCommand
            jamal2 =
            new AddClientCommand(new ClientBuilder().withName("Jamal the Hogrider")
                .withEmail("jamal@hogrider.org")
                .build());

        Map<String, Pair<String, AddClientCommand>> tests = Map.ofEntries(
            Map.entry("name first, then email", Pair.of("-cn Jamal -e jamal@hogrider.org", jamal)),
            Map.entry("email first, then name", Pair.of("-e jamal@hogrider.org -cn Jamal", jamal)),
            Map.entry("trailing whitespace", Pair.of("-cn Jamal -e jamal@hogrider.org  ", jamal)),
            Map.entry("multiple words in name", Pair.of("-cn Jamal the Hogrider -e jamal@hogrider.org", jamal2)),
            Map.entry("multiple names", Pair.of("-e jamal@hogrider.org -cn Alice -cn jamal", jamal)),
            Map.entry("multiple emails", Pair.of("-cn jamal -e alice@bakers.com -e jamal@hogrider.org", jamal)),
            Map.entry("multiple names and emails",
                Pair.of("-cn Alice -e alice@bakers.com -cn jamal -e jamal@hogrider.org", jamal))
        );
        for (String desc : tests.keySet()) {
            String input = " " + tests.get(desc).first;
            AddClientCommand want = tests.get(desc).second;
            AddClientCommand got = new AddClientCommandParser().parse(input);
            assertEquals(want, got, "While testing case: " + desc);
        }
    }
}
