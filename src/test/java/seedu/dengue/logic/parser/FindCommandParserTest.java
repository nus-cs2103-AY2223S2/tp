package seedu.dengue.logic.parser;

//import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.dengue.logic.commands.FindCommand;
//import seedu.dengue.model.person.Age;
//import seedu.dengue.model.person.Date;
//import seedu.dengue.model.person.Name;
//import seedu.dengue.model.person.SubPostal;
//import seedu.dengue.model.predicate.FindPredicate;
//import seedu.dengue.model.variant.Variant;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    //    @Test
    //    public void parse_emptyArg_throwsParseException() {
    //        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
    //        FindCommand.MESSAGE_USAGE));
    //    }

    //    @Test
    //    public void parse_validArgs_returnsFindCommand() {
    //        // no leading and trailing whitespaces
    //        Optional<SubPostal> emptySubPostal = Optional.empty();
    //        Optional<Name> testName = Optional.of(new Name("Alice"));
    //        Optional<Age> emptyAge = Optional.empty();
    //        Optional<Date> emptyDate = Optional.empty();
    //        Set<Variant> emptyVariants = new HashSet<>();
    //        FindCommand expectedFindCommand =
    //                new FindCommand(new FindPredicate(testName, emptySubPostal, emptyAge, emptyDate, emptyVariants));
    //        assertParseSuccess(parser, "n/Alice", expectedFindCommand);
    //
    //        // multiple whitespaces between keywords
    //        assertParseSuccess(parser, " n/\n Alice ", expectedFindCommand);
    //    }

}
