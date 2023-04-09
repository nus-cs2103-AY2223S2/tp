package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditVolunteerCommand object.
 */
public class EditVolunteerCommandParser implements Parser<EditVolunteerCommand> {
    private static final Prefix[] availablePrefixes = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_NRIC, PREFIX_BIRTH_DATE, PREFIX_REGION, PREFIX_TAG, PREFIX_MEDICAL_TAG, PREFIX_AVAILABILITY};
    /**
     * Parses the given {@code String} of arguments in the context of the EditVolunteerCommand
     * and returns an EditVolunteerCommand object for execution.
     *
     * @param args Arguments.
     * @return {@code EditVolunteerCommand} for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public EditVolunteerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, availablePrefixes);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditVolunteerCommand.MESSAGE_USAGE), pe);
        }

        EditDescriptor editDescriptor = new EditDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editDescriptor.setName(
                    ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editDescriptor.setEmail(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editDescriptor.setAddress(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            editDescriptor.setNric(
                    ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTH_DATE).isPresent()) {
            editDescriptor.setBirthDate(
                    ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTH_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_REGION).isPresent()) {
            editDescriptor.setRegion(
                    ParserUtil.parseRegion(argMultimap.getValue(PREFIX_REGION).get()));
        }
        EditCommandParser.parseRepeatableArgumentsForEdit(argMultimap.getAllEntries(PREFIX_TAG), ParserUtil::parseTags)
                .ifPresent(editDescriptor::setTags);
        EditCommandParser.parseRepeatableArgumentsForEdit(argMultimap.getAllEntries(PREFIX_AVAILABILITY),
                        ParserUtil::parseDateRanges)
                .ifPresent(editDescriptor::setAvailableDates);
        EditCommandParser.parseRepeatableArgumentsForEdit(argMultimap.getAllEntries(PREFIX_MEDICAL_TAG),
                        ParserUtil::parseMedicalTags)
                .ifPresent(editDescriptor::setMedicalTags);

        if (!editDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NO_FIELD_PROVIDED);
        }

        return new EditVolunteerCommand(index, editDescriptor);
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                EditVolunteerCommand.COMMAND_WORD,
                EditVolunteerCommand.COMMAND_PROMPTS,
                EditVolunteerCommandParser::validate, "<INDEX>");
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
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

