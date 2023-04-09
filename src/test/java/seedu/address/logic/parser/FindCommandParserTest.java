package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordPredicate;
import seedu.address.model.person.predicates.StatusContainsKeywordPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validName_success() {
        Predicate<Person> namePredicate = new NameContainsKeywordPredicate("John");
        Command expectedCommand = new FindCommand(namePredicate);
        assertParseSuccess(parser, "find n/John", expectedCommand);
    }

    @Test
    public void parse_validPhone_success() {
        Predicate<Person> phonePredicate = new PhoneContainsKeywordPredicate("123");
        Command expectedCommand = new FindCommand(phonePredicate);
        assertParseSuccess(parser, "find p/123", expectedCommand);
    }

    @Test
    public void parse_validEmail_success() {
        Predicate<Person> emailPredicate = new EmailContainsKeywordPredicate("gmail");
        Command expectedCommand = new FindCommand(emailPredicate);
        assertParseSuccess(parser, "find e/gmail", expectedCommand);
    }

    @Test
    public void parse_validStatus_success() {
        Predicate<Person> statusPredicate = new StatusContainsKeywordPredicate("y4");
        Command expectedCommand = new FindCommand(statusPredicate);
        assertParseSuccess(parser, "find s/y4", expectedCommand);
    }

    @Test
    public void parse_validAddress_success() {
        Predicate<Person> addressPredicate = new AddressContainsKeywordPredicate("blk");
        Command expectedCommand = new FindCommand(addressPredicate);
        assertParseSuccess(parser, "find a/blk", expectedCommand);
    }

    @Test
    public void parse_validTag_success() {
        Predicate<Person> tagPredicate = new TagContainsKeywordPredicate("cs");
        Command expectedCommand = new FindCommand(tagPredicate);
        assertParseSuccess(parser, "find t/cs", expectedCommand);
    }

    @Test
    public void parse_emptyName_exceptionThrown() {
        assertParseFailure(parser, "find n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }
}
