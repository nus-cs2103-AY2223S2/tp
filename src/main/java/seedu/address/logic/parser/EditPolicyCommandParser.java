package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPolicyCommand object
 */
public class EditPolicyCommandParser implements Parser<EditPolicyCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditPolicyCommand
     * and returns and {@code EditPolicyCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public EditPolicyCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(userInput, PREFIX_POLICY_INDEX,
                PREFIX_POLICY_NAME, PREFIX_POLICY_START_DATE, PREFIX_POLICY_PREMIUM, PREFIX_POLICY_FREQUENCY);

        if (!arePrefixesPresent(argMultiMap, PREFIX_POLICY_INDEX) || argMultiMap.getPreamble().isEmpty()) {
            logger.info("Missing parameters: " + userInput);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE));
        }
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException e) {
            logger.info("Missing Index: " + userInput);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE));
        }

        Index policyIndex = ParserUtil.parseIndex(argMultiMap.getValue(PREFIX_POLICY_INDEX).get());

        EditPolicyCommand.EditPolicyDescriptor editPolicyDescriptor = new EditPolicyCommand.EditPolicyDescriptor();
        if (argMultiMap.getValue(PREFIX_POLICY_NAME).isPresent()) {
            editPolicyDescriptor.setPolicyName(
                   ParserUtil.parsePolicyName(argMultiMap.getValue(PREFIX_POLICY_NAME).get())
            );
        }
        if (argMultiMap.getValue(PREFIX_POLICY_START_DATE).isPresent()) {
            editPolicyDescriptor.setStartDate(
                    ParserUtil.parseCustomDate(argMultiMap.getValue(PREFIX_POLICY_START_DATE).get())
            );
        }
        if (argMultiMap.getValue(PREFIX_POLICY_PREMIUM).isPresent()) {
            editPolicyDescriptor.setPremium(
                    ParserUtil.parsePremium(argMultiMap.getValue(PREFIX_POLICY_PREMIUM).get())
            );
        }
        if (argMultiMap.getValue(PREFIX_POLICY_FREQUENCY).isPresent()) {
            editPolicyDescriptor.setFrequency(
                    ParserUtil.parseFrequency(argMultiMap.getValue(PREFIX_POLICY_FREQUENCY).get())
            );
        }
        if (!editPolicyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPolicyCommand.MESSAGE_NOT_EDITED);
        }
        logger.info("Parsed: " + userInput);
        return new EditPolicyCommand(index, policyIndex, editPolicyDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultiMap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
