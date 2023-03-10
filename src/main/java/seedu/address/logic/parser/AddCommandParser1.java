package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax1.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax1.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax1.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax1.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax1.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand1;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Id;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Position;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser1 implements Parser1<AddCommand1> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand1 parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand1.MESSAGE_USAGE));
        }

        Position position = ParserUtil1.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Company company = ParserUtil1.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Status status = ParserUtil1.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Description description = ParserUtil1.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil1.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Id id = new Id("0");
        // pass id as 0 for now
        Internship internship = new Internship(position, company, id, status, description, tagList);

        return new AddCommand1(internship);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
