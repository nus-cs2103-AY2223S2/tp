package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.MedicalQualificationTag;

/**
 * Parses input arguments and creates a new AddElderlyCommand object
 */
public class AddVolunteerCommandParser implements Parser<AddVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddElderlyCommand
     * and returns an AddElderlyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NRIC_VOLUNTEER, PREFIX_AGE,
                        PREFIX_REGION, PREFIX_MEDICAL_TAG, PREFIX_TAG);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC_VOLUNTEER, PREFIX_AGE, PREFIX_REGION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddVolunteerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Region region = ParserUtil.parseRegion(argMultimap.getValue(PREFIX_REGION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<MedicalQualificationTag> medicalTagList = ParserUtil.parseMedicalTags(argMultimap.getAllValues(PREFIX_MEDICAL_TAG));

        Volunteer volunteer = new Volunteer(name, phone, email, address, nric, age, region, tagList, medicalTagList);

        return new AddVolunteerCommand(volunteer);
    }
}
