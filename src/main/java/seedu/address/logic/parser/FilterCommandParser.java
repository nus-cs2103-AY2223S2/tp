package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATOON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.List;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code FilterCommand} object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FilterCommand}
     * and returns a {@code FilterCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_RANK,
                        PREFIX_UNIT, PREFIX_COMPANY, PREFIX_PLATOON, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        FilterDescriptor filterDescriptor = new FilterDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameValue = argMultimap.getValue(PREFIX_NAME).get();
            requireFieldValueNotBlank(nameValue);
            filterDescriptor.setNameValue(nameValue);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneValue = argMultimap.getValue(PREFIX_PHONE).get();
            requireFieldValueNotBlank(phoneValue);
            filterDescriptor.setPhoneValue(phoneValue);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String emailValue = argMultimap.getValue(PREFIX_EMAIL).get();
            requireFieldValueNotBlank(emailValue);
            filterDescriptor.setEmailValue(emailValue);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String addressValue = argMultimap.getValue(PREFIX_ADDRESS).get();
            requireFieldValueNotBlank(addressValue);
            filterDescriptor.setAddressValue(addressValue);
        }
        if (argMultimap.getValue(PREFIX_RANK).isPresent()) {
            String rankValue = argMultimap.getValue(PREFIX_RANK).get();
            requireFieldValueNotBlank(rankValue);
            filterDescriptor.setRankValue(rankValue);
        }
        if (argMultimap.getValue(PREFIX_UNIT).isPresent()) {
            String unitValue = argMultimap.getValue(PREFIX_UNIT).get();
            requireFieldValueNotBlank(unitValue);
            filterDescriptor.setUnitValue(unitValue);
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            String companyValue = argMultimap.getValue(PREFIX_COMPANY).get();
            requireFieldValueNotBlank(companyValue);
            filterDescriptor.setCompanyValue(companyValue);
        }
        if (argMultimap.getValue(PREFIX_PLATOON).isPresent()) {
            String platoonValue = argMultimap.getValue(PREFIX_PLATOON).get();
            requireFieldValueNotBlank(platoonValue);
            filterDescriptor.setPlatoonValue(platoonValue);
        }

        List<String> tagValues = argMultimap.getAllValues(PREFIX_TAG);
        assert tagValues != null;
        if (tagValues.stream().anyMatch(t -> t.equals(""))) {
            throw new ParseException(FilterCommand.MESSAGE_EMPTY_FIELD);
        }

        filterDescriptor.setTagValues(tagValues);

        if (!filterDescriptor.hasNonEmptyField()) {
            throw new ParseException(FilterCommand.MESSAGE_NO_FIELD_GIVEN);
        }
        return new FilterCommand(filterDescriptor);
    }

    private String requireFieldValueNotBlank(String fieldValue) throws ParseException {
        if (fieldValue.isBlank()) {
            throw new ParseException(FilterCommand.MESSAGE_EMPTY_FIELD);
        }
        return fieldValue;
    }

}
