package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditElderlyCommand;
import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditElderlyCommand object
 */
public class EditElderlyCommandParser implements Parser<EditElderlyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditElderlyCommand
     * and returns an EditElderlyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditElderlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NRIC_ELDERLY, PREFIX_AGE, PREFIX_RISK, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditElderlyCommand.MESSAGE_USAGE), pe);
        }

        EditElderlyDescriptor editElderlyDescriptor = new EditElderlyDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editElderlyDescriptor.setName(
                    ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editElderlyDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editElderlyDescriptor.setEmail(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editElderlyDescriptor.setAddress(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_NRIC_ELDERLY).isPresent()) {
            editElderlyDescriptor.setNric(
                    ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_ELDERLY).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editElderlyDescriptor.setAge(
                    ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_RISK).isPresent()) {
            editElderlyDescriptor.setRiskLevel(
                    ParserUtil.parseRiskLevel(argMultimap.getValue(PREFIX_RISK).get()));
        }
        EditCommandParser.parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editElderlyDescriptor::setTags);

        if (!editElderlyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(Messages.MESSAGE_NOT_EDITED);
        }

        return new EditElderlyCommand(index, editElderlyDescriptor);
    }

}
