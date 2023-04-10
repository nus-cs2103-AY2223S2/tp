package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.person.InterviewDateTime.EMPTY_DATE_TIME;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AdvanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InterviewDateTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AdvanceCommand object
 */
public class AdvanceCommandParser implements Parser<AdvanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AdvanceCommand
     * and returns a AdvanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AdvanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AdvanceCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        InterviewDateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME)
                .orElse(EMPTY_DATE_TIME)).orElse(null);

        return new AdvanceCommand(new NamePhoneNumberPredicate(name, phone), Optional.ofNullable(dateTime));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
