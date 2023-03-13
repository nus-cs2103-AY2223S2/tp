package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.DoctorFilter;

/**
 * Parses input arguments and creates a new FindDoctorCommand object
 */
public class FindDoctorCommandParser implements Parser<FindDoctorCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDoctorCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SPECIALTY, PREFIX_YOE,
                        PREFIX_TAG);

        if (!hasAtLeastOnePrefix(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_SPECIALTY, PREFIX_YOE, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_NAME).orElse("").trim();
        String phone = argMultimap.getValue(PREFIX_PHONE).orElse("").trim();
        String email = argMultimap.getValue(PREFIX_EMAIL).orElse("").trim();
        String specialty = argMultimap.getValue(PREFIX_SPECIALTY).orElse("").trim();
        String yoe = argMultimap.getValue(PREFIX_YOE).orElse("").trim();
        Set<String> tagList = argMultimap.getAllValues(PREFIX_TAG)
                .stream().map(tag -> tag.toLowerCase().trim()).collect(Collectors.toSet());

        if (name.isEmpty() && phone.isEmpty() && email.isEmpty()
                && specialty.isEmpty() && yoe.isEmpty() && tagList.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        }

        DoctorFilter doctorFilter = new DoctorFilter(name, phone, email, specialty, yoe, tagList);

        return new FindDoctorCommand(new DoctorContainsKeywordsPredicate(doctorFilter));
    }

    /**
     * Returns true if at least one of the prefix contains {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAtLeastOnePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
