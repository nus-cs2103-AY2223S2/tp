package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static mycelium.mycelium.testutil.ClientBuilder.DEFAULT_EMAIL;
import static mycelium.mycelium.testutil.ClientBuilder.DEFAULT_MOBILE_NUMBER;
import static mycelium.mycelium.testutil.ClientBuilder.DEFAULT_NAME;
import static mycelium.mycelium.testutil.ClientBuilder.DEFAULT_SOURCE;
import static mycelium.mycelium.testutil.ClientBuilder.DEFAULT_YEAR_OF_BIRTH;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.UpdateClientCommand;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.testutil.UpdateClientDescriptorBuilder;

public class UpdateClientCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        UpdateClientCommand.MESSAGE_USAGE);
    private final UpdateClientCommandParser parser = new UpdateClientCommandParser();

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingRequiredArgs_throwsParseException() {
        assertParseFailure(parser, " -cn 123131", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " -e", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        Map<String, String> tests = Map.of(
            "-cn   ", Name.MESSAGE_CONSTRAINTS,
            "-e2 433", Email.MESSAGE_CONSTRAINTS,
            "-y mallory", YearOfBirth.MESSAGE_CONSTRAINTS,
            "-src  ", Messages.MESSAGE_EMPTY_SOURCE,
            "-mn scam", Phone.MESSAGE_CONSTRAINTS
        );
        tests.forEach((tt, err) -> {
            String input = " -e foobar@example.com " + tt;
            assertParseFailure(parser, input, err);
        });
    }

    @Test
    public void parse_requiredArgsGiven_success() {
        var input = " -e foobar@example.com";
        var want = new UpdateClientCommand(new Email("foobar@example.com"),
            new UpdateClientCommand.UpdateClientDescriptor());
        assertParseSuccess(parser, input, want);
    }

    @Test
    public void parse_allArgsGiven_success() {
        var email = "aaa@example.com";
        var desc = new UpdateClientDescriptorBuilder()
            .withName("John Doe")
            .withEmail(email)
            .withYearOfBirth("2023")
            .withSource("whited.com")
            .withMobileNumber("999")
            .build();

        var input = String.format(" -e %s -e2 %s -cn %s -src %s -y %s -mn %s",
            DEFAULT_EMAIL, email, DEFAULT_NAME, DEFAULT_SOURCE, DEFAULT_YEAR_OF_BIRTH, DEFAULT_MOBILE_NUMBER);
        var want = new UpdateClientCommand(new Email(DEFAULT_EMAIL), desc);

        assertParseSuccess(parser, input, want);
    }

    @Test
    public void parse_oldAndNewNamesIdentical_success() {
        var descriptor = new UpdateClientDescriptorBuilder().withEmail("foobar@example.com").build();
        var input = " -e foobar@example.com -e2 foobar@example.com";
        var want = new UpdateClientCommand(new Email("foobar@example.com"), descriptor);
        assertParseSuccess(parser, input, want);
    }

}
