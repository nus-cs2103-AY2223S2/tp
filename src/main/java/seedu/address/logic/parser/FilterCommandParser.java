package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterTuteeDescription;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Arrays;

public class FilterCommandParser implements Parser<FilterCommand>  {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SUBJECT,
                        PREFIX_SCHEDULE, PREFIX_STARTTIME, PREFIX_ENDTIME, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        FilterTuteeDescription filterTuteeDescription = new FilterTuteeDescription();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = trimmedArgs.split("\\s+");
            filterTuteeDescription.setNameToFilter(Arrays.asList(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            filterTuteeDescription.setPhoneToFilter(requireNonNull(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            filterTuteeDescription.setEmailToFilter(requireNonNull(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_ADDRESS).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            String[] addressKeywords = trimmedArgs.split("\\s+");
            filterTuteeDescription.setAddressToFilter(Arrays.asList(addressKeywords));
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            filterTuteeDescription.setSubjectToFilter(requireNonNull(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        if (argMultimap.getValue(PREFIX_SCHEDULE).isPresent()) {
            filterTuteeDescription.setScheduleToFilter(requireNonNull(argMultimap.getValue(PREFIX_SCHEDULE).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
            filterTuteeDescription.setStartTimeToFilter(requireNonNull(argMultimap.getValue(PREFIX_STARTTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_ENDTIME).isPresent()) {
            filterTuteeDescription.setEndTimeToFilter(requireNonNull(argMultimap.getValue(PREFIX_ENDTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_TAG).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            String[] tagKeywords = trimmedArgs.split("\\s+");
            filterTuteeDescription.setTagToFilter(Arrays.asList(tagKeywords));
        }

        if (filterTuteeDescription.isAllFieldEmpty()) {
            throw new ParseException(FilterCommand.MESSAGE_NOT_FILTERED);
        }

        return new FilterCommand(filterTuteeDescription);
    }

}
