package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;
import seedu.address.model.person.patient.PatientFilter;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindPatientCommandParser implements Parser<FindPatientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPatientCommand
     * and returns a FindPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPatientCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_HEIGHT, PREFIX_WEIGHT,
                        PREFIX_DIAGNOSIS, PREFIX_STATUS, PREFIX_REMARK, PREFIX_TAG);

        if (!hasAtLeastOnePrefix(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_DIAGNOSIS, PREFIX_STATUS, PREFIX_REMARK, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPatientCommand.getCommandUsage()));
        }

        String name = argMultimap.getValue(PREFIX_NAME).orElse("").trim();
        String phone = argMultimap.getValue(PREFIX_PHONE).orElse("").trim();
        String email = argMultimap.getValue(PREFIX_EMAIL).orElse("").trim();
        String height = argMultimap.getValue(PREFIX_HEIGHT).orElse("").trim();
        String weight = argMultimap.getValue(PREFIX_WEIGHT).orElse("").trim();
        String diagnosis = argMultimap.getValue(PREFIX_DIAGNOSIS).orElse("").trim();
        String status = argMultimap.getValue(PREFIX_STATUS).orElse("").trim();
        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("").trim();
        Set<String> tagList = argMultimap.getAllValues(PREFIX_TAG)
                .stream().filter(tag -> !tag.isBlank())
                .map(tag -> tag.toLowerCase().trim()).collect(Collectors.toSet());

        if (name.isEmpty() && phone.isEmpty() && email.isEmpty() && height.isEmpty() && weight.isEmpty()
                && diagnosis.isEmpty() && status.isEmpty() && remark.isEmpty() && tagList.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPatientCommand.getCommandUsage()));
        }

        PatientFilter patientFilter = new PatientFilter(name, phone, email, height, weight,
                diagnosis, status, remark, tagList);

        return new FindPatientCommand(new PatientContainsKeywordsPredicate(patientFilter));
    }

    /**
     * Returns true if at least one of the prefix contains {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAtLeastOnePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
