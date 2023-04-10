package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddElderlyCommand;
import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddElderlyCommand object.
 */
public class AddElderlyCommandParser implements Parser<AddElderlyCommand> {
    private static final Prefix[] availablePrefixes = { PREFIX_NAME, PREFIX_NRIC, PREFIX_PHONE, PREFIX_EMAIL,
        PREFIX_ADDRESS, PREFIX_BIRTH_DATE, PREFIX_REGION, PREFIX_RISK, PREFIX_AVAILABILITY, PREFIX_TAG };
    private static final Prefix[] compulsoryPrefixes = { PREFIX_NAME, PREFIX_NRIC, PREFIX_BIRTH_DATE };

    /**
     * Parses the given {@code String} of arguments in the context of the AddElderlyCommand
     * and returns an AddElderlyCommand object for execution.
     * Name, Nric, birthdate, region and risk must be specified.
     *
     * @param args Arguments.
     * @return {@code AddElderlyCommand} for execution.
     * @throws ParseException If the user input does not conform the expected
     *                        format.
     */
    public AddElderlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, availablePrefixes);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, compulsoryPrefixes)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddElderlyCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        BirthDate birthDate = ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTH_DATE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(FIELD_NOT_SPECIFIED));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(FIELD_NOT_SPECIFIED));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(FIELD_NOT_SPECIFIED));
        Region region = ParserUtil.parseRegion(argMultimap.getValue(PREFIX_REGION).orElse(FIELD_NOT_SPECIFIED));
        RiskLevel risk = ParserUtil.parseRiskLevel(argMultimap.getValue(PREFIX_RISK).orElse(FIELD_NOT_SPECIFIED));
        Set<AvailableDate> availableDates = ParserUtil.parseDateRanges(argMultimap.getAllEntries(PREFIX_AVAILABILITY));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllEntries(PREFIX_TAG));

        Elderly person = new Elderly(name, phone, email,
                address, nric, birthDate, region, risk, tagList, availableDates);
        return new AddElderlyCommand(person);
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                AddElderlyCommand.COMMAND_WORD,
                AddElderlyCommand.COMMAND_PROMPTS,
                AddElderlyCommandParser::validate);
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain
     * criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        if (PrefixUtil.checkIfContainsInvalidPrefixes(map)) {
            throw new RecommendationException("Invalid prefix.");
        } else {
            return true;
        }
    }
}
