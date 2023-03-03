package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATEANDTIME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.DateAndTime;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSTAL,
                        PREFIX_DATEANDTIME, PREFIX_AGE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_AGE, PREFIX_POSTAL, PREFIX_DATEANDTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Postal postal = ParserUtil.parsePostal(argMultimap.getValue(PREFIX_POSTAL).get());
        DateAndTime dateAndTime = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_DATEANDTIME).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, postal, dateAndTime, age, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
