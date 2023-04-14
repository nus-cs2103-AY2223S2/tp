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

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.CopyCommand.CopyInformationSelector;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns an CopyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_RANK,
                        PREFIX_UNIT, PREFIX_COMPANY, PREFIX_PLATOON, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE), pe);
        }
        CopyInformationSelector copyInformationSelector = new CopyInformationSelector();
        copyInformationSelector.copyRank(argMultimap.getValue(PREFIX_RANK).isPresent());
        copyInformationSelector.copyName(argMultimap.getValue(PREFIX_NAME).isPresent());
        copyInformationSelector.copyUnit(argMultimap.getValue(PREFIX_UNIT).isPresent());
        copyInformationSelector.copyCompany(argMultimap.getValue(PREFIX_COMPANY).isPresent());
        copyInformationSelector.copyPlatoon(argMultimap.getValue(PREFIX_PLATOON).isPresent());
        copyInformationSelector.copyPhone(argMultimap.getValue(PREFIX_PHONE).isPresent());
        copyInformationSelector.copyEmail(argMultimap.getValue(PREFIX_EMAIL).isPresent());
        copyInformationSelector.copyAddress(argMultimap.getValue(PREFIX_ADDRESS).isPresent());
        copyInformationSelector.copyTags(argMultimap.getValue(PREFIX_TAG).isPresent());

        if (!copyInformationSelector.isAnyFieldSelected()) {
            copyInformationSelector.copyAllInformation(true);
        }
        return new CopyCommand(index, copyInformationSelector);
    }
}
